package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IMU;

public class Calibrate extends CommandBase {
    private IMU subsysIMU;
    private boolean finished = false;

    public Calibrate(IMU mIMU, Drivetrain mDriveTrain) {
        subsysIMU = mIMU;
        addRequirements(subsysIMU, mDriveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        RobotContainer.ADIS_IMU.calibrate();
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    finished = true;
                }
            }, 
            4000 
        );
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }
    
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (finished) {
            subsysIMU.calibrating = false;
            return true;
        }
        return false;
    }
}
