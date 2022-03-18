package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class RetractClimb extends CommandBase {
    Climber subSysClimb;
    
    public RetractClimb (Climber mClimb) {
        addRequirements(mClimb);
        subSysClimb = mClimb;
    }

    @Override
    public void initialize() {
        RobotContainer.climber.set(-Constants.climbSpeed);
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
