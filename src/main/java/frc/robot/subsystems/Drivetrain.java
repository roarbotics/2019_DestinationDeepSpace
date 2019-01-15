/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public WPI_TalonSRX leftMotor;
  public WPI_TalonSRX leftMotor1;

  public WPI_TalonSRX rightMotor;
  public WPI_TalonSRX rightMotor1;

  public Encoder leftEnc;
  public Encoder rightEnc;

  public Drivetrain() {
    leftMotor = new WPI_TalonSRX(RobotMap.leftDriveMotor0);
    leftMotor1 = new WPI_TalonSRX(RobotMap.leftDriveMotor1);
    leftMotor1.follow(leftMotor);

    rightMotor = new WPI_TalonSRX(RobotMap.rightDriveMotor0);
    rightMotor1 = new WPI_TalonSRX(RobotMap.rightDriveMotor1);
    rightMotor1.follow(rightMotor);



    leftEnc = new Encoder(RobotMap.leftEncoder0, RobotMap.leftEncoder1, false, Encoder.EncodingType.k4X);
    rightEnc = new Encoder(RobotMap.rightEncoder0, RobotMap.rightEncoder1, false, Encoder.EncodingType.k4X);

    leftEnc.setMaxPeriod(.1);
    leftEnc.setMinRate(10);
    leftEnc.setDistancePerPulse(5);
    leftEnc.setReverseDirection(true);
    leftEnc.setSamplesToAverage(7);

    rightEnc.setMaxPeriod(.1);
    rightEnc.setMinRate(10);
    rightEnc.setDistancePerPulse(5);
    rightEnc.setReverseDirection(true);
    rightEnc.setSamplesToAverage(7);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDrive());
  }

}
