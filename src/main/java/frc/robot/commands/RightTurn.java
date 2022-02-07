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
  // The gain for a simple P loop
  double kP = 1;
  // distance left to turn
  double error = 90;

  public Drivetrain drivetrain;
  public IMU subsysIMU;

  public RightTurn(Drivetrain mDrivetrain, IMU mIMU) {
    drivetrain = mDrivetrain;
    subsysIMU = mIMU;
    addRequirements(drivetrain, subsysIMU);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    error += RobotContainer.ADIS_IMU.getAngle();
    RobotContainer.myRobot.arcadeDrive(0, subsysIMU.kP * error);
  }

  // Called once the command ends or is interrupted.
  public void end() {
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (error <= 4 && error >= -4) {
      return true;
    } else {
      return false;
    }
  }
}