package frc.robot;

import java.util.HashMap;
import java.util.Map;

// Stores constants (duh)
public final class Constants {
    // The ids for the motors 
    public static final int RightLeader = 0;
    public static final int RightFollower = 3;
    public static final int LeftLeader = 1;
    public static final int LeftFollower = 4;

    // The id for the joystick 
    public static final int joystick = 0;

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
        speedMults.put(8, 0.4);
        speedMults.put(10, 0.5);
        speedMults.put(12, 0.6);
    }
    public static final Map<Integer, Double> rotMults = new HashMap<>();
    static {
        speedMults.put(7, -0.4);
        speedMults.put(9, -0.5);
        speedMults.put(10, -0.6);
    }
    
    public static final double defSpdMult = 0.5;
    public static final double detRotMult = -0.5;
}
