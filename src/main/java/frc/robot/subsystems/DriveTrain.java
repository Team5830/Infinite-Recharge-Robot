/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  // Right Side Motor Controllers
  private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(
    new Spark(Ports.kLeftMotor1),
    new Spark(Ports.kLeftMotor2),
    new Spark(Ports.kLeftMotor3)
    );

  // Left Side Motor Controllers
  private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(
    new Spark(Ports.kLeftMotor1), 
    new Spark(Ports.kLeftMotor2),
    new Spark(Ports.kLeftMotor3)
    );

  private final DifferentialDrive m_drive = new DifferentialDrive (m_leftMotors, m_rightMotors);
   
  public void TankDrive(double left, double right) {
    //double driveSpeed = SmartDashboard.getNumber("Speed Percentage", 100)/100;
    //if(SmartDashboard.getBoolean("Reverse Left Drivetrain?", true)){
    //}
    m_drive.tankDrive(-left, right);
  }    
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
