package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IMU;
import java.lang.Math;
import java.lang.Character.Subset;
import java.text.DecimalFormat;

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
    
    if (initAngle < Constants.angleTolerance) {
      newAngle = 270;
    } else if (initAngle < (90 + Constants.angleTolerance)) {
      newAngle = 0;
    } else if (initAngle < (180 + Constants.angleTolerance)) {
      newAngle = 90;
    } else if (initAngle < (270 + Constants.angleTolerance)) {
      newAngle = 180;
    } else {
<<<<<<< HEAD
      newAngle = 270;
=======
      newAngle = 360;
>>>>>>> 49d48f3369812a23a141bd0f8aeb56bf93bc659e
    }

    System.out.println("Snap Right At" + initAngle + " Going to: " + newAngle);

    System.out.println("Turning to: " + angleFormat.format(newAngle));
    System.out.println();

    double error = Math.abs(newAngle - initAngle);
    System.out.println(error);
    // pid constants
    double kP = Math.abs(Constants.maxTurnPower / error);
    double kD = 0.001;
    double kI = 0;

    pid = new PIDController(kP, kD, kI);
    pid.setTolerance(Constants.errorTolerance, Constants.speedTolerance);

    // pid treats 0 and 360 as the same point for calculationss
    pid.enableContinuousInput(0, 360);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currAngle = (subsysIMU.getAngle() % 360 + 360) % 360;

    double movement = pid.calculate(currAngle, newAngle);

    movement = Math.signum(movement) *
      MathUtil.clamp(Math.abs(movement), Constants.snapMinSpeed, Constants.snapMaxSpeed);

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
