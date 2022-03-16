package frc.robot;

import java.util.HashMap;
import java.util.Map;
import edu.wpi.first.wpilibj.XboxController;

// Stores constants (duh)
public final class Constants {
    // The ids for the motors 
    public static final int RightLeader = 0;
    public static final int RightFollower = 3;
    public static final int LeftLeader = 1;
    public static final int LeftFollower = 4;

    // The id for the joystick 
    public static final int joystick = 0;

    // joystick button ids:

        // left and right bumper
        public static final int leftTurnButton = 3;
        public static final int rightTurnButton = 2;

        // left/right stick click to snap turn left and right
        public static final int snapLeftButton = 9;
        public static final int snapRightButton = 10;

        // y button, to calibrate imu
        public static final int calibrateButton = 4;

        // start button, to reset/recalibrate imu angle
        public static final int resetAngleButton = 8;

        // button to contract linear actuator. 5 button on the joystick
        public static final int contractButton = 5;

        // extend linear actuator, 6 on joystick
        public static final int extendButton = 6;

        // Shoot button (spin wheels)
        public static final int shootButton = 3;

        // climb button
        public static final int climbButton = 4;


    // The id for the XboxController
    public static final int xboxController = 1;
    
    // Constants for movement  
    public enum DriveMode {
        tankDrive,
        arcadeDrive;
    }
    // WPI removed the functionality to change between joysticks with a constant 
    // Determines which DriveMode is the default on startup 
    public static final DriveMode defDriveMode = DriveMode.arcadeDrive;

    // Key is the button and the value is the mult 
    public static final Map<Integer, Double> speedMults = new HashMap<>();
    static {
        speedMults.put(8, -0.5);
        speedMults.put(10, -0.75);
        speedMults.put(12, -0.9);
    }
    public static final Map<Integer, Double> rotMults = new HashMap<>();
    static {
        rotMults.put(7, 0.5);
        rotMults.put(9, 0.6);
        rotMults.put(11, 0.7);
    }
    
    // default movement mults 
    public static final double defSpdMult = -0.75;
    public static final double detRotMult = 0.6;

    // automatic turning speed multiplier for 90 degree turns
    public static final double autoTurnMult = 0.01;

    // angle tolerance for auto 90 turns
    public static final double errorTolerance = 2;

    // for snap turning, determines whether 265 should snap to 270 or 360 etc
    public static final double angleTolerance = 10;

    // max turning power when auto turn, might need change since motors have exponential scale
    public static final double maxTurnPower = 1;

    // max velocity that the robot can be moving when it stops 
    public static final double speedTolerance = 50;

    // max/min speed while snap turning 
    public static final double snapMaxSpeed = 0.5;
    public static final double snapMinSpeed = 0.18;

    // min ninety turn speed (exp input)
    public static final double minNinetySpeed = 0.35;

    // pid values for 90 degree turns
    public static final double NINETY_kP = 0.01;
    public static final double NINETY_kI = 0;
    public static final double NINETY_kD = 0;

    // Actuator Ports
    public static final int leftActuatorPort = 0;
    public static final int rightActuatorPort = 1;

    // Shoot ports
    public static final int frontShootMotorPort = 15;
    public static final int backShooterMotorPort = 2;


    // Shooting speed
    public static final double shooterSpeed = 0.5;

    // Climb port

    public static final int climbPort = 4;




}
