/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DriverStation;
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
  public Vision() {
    SmartDashboard.putString("View", "Front");
    SmartDashboard.putNumber("Target", 0);
    
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    targetXpos = SmartDashboard.getNumber("HT xPosition", -1);
    targetYpos = SmartDashboard.getNumber("HT yPosition",-1);
    targetLength = SmartDashboard.getNumber("HT length",-1);
    targetScore = SmartDashboard.getNumber("HT score",-1);
    ballXpos = SmartDashboard.getNumber("Ball xPosition",-1);
    ballYpos = SmartDashboard.getNumber("Ball yPosition",-1);
  }
}
