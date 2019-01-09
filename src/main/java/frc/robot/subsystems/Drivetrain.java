/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public WPI_TalonSRX leftMotor0;
  public WPI_TalonSRX leftMotor1;

  public WPI_TalonSRX rightMotor0;
  public WPI_TalonSRX rightMotor1;

  public SpeedControllerGroup leftMotor;
  public SpeedControllerGroup rightMotor;

  public Drivetrain(){
    leftMotor0 = new WPI_TalonSRX(RobotMap.leftDriveMotor0);
    leftMotor1 = new WPI_TalonSRX(RobotMap.leftDriveMotor1);

    rightMotor0 = new WPI_TalonSRX(RobotMap.rightDriveMotor0);
    rightMotor1 = new WPI_TalonSRX(RobotMap.rightDriveMotor1);

    leftMotor = new SpeedControllerGroup(leftMotor0, leftMotor1);
    rightMotor = new SpeedControllerGroup(rightMotor0, rightMotor1);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDrive());
  }

}
