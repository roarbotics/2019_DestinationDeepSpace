/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Retract extends Command {
  public Retract() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_pusher);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_pusher.clawSolenoid.set(DoubleSolenoid.Value.kReverse);

    Robot.m_pusher.lightPackets[0].setColor(0, 255, 0);
    Robot.m_pusher.lightPackets[0].setVelocity(0);

    Robot.m_pusher.lightPackets[1].setColor(0, 255, 0);
    Robot.m_pusher.lightPackets[1].setVelocity(0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
