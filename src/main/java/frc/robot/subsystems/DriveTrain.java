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

  public void TankDrive(double left, double right) {
    double driveSpeed = SmartDashboard.getNumber("Speed Percentage", 100)/100;

    if(SmartDashboard.getBoolean("Reverse Left Drivetrain?", true)){
      sparkL1.set(-left * driveSpeed);
      sparkL2.set(-left * driveSpeed);
      sparkL3.set(-left * driveSpeed);

      sparkR2.set(right * driveSpeed);
      sparkR1.set(right * driveSpeed);
      sparkR3.set(right * driveSpeed);
    }
  }
      
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
