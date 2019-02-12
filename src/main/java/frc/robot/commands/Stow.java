/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Actuator;

/**
 * An example command. You can replace me with your own command.
 */
public class Stow extends Command {

  public Stow() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_actuator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.m_actuator.leftDistance.getValue() > Actuator.LEFT_MIN)
      Robot.m_actuator.leftActuator.set(-1);
    if (Robot.m_actuator.rightDistance.getValue() > Actuator.RIGHT_MIN)
      Robot.m_actuator.rightActuator.set(-1);

    Robot.m_actuator.lightPacket.setColor(255, 0, 255);
    Robot.m_actuator.lightPacket.setVelocity(-1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return ((Robot.m_actuator.rightDistance.getValue() < Actuator.RIGHT_MIN)
        && (Robot.m_actuator.leftDistance.getValue() < Actuator.LEFT_MAX));
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_actuator.leftActuator.set(0);
    Robot.m_actuator.rightActuator.set(0);

    Robot.m_actuator.lightPacket.setColor(255, 0, 0);
    Robot.m_actuator.lightPacket.setVelocity(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
