/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
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
import frc.robot.util.LightPacket;
import frc.robot.util.Lightable;

/**
 * Add your docs here.
 */
public class Pusher extends Subsystem implements Lightable{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public LightPacket lightPacket;

  public DoubleSolenoid clawSolenoid;
  public Compressor k_compressor;
  public AnalogInput s_pressure;

  public Pusher(){
    lightPacket = new LightPacket(RobotMap.leftCylinderLightStart, RobotMap.rightCylinderLightStart+7);

    k_compressor = new Compressor();
    s_pressure = new AnalogInput(RobotMap.pressureSensor);
    clawSolenoid = new DoubleSolenoid(RobotMap.clawOpen, RobotMap.clawClose);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public double getPressure(){
    return (250 * (s_pressure.getVoltage() / 5)) - 25;
  }

  @Override
  public LightPacket getPacket() {
    return lightPacket;
}
}
