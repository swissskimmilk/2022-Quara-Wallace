package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Climber;

public class StopIntake extends CommandBase{
    private Intake subSysIntake;
    private Climber m_Climber;
    private boolean shooting = false;
    private boolean finished = false;

    public StopIntake(Intake mIntake, Climber climber)
    {
        subSysIntake = mIntake;
        m_Climber = climber;
        addRequirements(subSysIntake, m_Climber);
    }

    @Override
    public void initialize() {
        // RobotContainer.topShooter.stopMotor();
        // RobotContainer.bottomShooter.stopMotor();
        RobotContainer.climberMotor.stopMotor();
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        // command ends when toggled instead
        return true;
    }
}
