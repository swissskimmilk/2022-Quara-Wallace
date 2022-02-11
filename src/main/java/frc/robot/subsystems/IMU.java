package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.ADIS16470_IMU;

public class IMU extends SubsystemBase {
  ADIS16470_IMU myIMU;
  
  // The gain for a simple P loop
  public double kP = 1;

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

  static public double addAngles(double ang1, double ang2) {
    double newAng = ang1 + ang2;
    if (newAng > 360) {
      newAng -= 360;
    }
    else if (newAng < 0) {
      newAng += 360;
    }
    return newAng;
  }
}
