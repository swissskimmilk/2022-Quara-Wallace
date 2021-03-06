package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class ContractIntake extends CommandBase {
    private Intake subSysIntake;

    public ContractIntake(Intake intake){
        subSysIntake = intake;
        addRequirements(intake);
    }
    
    @Override
    public void initialize() {
        // RobotContainer.leftActuator.set(0);
        // RobotContainer.rightActuator.set(0);    
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
