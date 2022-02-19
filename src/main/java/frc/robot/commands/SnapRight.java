package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IMU;
import java.lang.Math;
import java.text.DecimalFormat;

public class SnapRight extends CommandBase {
  private double error;

  private double initAngle;
  private double newAngle; 
  private double lastAngle;
  private double integralSum;

  private IMU subsysIMU;
  private Drivetrain drivetrain;
  private double turnRate;

  public SnapRight(Drivetrain mDrivetrain, IMU mIMU) {
    drivetrain = mDrivetrain;
    subsysIMU = mIMU;
    addRequirements(mDrivetrain, subsysIMU);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Snap Right");
    DecimalFormat angleFormat = new DecimalFormat("###.##");

    // angle bounded from 0 to 360 including negatives
    initAngle = (subsysIMU.getAngle() % 360 + 360) % 360;
    System.out.println("Initial Angle: " + angleFormat.format(initAngle));

    // Find which angle (0, 90, 180, 270) robot is closest to
    if (initAngle > 270) {
      newAngle = 270;
    } else if (initAngle > 180) {
      newAngle = 180;
    } else if (initAngle > 90) {
      newAngle = 90;
    } else {
      newAngle = 0;
    }
    System.out.println("Turning to: " + angleFormat.format(newAngle));
    System.out.println();

    // multiply the speed by this to make it range from 1 to 0 (ish) 
    turnRate = 1 / (newAngle - initAngle);
    lastAngle = subsysIMU.getAngle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // angle confined from 0 to 360 including negatives
    double currAngle = (subsysIMU.getAngle() % 360 + 360) % 360;

    // PID vars
    error = newAngle - currAngle;
    integralSum += error * 0.02;
    double derivative = (lastAngle - currAngle) / 0.02;

    // PID calculation 
    double movement = Constants.PMult * error + Constants.IMult * integralSum + Constants.DMult * derivative;

    System.out.println("Err: " + error + " I: " + integralSum + " D: " + derivative + " Mvmt: " + movement);
    // speed dependent on angle distance
    RobotContainer.myRobot.arcadeDrive(movement, 0);

    lastAngle = currAngle;
  }

  // Called once the command ends or is interrupted.
  public void end() {
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // end if the angle is approximately the desired one
    if (Math.abs(error) <= Constants.errorTolerance) {
      return true;
    } 
    else {
      return false;
    }
  }
}
