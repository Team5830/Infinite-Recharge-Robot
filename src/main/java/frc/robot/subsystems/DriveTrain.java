/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.misc.ControlChooser;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */

   //Motor Controllers (Left Side)
   Spark sparkL1 = new Spark(0);
   Spark sparkL2 = new Spark(1);
   Spark sparkL3 = new Spark(2);
 
   //Motor Controllers (Right Side)
   Spark sparkR1 = new Spark(3);
   Spark sparkR2 = new Spark(4);
   Spark sparkR3 = new Spark(5);

  public DriveTrain() {
    double driveSpeed = SmartDashboard.getNumber("Speed Percentage", 100)/100;

    if(SmartDashboard.getBoolean("Reverse Left Drivetrain?", true)){
            sparkL1.set(-ControlChooser.leftJoy.getY() * driveSpeed);
            sparkL2.set(-ControlChooser.leftJoy.getY() * driveSpeed);
            sparkL3.set(-ControlChooser.leftJoy.getY() * driveSpeed);
          } else {
            sparkL1.set(ControlChooser.leftJoy.getY() * driveSpeed);
            sparkL2.set(ControlChooser.leftJoy.getY() * driveSpeed);
            sparkL3.set(ControlChooser.leftJoy.getY() * driveSpeed);
          }
      
          if(SmartDashboard.getBoolean("Reverse Right Drivetrain?", false)){
            sparkR1.set(-ControlChooser.rightJoy.getY() * driveSpeed);
            sparkR2.set(-ControlChooser.rightJoy.getY() * driveSpeed);
            sparkR3.set(-ControlChooser.rightJoy.getY() * driveSpeed);
          } else {
            sparkR1.set(ControlChooser.rightJoy.getY() * driveSpeed);
            sparkR2.set(ControlChooser.rightJoy.getY() * driveSpeed);
            sparkR3.set(ControlChooser.rightJoy.getY() * driveSpeed);
          }
      }
      
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
