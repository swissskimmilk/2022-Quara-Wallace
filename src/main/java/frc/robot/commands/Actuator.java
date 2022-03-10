package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class Actuator extends CommandBase {
    private Intake subSysIntake;
    private boolean extend = false;

    public Actuator(Intake intake){
        subSysIntake = intake;
        addRequirements(intake);
    }
    
    @Override
    public void initialize() {
        RobotContainer.leftActuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
        RobotContainer.rightActuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);

        double position = RobotContainer.leftActuator.get();
        System.out.println(position);
        // Linear actuator needs to go up.
        if (position < 0.82)
        {
            extend = true;
        }

    }

    public void execute() {
        
        // Linear actuator needs to go down.
        // if (extend)
        // {
        //     RobotContainer.leftActuator.setSpeed(1);
        //     RobotContainer.rightActuator.setSpeed(1);
        // } else {
            RobotContainer.leftActuator.set(1);
            RobotContainer.rightActuator.set(1);
        // }
    }

    @Override
    public boolean isFinished() {
        double position = RobotContainer.leftActuator.get();
        
        if (position == 1) {
            return true;
        } else {
            return false;
        }
        // else if (position > 0 && !extend)
        // {
        //     return false;
        // } else {
        //     return false;
        // }
    }
}
