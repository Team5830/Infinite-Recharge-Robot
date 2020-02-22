/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANBusID;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  
  // Right Side Motor Controllers
  WPI_TalonSRX m_rightlead = new WPI_TalonSRX(CANBusID.kRightMotor1);
  WPI_VictorSPX m_rightfollow = new WPI_VictorSPX(CANBusID.kRightMotor2);
  
  // Left Side Motor Controllers
  
  WPI_TalonSRX m_leftlead = new WPI_TalonSRX(CANBusID.kLeftMotor1);
  WPI_VictorSPX m_leftfollow = new WPI_VictorSPX(CANBusID.kLeftMotor2);

  DifferentialDrive m_drive = new DifferentialDrive(m_leftlead, m_rightlead);
  
  
  public void TankDrive(double left, double right){
    m_drive.tankDrive(left, right);
  }
    // Right side drive encoder
     ///m_leftEncoder  = m_
     // m_rightEncoder = new m_rightlead.encoder();

    
    
     // m_leftEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
      //m_rightEncoder.setDistancePerPulse(DriveConstants.kEncoderDistancePerPulse);
}