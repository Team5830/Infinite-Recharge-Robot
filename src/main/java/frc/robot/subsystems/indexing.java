/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class indexing extends SubsystemBase {
  /**
   * Creates a new indexing.
   */
//  public indexing() {
  public static DigitalInput intakeSensor1 = new DigitalInput(Constants.Ports.intakeProximitySensor1);
  public static DigitalInput intakeSensor2 = new DigitalInput(Constants.Ports.intakeProximitySensor2) ;
  public static DigitalInput shooterSensor = new DigitalInput(Constants.Ports.shooterProximitySensor  );
  public static DigitalInput feederSensor1 = new DigitalInput(Constants.Ports.feederProximitySensor1);
  public static DigitalInput feederSensor2 = new DigitalInput(Constants.Ports.feederProximitySensor2);
  public static DigitalInput feederSensor3 = new DigitalInput(Constants.Ports.feederProximitySensor3);

  //  intakeSensor1.setUpSourceEdge(true, true);  // (false,true) Set to trigger on falling edge
  //  intakeSensor2.setUpSourceEdge(true, true);  // (true, false)Set to trigger on leading edge
  //  shooterSensor.setUpSourceEdge(false, true);  // (true, true) Set to trigger on both
  //  feederSensor1.setUpSourceEdge(false, true);  // 
  //  feederSensor2.setUpSourceEdge(false, true);  // 
  //  feederSensor3.setUpSourceEdge(false, true);  // 


    //     intakesensor1.requestInterrupts();
//      intakesensor1.setUpSourceEdge(false, true);  // Set to trigger on falling edge
   //   intakesensor1.waitForInterrupt();
     // intakesensor1.cancelInterrupts(); // Reset so we can use it again 
      // Wait until ball passes -> ballsensor
     // itakesensor2.requestInterrupts();
  //    itakesensor2.setUpSourceEdge(false, true);  // Set to trigger on falling edge
      
      //itakesensor2.waitForInterrupt();
      //itakesensor2.cancelInterrupts(); // Reset so we can use it again 
      // Wait until ball passes -> ballsensor
      
    
     // feedersensor1.requestInterrupts();
    // feedersensor1.setUpSourceEdge(false, true);  // Set to trigger on falling edge
     // feedersensor1.waitForInterrupt();
      //feedersensor1.cancelInterrupts(); // Reset so we can use it again 
      // Wait until ball passes -> ballsensor

    
     // feedersensor2.requestInterrupts();
     //feedersensor2.setUpSourceEdge(false, true);  // Set to trigger on falling edge
     // feedersensor2.waitForInterrupt();
      //feedersensor2.cancelInterrupts(); // Reset so we can use it again 
      // Wait until ball passes -> ballsensor
       

  //}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
