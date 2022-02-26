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
    double initAngleRemainder = (RobotContainer.ADIS_IMU.getAngle() % 360 + 360) % 360;

    // the angle straight from the imu 
    exactInitAngle = RobotContainer.ADIS_IMU.getAngle();

    System.out.println("Initial Angle: " + angleFormat.format(initAngleRemainder));

    // Find which angle (0, 90, 180, 270) robot is closest to
    // uses angleTolerance to create some leeway 
    // looks weird being for the imu, turning right is negative 
    if (initAngleRemainder > 270) {
      newAngle = 270;
    }
    else if (initAngleRemainder > 180) {
      newAngle = 180;
    } 
    else if (initAngleRemainder > 90) {
      newAngle = 90;
    } 
    else {
      newAngle = 0;
    }

    System.out.println("Turning to: " + angleFormat.format(newAngle));
    System.out.println();

    double error = Math.abs(newAngle - initAngleRemainder);

    // pid constants
    double kP = Math.abs(Constants.maxTurnPower / error);
    double kD = 0.001;
    double kI = 0;

    pid = new PIDController(kP, kI, kD);
    pid.setTolerance(Constants.errorTolerance);
    pid.enableContinuousInput(0, 360);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // angle confined from 0 to 360 including negatives
    double currAngle = (subsysIMU.getAngle() % 360 + 360) % 360;

    // double currAngle = RobotContainer.ADIS_IMU.getAngle();
    
    // speed dependent on angle distance
    double movement = pid.calculate(currAngle, newAngle);

    // add bounds on the speed to prevent it from going too fast or slow 
    if (Math.abs(movement) < Constants.snapMinSpeed && movement < 0) {
      movement = -Constants.snapMinSpeed;
    }
    else if (Math.abs(movement) < Constants.snapMinSpeed) {
      movement = Constants.snapMinSpeed;
    }
    else if (Math.abs(movement) > Constants.snapMaxSpeed && movement < 0) {
      movement = -Constants.snapMaxSpeed;
    }
    else if (Math.abs(movement) > Constants.snapMaxSpeed) {
      movement = Constants.snapMaxSpeed;
    }

    // System.out.println("NewAng: " + newAngle + " | " + "Curr: " + currAngle + " | " + "Movement: " + movement);
    // needs to be negative because of the IMU 
    RobotContainer.myRobot.arcadeDrive(-movement, 0, false);
  }

  // Called once the command ends or is interrupted.
  public void end() {
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // end if the angle is approximately the desired one and velocity is low enough 
    if (pid.atSetpoint() && Math.abs(RobotContainer.ADIS_IMU.getRate()) < Constants.maxVelocity) {
      return true;
    } 
    else {
      return false;
    }
  }
}
