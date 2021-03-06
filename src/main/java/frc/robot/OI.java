/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.LowerIntake;
import frc.robot.commands.RaiseIntake;
import frc.robot.commands.ReleaseIntake;
import frc.robot.commands.SuckIntake;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  public Joystick stick = new Joystick(0);

  public int moveAxis = 0;
  public int rotateAxis = 1;

  Button raiseIntake = new JoystickButton(stick, 1);
  Button lowerIntake = new JoystickButton(stick, 2);
  Button intakeForwards = new JoystickButton(stick, 3);
  Button intakeReverse = new JoystickButton(stick, 4);

  public OI(){
    raiseIntake.whenPressed(new RaiseIntake());
    lowerIntake.whenPressed(new LowerIntake());

    intakeForwards.whileHeld(new SuckIntake());
    intakeReverse.whileHeld(new ReleaseIntake());
  }


  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

}
