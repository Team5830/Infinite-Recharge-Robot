/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class feeder extends SubsystemBase {
  /**
   * Creates a new feeder.
   */
  public static Victor feeder = new Victor(3);{
    
  }
  public feeder() {

  }
  public static boolean isfeederon = false;
  public void feederon(){
    
    feeder.set(0.75);
 
    SmartDashboard.putBoolean("feederon", true);
    isfeederon = true;
  }
  public void feederoff(){
    
   feeder.set(0);
 
   SmartDashboard.putBoolean("feederon", false);
   isfeederon = false;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
  