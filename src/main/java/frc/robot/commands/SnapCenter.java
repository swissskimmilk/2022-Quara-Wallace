package frc.robot.commands;

import java.util.Map;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveMode;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IMU;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import java.lang.Math;

public class SnapCenter extends CommandBase {
  private double error;
  private double initAngle;
  private double newAngle; 
  private IMU subsysIMU;
  private Drivetrain drivetrain;

  public SnapCenter(Drivetrain mDrivetrain, IMU mIMU) {
    drivetrain = mDrivetrain;
    subsysIMU = mIMU;
    addRequirements(mDrivetrain, subsysIMU);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initAngle = RobotContainer.ADIS_IMU.getAngle();
    // Find which angle (0, 90, 180, 270) robot is closest to
    if (initAngle > 315 && initAngle <= 45) {
      newAngle = 0.0;
    } 
    else if (initAngle > 45 && initAngle <= 135) {
      newAngle = 90.0;
    } 
    else if (initAngle > 135 && initAngle <= 225) {
      newAngle = 180.0;
    } 
    else if (initAngle > 225 && initAngle <= 315){
      newAngle = 270.0;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currAngle = RobotContainer.ADIS_IMU.getAngle();
    if (newAngle != 0) {
      error = newAngle - currAngle;
    }
    else {
      if (currAngle > 315) {
        error = 360 - currAngle;
      }
      else if (currAngle <= 45) {
        error = -currAngle;
      }
    }

    // I dunno what this is supposed to do 
    // if (RobotContainer.ADIS_IMU.getAngle() >= 270) {
    //   error = 360 - RobotContainer.ADIS_IMU.getAngle() + newAngle;
    // } 
    // else {
    //   error = newAngle - RobotContainer.ADIS_IMU.getAngle();
    // }
    RobotContainer.myRobot.arcadeDrive(subsysIMU.kP * error / 180, 0);
  }

  // Called once the command ends or is interrupted.
  public void end() {
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(error) <= 2) {
      return true;
    } 
    else {
      return false;
    }
  }
}
