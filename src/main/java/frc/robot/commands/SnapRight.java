package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IMU;
import java.lang.Math;

public class SnapRight extends CommandBase {
  private double error;
  private double initAngle;
  private double newAngle; 
  private IMU subsysIMU;
  private Drivetrain drivetrain;

  public SnapRight(Drivetrain mDrivetrain, IMU mIMU) {
    drivetrain = mDrivetrain;
    subsysIMU = mIMU;
    addRequirements(mDrivetrain, subsysIMU);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initAngle = RobotContainer.ADIS_IMU.getAngle() % 360;
    // Find which angle (0, 90, 180, 270) robot is closest to
    if (initAngle >= 0 && initAngle < 90) {
      newAngle = 90;
    } 
    else if (initAngle >= 90 && initAngle < 180) {
      newAngle = 180;
    } 
    else if (initAngle >= 180 && initAngle < 270) {
      newAngle = 270;
    } 
    else if (initAngle >= 270 && initAngle < 360){
      newAngle = 0;
    }
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
    if (Math.abs(error) <= Constants.angleTolerance) {
      return true;
    } 
    else {
      return false;
    }
  }
}
