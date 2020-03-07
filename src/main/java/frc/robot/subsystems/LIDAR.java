/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.Ports;
public class LIDAR extends SubsystemBase {
  /**
   * Creates a new LIDAR.
   */
  public static Counter m_LIDAR;
  public void init() {
      try {
        m_LIDAR = new Counter(Ports.LIDAR); //lidar Port (in constants)
        m_LIDAR.setMaxPeriod(1.00); //set the max period that can be measured
        m_LIDAR.setSemiPeriodMode(true); //Set the counter to period measurement
        m_LIDAR.reset();
      } catch (RuntimeException ex ) {
          DriverStation.reportError("Error initializing LIDAR:  " + ex.getMessage(), true);
      }
  }
   
  public void reset(){
    init();
  }

  public double getDistance() {
    // Return distance  from LIDAR in inches
    double dist=100000;
    try {
      dist = (m_LIDAR.getPeriod()*1000000.0/10.0)/2.54; //Sensor is high 10 us for every centimeter. 
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error reading LIDAR:  " + ex.getMessage(), true);
    }
    return dist;
  }

  final double off  = 0; //offset for sensor. test with tape measure
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Distance", getDistance()); //put the distance on the dashboard
  }
}
