package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IMU;
import java.lang.Math;
import java.text.DecimalFormat;

public class RightTurn extends CommandBase {
  private Drivetrain drivetrain;
  private IMU subsysIMU;
  private double newAngle;
  private double error;

  public RightTurn(Drivetrain mDrivetrain, IMU mIMU) {
    drivetrain = mDrivetrain;
    subsysIMU = mIMU;
    addRequirements(drivetrain, subsysIMU);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DecimalFormat angleFormat = new DecimalFormat("###.##");
    
    System.out.println("Turning Right");
    double currAngle = RobotContainer.ADIS_IMU.getAngle();
    System.out.println("Initial Angle:" + angleFormat.format(currAngle));
    newAngle = currAngle - 90;
    System.out.println("Turning to: " + angleFormat.format(newAngle));
    System.out.println();
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currAngle = RobotContainer.ADIS_IMU.getAngle();
    error = newAngle - currAngle;
    RobotContainer.myRobot.arcadeDrive(Math.abs(subsysIMU.kP * error * Constants.autoTurnMult), 0);
  }

  // Called once the command ends or is interrupted.
  public void end() {
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(error) <= Constants.errorTolerance) {
      return true;
    } 
    else {
      return false;
    }
  }
}
