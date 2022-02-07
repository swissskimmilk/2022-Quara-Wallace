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

public class LeftTurn extends CommandBase {
  public Drivetrain drivetrain;
  public IMU subsysIMU;
  public double angle;
  public double error;
  
  public LeftTurn(Drivetrain mDrivetrain, IMU mIMU) {
    drivetrain = mDrivetrain;
    subsysIMU = mIMU;
    addRequirements(drivetrain, subsysIMU);
    // the angle we want
    double angle = (90 + RobotContainer.ADIS_IMU.getAngle()) % 360;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (angle >= 270) {
      error = abs(360 - RobotContainer.ADIS_IMU.getAngle()) + angle;
    } else {
      error = (angle - RobotContainer.ADIS_IMU.getAngle());
    }
    // i think right is not inverted so negative is positive is negative, 180 is just to damp the speed
    RobotContainer.myRobot.arcadeDrive((subsysIMU.kP * error) / 180, 0);
  }

  // Called once the command ends or is interrupted.
  public void end() {
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (abs(error) <= 2) {
      return true;
    } else {
      return false;
    }
  }
}
