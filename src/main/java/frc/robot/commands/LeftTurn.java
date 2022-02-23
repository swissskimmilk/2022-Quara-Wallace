package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IMU;
import java.text.DecimalFormat;

public class LeftTurn extends CommandBase {
  private Drivetrain drivetrain;
  private IMU subsysIMU;
  private double newAngle;
  private PIDController pid;
  
  public LeftTurn(Drivetrain mDrivetrain, IMU mIMU) {
    drivetrain = mDrivetrain;
    subsysIMU = mIMU;
    addRequirements(drivetrain, subsysIMU);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DecimalFormat angleFormat = new DecimalFormat("###.##");
    
    System.out.println("Turning Left");
    double currAngle = RobotContainer.ADIS_IMU.getAngle();
    System.out.println("Initial Angle: " + angleFormat.format(currAngle));
    newAngle = currAngle + 90;
    System.out.println("Turning to: " + angleFormat.format(newAngle));
    System.out.println();
    
    // pid controller for robot speed values
    pid = new PIDController(Constants.NINETY_kP, Constants.NINETY_kI, Constants.NINETY_kD);
    pid.setTolerance(Constants.errorTolerance);
    // pid.enableContinuousInput(0, 360);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currAngle = RobotContainer.ADIS_IMU.getAngle();
    RobotContainer.myRobot.arcadeDrive(-pid.calculate(currAngle, newAngle), 0);
  }

  // Called once the command ends or is interrupted.
  public void end() {
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (pid.atSetpoint()) {
      return true;
    } 
    else {
      return false;
    }
  }
}
