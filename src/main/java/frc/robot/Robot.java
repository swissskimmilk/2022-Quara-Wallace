// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// Imports subsystems and commands 
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.ADIS16470_IMU;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    
    // Create controller objects 
    RobotContainer.xController = new XboxController(Constants.xboxController);
    RobotContainer.joystick = new Joystick(Constants.joystick);

    // buttons
    RobotContainer.xButtonLeftTurn = new JoystickButton(RobotContainer.xController, Constants.leftTurnButton);
    RobotContainer.xButtonRightTurn = new JoystickButton(RobotContainer.xController, Constants.rightTurnButton);
    RobotContainer.xButtonSnapRight = new JoystickButton(RobotContainer.xController, Constants.snapRightButton);
    RobotContainer.xButtonSnapLeft = new JoystickButton(RobotContainer.xController, Constants.snapLeftButton);
    RobotContainer.xButtonCalibrate = new JoystickButton(RobotContainer.xController, Constants.calibrateButton);
    RobotContainer.xButtonResetAngle = new JoystickButton(RobotContainer.xController, Constants.resetAngleButton);
    
    // imu subsystem
    RobotContainer.IMU = new IMU();
    
    // the actual imu 
    RobotContainer.ADIS_IMU = new ADIS16470_IMU();

    // Create move system 
    RobotContainer.drivetrain = new Drivetrain();
    RobotContainer.move = new Move(RobotContainer.drivetrain, RobotContainer.IMU);
    RobotContainer.drivetrain.initDefaultCommand(RobotContainer.move);
    RobotContainer.leftTurn = new LeftTurn(RobotContainer.drivetrain, RobotContainer.IMU);
    RobotContainer.rightTurn = new RightTurn(RobotContainer.drivetrain, RobotContainer.IMU);
    RobotContainer.snapRight = new SnapRight(RobotContainer.drivetrain, RobotContainer.IMU);
    RobotContainer.snapLeft = new SnapLeft(RobotContainer.drivetrain, RobotContainer.IMU);
    RobotContainer.calibrate = new Calibrate(RobotContainer.IMU);
    RobotContainer.resetAngle = new ResetAngle(RobotContainer.IMU);

    // Create motor objects 
    RobotContainer.rightLeader = new WPI_VictorSPX(Constants.RightLeader);
    RobotContainer.leftLeader = new WPI_VictorSPX(Constants.LeftLeader);
    RobotContainer.rightFollower = new WPI_VictorSPX(Constants.RightFollower);
    RobotContainer.leftFollower = new WPI_VictorSPX(Constants.LeftFollower);
    
    // Create groups and diffDrive 
    RobotContainer.rightGroup = new MotorControllerGroup(RobotContainer.rightLeader, RobotContainer.rightFollower);
    RobotContainer.leftGroup = new MotorControllerGroup(RobotContainer.leftLeader, RobotContainer.leftFollower);
    RobotContainer.myRobot = new DifferentialDrive(RobotContainer.leftGroup, RobotContainer.rightGroup);

    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  // @Override
  // public void autonomousInit() {
  //   m_autonomousCommand = m_robotContainer.getAutonomousCommand();

  //   // schedule the autonomous command (example)
  //   if (m_autonomousCommand != null) {
  //     m_autonomousCommand.schedule();
  //   }
  // }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
