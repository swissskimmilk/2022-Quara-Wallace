package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Autonomous extends SequentialCommandGroup {
  public Autonomous() {
    addCommands(
        new DriveForTime(RobotContainer.drivetrain, 1, true),
        
        new ExtendIntake(RobotContainer.mIntake),

        new Delay(Constants.extendTime),

        new Shoot(RobotContainer.mIntake),

        new Delay(1),

        new StopIntake(RobotContainer.mIntake),

        new DriveForTime(RobotContainer.drivetrain, 4, false));
  }
}