/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Vision extends SubsystemBase {
  /**
   * Creates a new Vision.
   */
   double targetXpos;
   double targetYpos;
   double targetLength;
   double targetScore;
   double ballXpos;
   double ballYpos;
   double ballSize;
   double currentTarget;
   double currentHeading;
   double currentLIDAR;
  public Vision() {

    
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    currentTarget = SmartDashboard.getNumber("Target", -1);  //What are we looking for 0: Shooting target; 1: ball

    currentHeading= SmartDashboard.getNumber("Gyro Angle",-1);  //Compass Angle, starting position should be 0 
    currentLIDAR =  SmartDashboard.getNumber("LIDAR Distance",-1);
    targetXpos =    SmartDashboard.getNumber("HT xPosition", -1);  //Target X Pixel position from Raspberry Pi
    targetYpos =    SmartDashboard.getNumber("HT yPosition",-1);   //Target Y Pixel position from Raspberry Pi
    targetLength =  SmartDashboard.getNumber("HT length",-1);      //Target width in pixels 
    targetScore =   SmartDashboard.getNumber("HT score",-1);       //Vision processing score 
    
    ballXpos = SmartDashboard.getNumber("Ball xPosition",-1);      // ball X pixel position from Raspberry Pi 
    ballYpos = SmartDashboard.getNumber("Ball yPosition",-1);      // ball Y pixel position from Raspberry Pi
    ballSize = SmartDashboard.getNumber("Ball Size",-1);           // ball size in pixels 
    // Insert code to estimate distance and angle to target
    // camera images are 320x240
  }
}
