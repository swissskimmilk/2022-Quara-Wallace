package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Drivetrain extends SubsystemBase {
  public Drivetrain() {}
 
  public void initialize() {
    RobotContainer.myRobot.tankDrive(0, 0);
    // RobotContainer.joystick.toggleWhenActive(new Move(), true);
  }

  // This method will be called once per scheduler run
  @Override
  public void periodic() {
    RobotContainer.move.execute();
  }
}
