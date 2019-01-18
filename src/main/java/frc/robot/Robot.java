/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ProcessCamera;
import frc.robot.commands.ViewCamera;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.GroundIntake;
import frc.robot.subsystems.Lift;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static Drivetrain m_drivetrain = new Drivetrain();
  public static GroundIntake m_groundintake = new GroundIntake();
  public static Camera m_camera = new Camera();
  public static Lift m_lift = new Lift();
  public static OI m_oi;

  ADXRS450_Gyro frcGyro;
  ADXL362 frcAccel;
  AHRS ahrs;
  BuiltInAccelerometer builtInAccelerometer;

  NetworkTableEntry xMXPEntry;
  NetworkTableEntry yMXPEntry;
  NetworkTableEntry angleMXPEntry;

  NetworkTableEntry xGyroEntry;
  NetworkTableEntry yGyroEntry;
  NetworkTableEntry angleGyroEntry;

  NetworkTableEntry xRioEntry;
  NetworkTableEntry yRioEntry;

  NetworkTableEntry leftEncoderEntry;
  NetworkTableEntry rightEncoderEntry;

  public static PowerDistributionPanel k_pdp = new PowerDistributionPanel(5);

  public static AnalogInput s_pressure = new AnalogInput(RobotMap.pressureSensor);

  public double getPressure() {
    return (250 * (s_pressure.getVoltage() / 5)) - 125;
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
    xMXPEntry = table.getEntry("xMXP");
    yMXPEntry = table.getEntry("yMXP");
    angleMXPEntry = table.getEntry("angleMXP");
    xGyroEntry = table.getEntry("xGyro");
    yGyroEntry = table.getEntry("yGyro");
    angleGyroEntry = table.getEntry("angleGyro");
    leftEncoderEntry = table.getEntry("leftEnc");
    rightEncoderEntry = table.getEntry("rightEnc");
    xRioEntry = table.getEntry("xRio");
    yRioEntry = table.getEntry("yRio");

    // m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    m_visionChoice.addOption("Processed", new ProcessCamera());
    m_visionChoice.setDefaultOption("Default", new ViewCamera());
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

    /**
     * Add all of the data to the network table.
     */

    if (ahrs != null) {
      xMXPEntry.setNumber(ahrs.getDisplacementX());
      yMXPEntry.setDouble(ahrs.getDisplacementY());
      angleMXPEntry.setDouble(ahrs.getAngle());
    }

    if (frcAccel != null) {
      xGyroEntry.setDouble(frcAccel.getX());
      yGyroEntry.setDouble(frcAccel.getY());
    }
    if (frcGyro != null) {
      angleGyroEntry.setDouble(frcGyro.getAngle());
    }

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
    m_visionChoice.getSelected().start();
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
    m_visionChoice.getSelected().start();
    // m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

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
    m_visionChoice.getSelected().start();
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
