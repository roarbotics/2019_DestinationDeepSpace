/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.LightPacket;
import frc.robot.util.Lightable;

public class WriteLights extends Command {

  I2C wire;

  public WriteLights() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_lights);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    wire = new I2C(Port.kOnboard, 4);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    String dat = "";
    for (Lightable light : Robot.m_lights.lightables) {
      for (LightPacket packet : light.getPackets()) {
        dat += packet.getPos() + ",";
        dat += packet.getColorDat() + ",";
        dat += packet.getVelocity();
        write(dat);
        dat = "";
      }
    }
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

  private void write(String s) {
    char[] CharArray = s.toCharArray();
    byte[] WriteData = new byte[CharArray.length];
    for (int i = 0; i < CharArray.length; i++) {
      WriteData[i] = (byte) CharArray[i];
    }
    wire.transaction(WriteData, WriteData.length, null, 0);
  }
}
