/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  public static int leftDriveMotor0 = 1;
  public static int leftDriveMotor1 = 3;
  public static int rightDriveMotor0 = 0;
  public static int rightDriveMotor1 = 2;

  public static int leftEncoder0 = 0;
  public static int leftEncoder1 = 1;

  public static int rightEncoder0 = 2;
  public static int rightEncoder1 = 3;

  public static int liftEncoder0 = 4;
  public static int liftEncoder1 = 5;

  public static int intakeMotor = 2;

  public static int leftIntakeCylinderIn = 0;
  public static int leftIntakeCylinderOut = 1;
  public static int rightIntakeCylinderIn = 2;
  public static int rightIntakeCylinderOut = 3;

  public static int liftMotor = 3;

  public static int pressureSensor = 0;

  public static int lowerLiftLimit = 6;
  public static int upperLiftLimit = 7;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
