package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IMU;
import java.lang.Math;
import java.text.DecimalFormat;
import java.util.HashMap;

public class SnapRight extends CommandBase {

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
    double initAngle = (RobotContainer.ADIS_IMU.getAngle() % 360 + 360) % 360;

    System.out.println("Initial Angle: " + angleFormat.format(initAngle));

    // Find which angle (0, 90, 180, 270) robot is closest to
    // uses angleTolerance to create some leeway 
    // looks weird being for the imu, turning right is negative 
    /*
    if (initAngle < Constants.angleTolerance) {
      newAngle = 270;
    } else if (initAngle < (90 + Constants.angleTolerance)) {
      newAngle = 0;
    } else if (initAngle < (180 + Constants.angleTolerance)) {
      newAngle = 90;
    } else if (initAngle < (270 + Constants.angleTolerance)) {
      newAngle = 180;
    } else {
      newAngle = 270;
    }
    */

    if(initAngle - nearestAngle(initAngle) <= Constants.angleTolerance)
    {
      newAngle = nearestAngle(initAngle + Constants.angleTolerance + 1);
    }
    else{
      newAngle = nearestAngle(initAngle);
    }

    System.out.println("Snap Right At" + initAngle + " Going to: " + newAngle);

    System.out.println("Turning to: " + angleFormat.format(newAngle));
    System.out.println();

    double error = Math.abs(newAngle - initAngle);

    // pid constants
    double kP = Math.abs(Constants.maxTurnPower / error);
    double kD = 0.001;
    double kI = 0;

    pid = new PIDController(kP, kI, kD);
    pid.setTolerance(Constants.errorTolerance, Constants.speedTolerance);

    // pid treats 0 and 360 as the same point for calculationss
    pid.enableContinuousInput(0, 360);
  }

  static double nearestAngle(double angle)
  {
    if(angle > 270)
    {
      return 270.0;
    }
    else if(angle > 180)
    {
      return 180.0;
    }
    else if(angle > 90)
    {
      return 90.0;
    }
    else if(angle > 0)
    {
      return 360.0;
    }
    return 0;
  }

  /*
  static boolean shouldStop(double angle)
  {
    HashMap<Integer, Integer> distancesToaAngles = new HashMap<>();
    
    if(Math.abs(360 - angle) <= Constants.angleTolerance)
      return true;
    else if(Math.abs(270 - angle) <= Constants.angleTolerance)
      return true;double initAngle = (RobotContainer.ADIS_IMU.getAngle() % 360 + 360) % 360;
    
    
    return false;
  }
  */

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // angle confined from 0 to 360 including negatives
    double currAngle = (subsysIMU.getAngle() % 360 + 360) % 360;

    // double currAngle = RobotContainer.ADIS_IMU.getAngle();
    
    // speed dependent on angle distance
    double movement = pid.calculate(currAngle, newAngle);

    // add bounds on the speed to prevent it from going too fast or slow 
    movement = Math.signum(movement) * 
      MathUtil.clamp(Math.abs(movement), Constants.snapMinSpeed, Constants.snapMaxSpeed);

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
    if (pid.atSetpoint()) {
      return true;
    } 
    else {
      return false;
    }
  }
}
