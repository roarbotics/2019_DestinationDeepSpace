/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Actuator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public DoubleSolenoid clawSolenoid;

  public Compressor k_compressor;

  public AnalogInput s_pressure;
  public AnalogInput leftDistance;
  public AnalogInput rightDistance;

  public WPI_TalonSRX leftActuator;
  public WPI_TalonSRX rightActuator;

  public static int LEFT_MAX = 3000;
  public static int RIGHT_MAX = 3000;

  public static int LEFT_MIN = 500;
  public static int RIGHT_MIN = 500;

  public Actuator(){
    k_compressor = new Compressor();
    s_pressure = new AnalogInput(RobotMap.pressureSensor);
    clawSolenoid = new DoubleSolenoid(RobotMap.clawOpen, RobotMap.clawClose);

    leftActuator = new WPI_TalonSRX(RobotMap.leftActuator);
    rightActuator = new WPI_TalonSRX(RobotMap.rightActuator);

    leftDistance = new AnalogInput(RobotMap.leftPot);
    rightDistance = new AnalogInput(RobotMap.rightPot);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(null);
  }

  public double getPressure(){
    return (250*(s_pressure.getVoltage()/5))-25;
  }

}
