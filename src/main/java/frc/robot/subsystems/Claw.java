/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.DeployClaw;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Claw extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public DoubleSolenoid clawSolenoid;

  public Compressor k_compressor;

  public AnalogInput s_pressure;

  public Claw(){
    k_compressor = new Compressor();
    s_pressure = new AnalogInput(RobotMap.pressureSensor);
    clawSolenoid = new DoubleSolenoid(RobotMap.clawOpen, RobotMap.clawClose);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(null);
  }

}
