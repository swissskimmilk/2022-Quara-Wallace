package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.ADIS16470_IMU;

public class IMU extends SubsystemBase {
  private ADIS16470_IMU myIMU;
  public boolean calibrating = false;
  
  // Set what the 0 angle should be, since it varies at startup 
  public double startAngle = 0;

  public IMU() {
    myIMU = RobotContainer.ADIS_IMU;
  }
  
  public void initialize() {
  }

  // This method will be called once per scheduler run
  @Override
  public void periodic() {
  }

  public double getAngle() {
    return myIMU.getAngle();
  }
}
