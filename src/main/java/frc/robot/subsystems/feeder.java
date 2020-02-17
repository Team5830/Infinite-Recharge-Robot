/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class feeder extends SubsystemBase {
  /**
   * Creates a new feeder.
   */
  public static Victor feeder = new Victor(4);
  DigitalInput ballsensor = new DigitalInput(8);
  public feeder() {

  }
  public static boolean isfeederon = false;
  
  public void feedoneball(){
    ballsensor.requestInterrupts();
    ballsensor.setUpSourceEdge(false, true);
    feederon();
    ballsensor.waitForInterrupt(10);
    // Wait until ball passes -> ballsensor
    feederoff();
  }
  public void feederon(){
    
    feeder.set(0.75);
    isfeederon = true;
    SmartDashboard.putBoolean("feederon", isfeederon);
   }
  public void feederoff(){
   feeder.set(0);
   isfeederon = false;
   SmartDashboard.putBoolean("feederon", isfeederon);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
  