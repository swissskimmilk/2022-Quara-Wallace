package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class Shoot extends CommandBase{
    private Intake subSysIntake;

    public Shoot(Intake mIntake)
    {
        addRequirements(mIntake);
        subSysIntake = mIntake;
    }

    @Override
    public void initialize() {
        RobotContainer.shooter.set(Constants.shooterSpeed);
    }

    public void excecute() {
        
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
