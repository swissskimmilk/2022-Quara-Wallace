package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IMU;
import java.lang.Math;
import java.text.DecimalFormat;

public class SnapLeft extends CommandBase {
  private double error;
  private double initAngle;
  private double newAngle; 
  private IMU subsysIMU;
  private Drivetrain drivetrain;
  private double turnRate;

  public SnapLeft(Drivetrain mDrivetrain, IMU mIMU) {
    drivetrain = mDrivetrain;
    subsysIMU = mIMU;
    addRequirements(mDrivetrain, subsysIMU);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Snap Left");
    DecimalFormat angleFormat = new DecimalFormat("###.##");

    // angle bounded from 0 to 360 including negatives
    initAngle = (RobotContainer.ADIS_IMU.getAngle() % 360 + 360) % 360;
    System.out.println("Initial Angle: " + angleFormat.format(initAngle));

    // Find which angle (0, 90, 180, 270) robot is closest to
    if (initAngle > 270) {
      newAngle = 0;
    } else if (initAngle < 90) {
      newAngle = 90;
    } else if (initAngle < 180) {
      newAngle = 180;
    } else if (initAngle > 180) {
      newAngle = 270;
    }
    System.out.println("Turning to: " + angleFormat.format(newAngle));

    // multiply the speed by this to make it range from 1 to 0 (ish) 
    turnRate = 1 / (newAngle - initAngle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // angle confined from 0 to 360 including negatives
    double currAngle = (RobotContainer.ADIS_IMU.getAngle() % 360 + 360) % 360;

    // angle distance remaining 
    error = newAngle - currAngle;

    // speed dependent on angle distance
    RobotContainer.myRobot.arcadeDrive(-Math.abs(subsysIMU.kP * error * turnRate), 0);
  }

  // Called once the command ends or is interrupted.
  public void end() {
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // end if the angle is approximately the desired one
    if (Math.abs(error) <= Constants.angleTolerance) {
      return true;
    } 
    else {
      return false;
    }
  }
}
