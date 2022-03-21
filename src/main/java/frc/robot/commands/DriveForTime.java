package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;

public class DriveForTime extends CommandBase {
  private final Drivetrain m_Drivetrain;
  private double m_Time;
  private Timer m_Timer;
  private boolean m_Forward;

  public DriveForTime(Drivetrain drivetrain, double time, boolean forward) {
    m_Drivetrain = drivetrain;
    m_Time = time;
    m_Timer = new Timer();
    m_Timer.start();
    m_Forward = forward;
    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    if (m_Forward) {
        RobotContainer.myRobot.arcadeDrive(0, Constants.autonSpeed);
    }
    else {
      System.out.println("Driving backwardsw at " + -Constants.autonSpeed);
        RobotContainer.myRobot.arcadeDrive(0, -Constants.autonSpeed);
    }
  }

  @Override
  public boolean isFinished() {
    System.out.println("Time " + m_Timer.get());
    return m_Timer.hasElapsed(m_Time);
  }
}