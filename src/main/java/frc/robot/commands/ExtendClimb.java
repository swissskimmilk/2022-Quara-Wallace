package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class ExtendClimb extends CommandBase {
    Climb subSysClimb;
    
    public ExtendClimb (Climb mClimb) {
        addRequirements(mClimb);
        subSysClimb = mClimb;
    }
}
