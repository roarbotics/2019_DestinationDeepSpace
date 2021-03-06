/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Lift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Spark liftMotor;

  public double speed = 1;

  public DigitalInput lowerLimit;
  public DigitalInput upperLimit;

  public Encoder liftEncoder;

  public Lift(){
    liftMotor = new Spark(RobotMap.liftMotor);

    lowerLimit = new DigitalInput(RobotMap.lowerLiftLimit);
    upperLimit = new DigitalInput(RobotMap.upperLiftLimit);

    liftEncoder = new Encoder(RobotMap.liftEncoder0, RobotMap.liftEncoder1, false, Encoder.EncodingType.k4X);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(null);
  }

}
