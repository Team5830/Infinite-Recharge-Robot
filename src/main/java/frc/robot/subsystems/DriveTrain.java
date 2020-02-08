/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.misc.ControlChooser;

import com.kauailabs.navx.frc.AHRS;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  private final SpeedControllerGroup m_rightMotors =
  new SpeedControllerGroup(new Spark(DriveConstants.kRightMotor1Port), new Spark(DriveConstants.kRightMotor2Port));
    //Motor Controllers (Left Side)
    private final SpeedControllerGroup m_leftMotors =
  new SpeedControllerGroup(new Spark(DriveConstants.kLeftMotor1Port), new Spark(DriveConstants.kLeftMotor2Port));

  private final DifferentialDrive m_drive = new DifferentialDrive (m_leftMotors, m_rightMotors);

  private final Encoder m_rightEncoder = new Encoder(DriveConstants.kRightEncoderPorts[0], DriveConstants.kRightEncoderPorts[1], DriveConstants.kRightEncoderReversed);
  
   // The left-side drive encoder
   private final Encoder m_leftEncoder =
   new Encoder(DriveConstants.kLeftEncoderPorts[0], DriveConstants.kLeftEncoderPorts[1],
               DriveConstants.kLeftEncoderReversed);

  /**
   * Creates a new DriveSubsystem.
   */
  public DriveTrain() {
    // Sets the distance per pulse for the encoders
    m_leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
    m_rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
  }

   public void TankDrive(double x, double y){
      m_drive.tankDrive(ControlChooser.leftJoy.getY(), ControlChooser.rightJoy.getY());
      System.out.println(ControlChooser.leftJoy.getY());
      System.out.println(ControlChooser.rightJoy.getX());

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
   // The gyro sensor
  //private final Gyro m_gyro = new ADXRS450_Gyro();
  public static AHRS m_ahrs;
  
  //m_gyro.getAngle()
  /**
   * Zeroes the heading of the robot.
   */
  public void zeroGyro() {
    m_ahrs.zeroYaw();
  }

  // Resets gyro 
  public void resetGyro() {
    m_ahrs.reset();
  }

  public void adjustAngleGyro(double adjustment ){
    m_ahrs.setAngleAdjustment(adjustment);
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from 180 to 180
   */
  public double getHeading() {
    //double currentHeading = 0;
    //if (m_ahrs.isMagnetometerCalibrated() ){
    //  currentHeading = m_ahrs.getFusedHeading();
    //}
    double currentHeading = m_ahrs.getAngle();
    
    return Math.IEEEremainder(currentHeading, 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0) + (DriveConstants.kGyroReversed ? 180 : -180 );
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return m_ahrs.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }
  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public boolean isCalibrating() {
    return m_ahrs.isCalibrating() ;
  }

  public void rotate(double spValue){ 
    // Positive rotation is rotating to the right, 
    // negative is rotating to the left
    m_drive.tankDrive(spValue,-spValue);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
 
  }
}
