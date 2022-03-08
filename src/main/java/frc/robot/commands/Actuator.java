package frc.robot.commands;

import frc.robot.subsystems.Intake;

public class Actuator extends Intake {
    public Actuator(){

    }
    
    public void execute () {
        Intake.actuator.set(0);
    }
}
