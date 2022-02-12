package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IMU;
import java.lang.Math;

public class LeftTurn extends CommandBase {
  public Drivetrain drivetrain;
  public IMU subsysIMU;
  public double newAngle;
  public double error;
  
  public LeftTurn(Drivetrain mDrivetrain, IMU mIMU) {
    drivetrain = mDrivetrain;
    subsysIMU = mIMU;
    addRequirements(drivetrain, subsysIMU);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double currAngle = RobotContainer.ADIS_IMU.getAngle();
    // limit angle from 0 to 360, must convert 
    newAngle = currAngle + 90;
    System.out.println("newAngle: " + newAngle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currAngle = RobotContainer.ADIS_IMU.getAngle();
    error = newAngle - currAngle;
    RobotContainer.myRobot.arcadeDrive(-Math.abs(subsysIMU.kP * error) * Constants.autoTurnMult, 0);
  }

  // Called once the command ends or is interrupted.
  public void end() {
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(error) <= Constants.angleTolerance) {
      return true;
    } 
    else {
      return false;
    }
  }
}
