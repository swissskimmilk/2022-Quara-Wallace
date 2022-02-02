package frc.robot;

// Imports subsystems and commands 
import frc.robot.subsystems.*;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
//This class is where the bulk of the robot should be declared.
public class RobotContainer {

  // The controllers 
  public static Joystick joystick;
  public static XboxController xController;

  // Movement system
  public static Drivetrain drivetrain;
  public static Move move;

  // The motors 
  public static WPI_VictorSPX rightLeader;
  public static WPI_VictorSPX rightFollower;
  public static WPI_VictorSPX leftLeader;
  public static WPI_VictorSPX leftFollower;

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
  private void configureButtonBindings() {}

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
