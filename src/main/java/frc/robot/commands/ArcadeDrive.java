/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;

/**
 * An example command. You can replace me with your own command.
 */
public class ArcadeDrive extends Command {

  DifferentialDrive drive;

  public ArcadeDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_drivetrain.rightMotor.setInverted(true);
    drive = new DifferentialDrive(Robot.m_drivetrain.leftMotor, Robot.m_drivetrain.rightMotor);
    drive.setRightSideInverted(true);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    drive.arcadeDrive(-Robot.m_oi.stick.getRawAxis(1), Robot.m_oi.stick.getRawAxis(2));
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
