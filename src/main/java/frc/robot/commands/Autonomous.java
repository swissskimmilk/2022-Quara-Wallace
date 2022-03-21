package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Autonomous extends SequentialCommandGroup {
  public Autonomous() {
    addCommands(
        //new DriveForTime(RobotContainer.drivetrain, 1, true),

        // new Shoot(RobotContainer.mIntake),

        // new Delay(3),

        // new StopIntake(RobotContainer.mIntake, RobotContainer.climber),

        new DriveForTime(RobotContainer.drivetrain, 3, false));
  }
}