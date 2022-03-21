package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class Delay extends CommandBase {
  private double m_Time;
  private Timer m_Timer;

  public Delay(double time) {
    m_Time = time;
    m_Timer = new Timer();
    m_Timer.start();
  }

  @Override
  public void execute() {
  }

  @Override
  public boolean isFinished() {
    return m_Timer.hasElapsed(m_Time);
  }
}