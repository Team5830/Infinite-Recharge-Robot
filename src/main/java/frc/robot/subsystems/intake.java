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

public class intake extends SubsystemBase {
  /**
   * Creates a new intake.
   */
  public static Victor intake = new Victor(3);{
    
  }
  public intake() {
  
  }
  public static boolean isintakeon = false;
  public void intakeon(){
    
    intake.set(1);
 
    SmartDashboard.putBoolean("intakeon", true);
    isintakeon = true;
  }
  public void intakeoff(){
    
   intake.set(0);
 
   SmartDashboard.putBoolean("intakeon", false);
   isintakeon = false;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
