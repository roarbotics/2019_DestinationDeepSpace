/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.WriteLights;
import frc.robot.util.Lightable;

/**
 * Add your docs here.
 */
public class Lights extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public ArrayList<Lightable> lightables = new ArrayList<>();

  public Lights(){

  }

  public void addLightable(Lightable l){
    lightables.add(l);
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new WriteLights());
  }
}
