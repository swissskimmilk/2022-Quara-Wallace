package frc.robot;

// Imports subsystems and commands 
import frc.robot.subsystems.*;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

//This class is where the bulk of the robot should be declared.
public class RobotContainer {

  // The controllers 
  public static Joystick joystick;
  public static XboxController xController;

  // buttons for bindings
  public static JoystickButton xButtonLeftTurn;
  public static JoystickButton xButtonRightTurn;
  public static JoystickButton xButtonSnapRight;
  public static JoystickButton xButtonSnapLeft;
  //public static JoystickButton xButtonCalibrate;
  public static JoystickButton xButtonStopIntake;
  public static JoystickButton xButtonResetAngle;

  // public static JoystickButton jButtonContractIntake;
  // public static JoystickButton jButtonExtendIntake;
  // public static JoystickButton jButtonShoot;
  // public static JoystickButton jButtonSuck;
  public static JoystickButton jButtonExtendClimb; 
  public static JoystickButton jButtonRetractClimb; 

  // imu subsystem
  public static IMU IMU;

  // actual imu
  public static ADIS16470_IMU ADIS_IMU;

  // Movement system
  public static Drivetrain drivetrain;
  public static Move move;
  public static LeftTurn leftTurn;
  public static RightTurn rightTurn;
  public static SnapRight snapRight;
  public static SnapLeft snapLeft;
  public static Calibrate calibrate;
  public static ResetAngle resetAngle;

  // intake 
  public static Intake mIntake;
  // public static ExtendIntake extendIntake;
  // public static ContractIntake contractIntake;
  // public static Servo leftActuator;
  // public static Servo rightActuator;
  // public static Shoot shoot;
  // public static Suck suck;
  public static StopIntake stopIntake;

  // climb
  public static Climber climber;
  public static ExtendClimb extendClimb;
  public static RetractClimb retractClimb;

  // The motors 
  public static WPI_VictorSPX rightLeader;
  public static WPI_VictorSPX rightFollower;
  public static WPI_VictorSPX leftLeader;
  public static WPI_VictorSPX leftFollower;
  // public static WPI_VictorSPX topShooter;
  // public static WPI_VictorSPX bottomShooter;
  public static WPI_VictorSPX climberMotor;

  // The groups for the motors 
  public static MotorControllerGroup rightGroup;
  public static MotorControllerGroup leftGroup; 

  // DifferentialDrive is what actually performs the movements 
  public static DifferentialDrive myRobot;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //xButtonLeftTurn.whenPressed(leftTurn);
    //xButtonRightTurn.whenPressed(rightTurn);
    //xButtonSnapRight.whenPressed(snapRight);
    //xButtonSnapLeft.whenPressed(snapLeft);
    //xButtonCalibrate.whenPressed(calibrate);
    xButtonStopIntake.whenPressed(stopIntake);
    xButtonResetAngle.whenPressed(resetAngle);
  
    // jButtonContractIntake.whenPressed(contractIntake);
    // jButtonExtendIntake.whenPressed(extendIntake);
    jButtonExtendClimb.whenPressed(extendClimb);
    jButtonRetractClimb.whenPressed(retractClimb);
    
    // jButtonShoot.whenPressed(shoot);
    // jButtonSuck.whenPressed(suck);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }
}
