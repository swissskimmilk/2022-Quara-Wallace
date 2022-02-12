package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IMU;
import java.text.DecimalFormat;

public class ResetAngle extends CommandBase {
    private IMU subsysIMU;
    private boolean isReset;

    public ResetAngle(IMU mIMU) {
      subsysIMU = mIMU;
      addRequirements(subsysIMU);
    }
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // get and round angle to print
        DecimalFormat angleFormat = new DecimalFormat("###.##");

        System.out.println("The angle " + angleFormat.format(RobotContainer.ADIS_IMU.getAngle()) + " is now set as zero");

        RobotContainer.ADIS_IMU.reset();
        isReset = true;
    }
    
    // Called once the command ends or is interrupted.
    public void end() {
        isReset = false;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (isReset) {
            return true;
        } else {
            return false;
        }
    }
}
