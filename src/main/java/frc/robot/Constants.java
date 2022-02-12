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
        public static final int leftTurnButton = 5;
        public static final int rightTurnButton = 6;

        // left/right stick click to snap turn left and right
        public static final int snapLeftButton = 9;
        public static final int snapRightButton = 10;

        // y button, to calibrate imu
        public static final int calibrateButton = 4;

        // x button, to reset/recalibrate imu angle
        public static final int resetAngleButton = 3;


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
    
    public static final double defSpdMult = -0.75;
    public static final double detRotMult = 0.6;

    // automatic turning speed multiplier for 90 degree turns
    public static final double autoTurnMult = 1 / 90;

    // angle tolerance for automatic turns
    public static final double angleTolerance = 2;
}
