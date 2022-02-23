package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IMU;
import java.lang.Math;
import java.text.DecimalFormat;

public class SnapLeft extends CommandBase {
  private double initAngle;
  private double newAngle; 
  private IMU subsysIMU;
  private Drivetrain drivetrain;
  private PIDController pid;
  private double angleTolerance;

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
    initAngle = (subsysIMU.getAngle() % 360 + 360) % 360;
    System.out.println("Initial Angle: " + angleFormat.format(initAngle));

    // Find which angle (0, 90, 180, 270) robot is closest to
    if (initAngle < 90) {
      newAngle = 90;
    } else if (initAngle < 180) {
      newAngle = 180;
    } else if (initAngle < 270) {
      newAngle = 270;
    } else {
      newAngle = 360;
    }
    System.out.println("Turning to: " + angleFormat.format(newAngle));
    System.out.println();

    double error = Math.abs(newAngle - initAngle);

    // pid constants
    double kP = Math.abs(Constants.maxTurnPower / error);
    double kD = 0;
    double kI = kP / 200;

    pid = new PIDController(kP, kI, kD);

    angleTolerance = 1.0 / error * 100.0;
    pid.setTolerance(angleTolerance);
    // pid.enableContinuousInput(0, 360);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // angle confined from 0 to 360 including negatives
    double currAngle = (subsysIMU.getAngle() % 360 + 360) % 360;

    // speed dependent on angle distance
    RobotContainer.myRobot.arcadeDrive(pid.calculate(currAngle, newAngle), 0);
  }

  // Called once the command ends or is interrupted.
  public void end() {
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // end if the angle is approximately the desired one
    if (pid.atSetpoint()) {
      return true;
    } 
    else {
      return false;
    }
  }
}
