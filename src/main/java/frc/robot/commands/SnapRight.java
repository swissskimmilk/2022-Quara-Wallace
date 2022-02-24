package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IMU;
import java.lang.Math;
import java.text.DecimalFormat;

public class SnapRight extends CommandBase {

  private double exactInitAngle;
  private double newAngle; 

  private IMU subsysIMU;
  private Drivetrain drivetrain;
  private PIDController pid;
  private double angleTolerance;

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
    double initAngle = (RobotContainer.ADIS_IMU.getAngle() % 360 + 360) % 360;
    exactInitAngle = RobotContainer.ADIS_IMU.getAngle();
    System.out.println("Initial Angle: " + angleFormat.format(initAngle));

    // Find which angle (0, 90, 180, 270) robot is closest to
    if (initAngle > 270) {
      newAngle = exactInitAngle - (initAngle - 270);
    } else if (initAngle > 180) {
      newAngle = exactInitAngle - (initAngle - 180);
    } else if (initAngle > 90) {
      newAngle = exactInitAngle - (initAngle - 90);
    } else {
      newAngle = exactInitAngle - (initAngle);
    }

    System.out.println("Turning to: " + angleFormat.format(newAngle));
    System.out.println();

    double error = Math.abs(newAngle - exactInitAngle);

    // pid constants
    double kP = Math.abs(Constants.maxTurnPower / error);
    //double kP = 0.005;
    double kD = 0;
    double kI = kP / 200;

    //angleTolerance = 1.0 / error * 100.0;
    angleTolerance = 2;

    pid = new PIDController(kP, kI, kD);
    pid.setTolerance(angleTolerance);
    // pid.enableContinuousInput(0, 360);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // angle confined from 0 to 360 including negatives
    //double currAngle = (subsysIMU.getAngle() % 360 + 360) % 360;

    double currAngle = RobotContainer.ADIS_IMU.getAngle();
    
    // speed dependent on angle distance
    double movement = pid.calculate(currAngle, newAngle);
    System.out.println("NewAng: " + newAngle + " | " + "Curr: " + currAngle + " | " + "Movement: " + movement);
    RobotContainer.myRobot.arcadeDrive(-movement, 0, false);
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
