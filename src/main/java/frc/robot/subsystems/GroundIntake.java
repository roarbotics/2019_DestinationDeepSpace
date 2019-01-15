/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class GroundIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public DoubleSolenoid left;
  public DoubleSolenoid right;

  public Spark wheels;

  public double speed = 1;

  public GroundIntake(){
    left = new DoubleSolenoid(RobotMap.leftIntakeCylinderIn, RobotMap.leftIntakeCylinderOut);
    right = new DoubleSolenoid(RobotMap.rightIntakeCylinderIn, RobotMap.rightIntakeCylinderOut);

    wheels = new Spark(RobotMap.intakeMotor);

  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(null);
  }

}
