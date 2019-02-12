/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.Deploy;
import frc.robot.subsystems.Actuator;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Lights;
import frc.robot.subsystems.Pusher;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Drivetrain m_drivetrain = new Drivetrain();
  public static Actuator m_actuator = new Actuator();
  public static Pusher m_pusher = new Pusher();
  public static OI m_oi;
  public static Lights m_lights = new Lights();

  public static AHRS ahrs;
  BuiltInAccelerometer builtInAccelerometer;

  NetworkTableEntry angleMXPEntry;

  NetworkTableEntry xRioEntry;
  NetworkTableEntry yRioEntry;

  NetworkTableEntry leftEncoderEntry;
  NetworkTableEntry rightEncoderEntry;

  public static PowerDistributionPanel k_pdp = new PowerDistributionPanel(5);

  public double getPressure() {
    return (250 * (m_pusher.s_pressure.getVoltage() / 5)) - 125;
  }

  Command m_autonomousCommand;
  SendableChooser<Command> m_visionChoice = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();

    //UsbCamera camera = new UsbCamera("cam0",0);
    //camera.setFPS(15);
    //camera.setResolution(320, 240);

    //CameraServer.getInstance().startAutomaticCapture(camera);
    CameraServer.getInstance().startAutomaticCapture();

    builtInAccelerometer = new BuiltInAccelerometer();

    try {
      ahrs = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException e) {
      DriverStation.reportError("MXP error: " + e.getMessage(), true);
    }

    ahrs.resetDisplacement();

    /**
     * Set up the NetworkTable and map all of the keys to the table.
     */
    NetworkTableInstance inst = NetworkTableInstance.getDefault();

    NetworkTable table = inst.getTable("datatable");
    angleMXPEntry = table.getEntry("angleMXP");
    leftEncoderEntry = table.getEntry("leftEnc");
    rightEncoderEntry = table.getEntry("rightEnc");
    xRioEntry = table.getEntry("xRio");
    yRioEntry = table.getEntry("yRio");

    // m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    //m_visionChoice.addOption("Processed", new ProcessCamera());
    // m_visionChoice.setDefaultOption("Default", new ViewCamera());
    SmartDashboard.putData("Vision Option", m_visionChoice);

    System.out.println("MXP Firmware Version " + ahrs.getFirmwareVersion());
    System.out.println("\n\nINIT COMPLETE\n\n");
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // SmartDashboard.putNumber("Stored Pressure", getPressure());
    SmartDashboard.putNumber("Voltage", k_pdp.getVoltage());
    SmartDashboard.putNumber("Total Current", k_pdp.getTotalCurrent());

    SmartDashboard.putNumber("Pressure", m_pusher.getPressure());

    if (ArcadeDrive.drive != null)
      SmartDashboard.putData("Drivetrain", ArcadeDrive.drive);

    if (m_oi.stick.getRawButton(3)){
      m_drivetrain.leftEnc.reset();
      m_drivetrain.rightEnc.reset();
    }

    /**
     * Add all of the data to the network table.
     */

    if (ahrs != null) {
      angleMXPEntry.setDouble(ahrs.getAngle());
      SmartDashboard.putData("Gyro", ahrs);
    }

    /*
     * if (frcAccel != null) { xGyroEntry.setDouble(frcAccel.getX());
     * yGyroEntry.setDouble(frcAccel.getY()); } if (frcGyro != null) {
     * angleGyroEntry.setDouble(frcGyro.getAngle()); }
     */

    if (builtInAccelerometer != null) {
      xRioEntry.setDouble(builtInAccelerometer.getX());
      yRioEntry.setDouble(builtInAccelerometer.getY());
    }

    leftEncoderEntry.setDouble(m_drivetrain.leftEnc.getDistance());
    rightEncoderEntry.setDouble(m_drivetrain.rightEnc.getDistance());

  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
    //m_visionChoice.getSelected().start();
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    //m_visionChoice.getSelected().start();
    // m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    m_autonomousCommand = new Deploy();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    //m_visionChoice.getSelected().start();
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
