package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class ExtendIntake extends CommandBase {
    private Intake subSysIntake;
    
    public ExtendIntake(Intake mIntake) {
        addRequirements(mIntake);
        subSysIntake = mIntake;
    }

    @Override
    public void initialize() {
        RobotContainer.leftActuator.set(1);
        RobotContainer.rightActuator.set(1);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
