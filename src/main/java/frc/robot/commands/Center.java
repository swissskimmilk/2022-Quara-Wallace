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

public class Center extends CommandBase {
  // desired angle of robot
  double angle;
  
  // distance left to center
  double error;
  
  IMU subsysIMU;
  Drivetrain drivetrain;

  public Center(Drivetrain mDrivetrain, IMU mIMU) {
    drivetrain = mDrivetrain;
    subsysIMU = mIMU;
    addRequirements(mDrivetrain, subsysIMU);
    double initAngle = RobotContainer.ADIS_IMU.getAngle();
    // find which angle (0, 90, 180, 270) robot is closest to
    if (initAngle >= 315 || initAngle <= 45) {
      angle = 0.0;
    } else if (initAngle > 45 || initAngle <= 135) {
      angle = 90.0;
    } else if (initAngle > 135 || initAngle <= 225) {
      angle = 180.0;
    } else if (initAngle > 225 || initAngle < 315){
      angle = 270.0;
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.ADIS_IMU.getAngle() >= 270) {
      error = 360 - RobotContainer.ADIS_IMU.getAngle() + angle;
    } else {
      error = angle - RobotContainer.ADIS_IMU.getAngle();
    }
    RobotContainer.myRobot.arcadeDrive(-subsysIMU.kP * error / 180, 0);
  }

  // Called once the command ends or is interrupted.
  public void end() {
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(error) <= 2) {
      return true;
    } else {
      return false;
    }
  }
}
