package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.ADIS16470_IMU;

public class IMU extends SubsystemBase {
  ADIS16470_IMU myIMU;
  
  // The gain for a simple P loop
  double kP = 1;

  public IMU() {
    myIMU = RobotContainer.ADIS_IMU;
  }
  
  public void initialize() {

  }

  // This method will be called once per scheduler run
  @Override
  public void periodic() {
  }
}
