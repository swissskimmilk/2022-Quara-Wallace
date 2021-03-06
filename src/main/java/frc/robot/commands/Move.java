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

public class Move extends CommandBase {
  private double spdMult = Constants.defSpdMult;
  private double rotMult = Constants.detRotMult;
  private DriveMode driveMode = Constants.defDriveMode;
  private IMU subsysIMU;
  private Drivetrain drivetrain;

  public Move(Drivetrain mDrivetrain, IMU imu) {
    drivetrain = mDrivetrain;
    subsysIMU = imu;
    addRequirements(mDrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Don't move while calibrating is happening 
    if (subsysIMU.calibrating) {
      return;
    }
    
    // Switch speed by iterating over the map and checking if any of the buttons is pressed
    for (Map.Entry<Integer,Double> entry : Constants.speedMults.entrySet())
    {
      if (RobotContainer.joystick.getRawButtonPressed(entry.getKey()))
      {
        spdMult = entry.getValue();
        System.out.println("Speed mult changed to: " + spdMult);
      }
    }

    // Switch rotation speed by iterating over the map and checking if any of the buttons is pressed
    for (Map.Entry<Integer,Double> entry : Constants.rotMults.entrySet())
    {
      if (RobotContainer.joystick.getRawButtonPressed(entry.getKey()))
      {
        rotMult = entry.getValue();
        System.out.println("Rot mult changed to: " + rotMult);
      }
    }
    
    // Switch driveMode
    if (RobotContainer.xController.getAButtonPressed()) {
      if (driveMode == DriveMode.arcadeDrive) {
        driveMode = DriveMode.tankDrive;
        System.out.println("Switched to tankDrive");
      }
      else if (driveMode == DriveMode.tankDrive) {
        driveMode = DriveMode.arcadeDrive;
        System.out.println("Switched to arcadeDrive");
      }
    }

    // Calls a function to move the robot depending on the driveMode constant 
    if (driveMode == DriveMode.arcadeDrive) {
      double speed = RobotContainer.xController.getLeftY();
      double rotation = RobotContainer.xController.getLeftX();
      // shouldn't this be switched? speed, then rotation
      RobotContainer.myRobot.arcadeDrive(rotation * rotMult, spdMult * speed);
      //System.out.println(RobotContainer.ADIS_IMU.getAngle());    
      //System.out.println("leftX: " + RobotContainer.xController.getLeftX() + " | leftY: " + RobotContainer.xController.getLeftY() + " | rightX: " + RobotContainer.xController.getRightX() + " | " + RobotContainer.xController.getRightY());
    }
    else if (driveMode == DriveMode.tankDrive) {
      // Needs to be negative or else it goes backwards... 
      double left = RobotContainer.xController.getLeftY();
      double right = -RobotContainer.xController.getRightY();
      RobotContainer.myRobot.tankDrive(spdMult * left,spdMult * right);
    }
  }

  // Called once the command ends or is interrupted.
  public void end() {
    RobotContainer.myRobot.tankDrive(0, 0);
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}