package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class Shoot extends CommandBase{
    private Intake subSysIntake;
    private boolean shooting = false;
    private boolean finished = false;

    public Shoot(Intake mIntake)
    {
        addRequirements(mIntake);
        subSysIntake = mIntake;
    }

    @Override
    public void initialize() {
        RobotContainer.shooterGroup.set(Constants.shooterSpeed);

    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        // command ends when toggled instead
        return finished;
    }

    @Override 
    public void end(boolean interrupted) {
        System.out.println("stopped");
        RobotContainer.shooterGroup.stopMotor();
    }
}
