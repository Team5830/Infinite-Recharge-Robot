/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.PortAssignments;
//import frc.robot.misc.ControlChooser;
import edu.wpi.first.wpilibj.SerialPort;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Counter;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  // Right Side Motor Controllers
  private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(
    new Spark(PortAssignments.kRightMotorPort1), 
    new Spark(PortAssignments.kRightMotorPort2));
  // Left Side Motor Controllers
  private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(
    new Spark(PortAssignments.kLeftMotorPort1), 
    new Spark(PortAssignments.kLeftMotorPort2));

  private final DifferentialDrive m_drive = new DifferentialDrive (m_leftMotors, m_rightMotors);

  // Right side drive encoder
  
  private final Encoder m_rightEncoder = new Encoder(
    PortAssignments.kRightEncoderPort1, 
    PortAssignments.kRightEncoderPort2, 
    DriveConstants.kRightEncoderReversed);

  // Left side drive encoder
  
  private final Encoder m_leftEncoder = new Encoder(
    PortAssignments.kLeftEncoderPort1, 
    PortAssignments.kLeftEncoderPort2,
    DriveConstants.kLeftEncoderReversed);
  
  /**
   * Creates a new DriveSubsystem.
   */
  // Sets the distance per pulse for the encoders
  
  public DriveTrain() {
    
    //m_leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
   // m_rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
  }
  

  // The gyro sensor
  private static AHRS navx;
  public static boolean navxConnected = false;

  public void getNavx(){
    try {
      navx = new AHRS(SerialPort.Port.kUSB1);
    } catch (Exception ex) {
      DriverStation.reportError("Error instantiating navX :  " + ex.getMessage(), true);
    }
    navxConnected = true;
  }
 
  public void move(double left, double right){
    m_drive.tankDrive(left, right);
  }

  public void rotate(double spValue){ 
    // Positive rotation is rotating to the left, 
    // negative is rotating to the right
    m_drive.tankDrive(spValue,-spValue);
  }

  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public Encoder getLeftEncoder() {
    return m_leftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public Encoder getRightEncoder() {
    return m_rightEncoder;
  }

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  /**
   * Zeroes the heading of the robot.
   */
  public void zeroGyro() {
    navx.zeroYaw();
  }

  // Resets gyro 
  public void resetGyro() {
    navx.reset();
  }

  public void adjustAngleGyro(double adjustment ){
    navx.setAngleAdjustment(adjustment);
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from 180 to 180
   */
  public double getHeading() {
    //double currentHeading = 0;
    //if (navx.isMagnetometerCalibrated() ){
    //  currentHeading = navx.getFusedHeading();
    //}
    double currentHeading = navx.getAngle();
    // currentHeading is continuous, limit to -180 to 180 
    return Math.IEEEremainder(currentHeading, 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return navx.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }
  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public boolean isCalibrating() {
    return navx.isCalibrating() ;
  }

  private Counter m_LIDAR;
  public boolean lidarConnected = false;
  public void getLIDAR() {
    try {
      m_LIDAR = new Counter(PortAssignments.LIDARPort); //LIDAR  PWM Port
      m_LIDAR.setMaxPeriod(1.00); //set the max period that can be measured
      m_LIDAR.setSemiPeriodMode(true); //Set the counter to period measurement
      m_LIDAR.reset();
    }catch (Exception ex) {
      DriverStation.reportError("Error instantiating LIDAR :  " + ex.getMessage(), true);
    }
    lidarConnected = true;
  }

  public double getDistance(){
    double dist = -1; // Distance should always be positive this is a default value 
    if (lidarConnected){
      dist = (m_LIDAR.getPeriod()*1000000.0/10.0); //convert to distance. sensor is high 10 us for every centimeter. 
    }
    return dist;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (navxConnected) {
      SmartDashboard.putNumber("Gyro Heading", getHeading()); //put the distance on the dashboard
    }
    if (lidarConnected){
      SmartDashboard.putNumber("LIDAR Distance", getDistance()); //put the distance on the dashboard
    }
  }
}
