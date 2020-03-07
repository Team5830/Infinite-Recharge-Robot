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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  public boolean intake1Get(){
    return(intakeSensor1.get());
  }
  public boolean intake2Get(){
    return(intakeSensor2.get());
  }
  public boolean feeder1Get(){
    return(feederSensor1.get());
  }
  public boolean feeder2Get(){
    return(feederSensor2.get());
  }
  public boolean feeder3Get(){
    return(feederSensor3.get());
  }
public int getBallCount(){
    int ballCount = 0;
    //resetFeed();
    if (feederSensor3.get()){
      ballCount += 1;
    }
    if (feederSensor2.get()){
      ballCount += 1;
    }
    if (feederSensor1.get() ){
      ballCount += 1;
    }
    if (intakeSensor1.get()){
      ballCount += 1;
    }
    if (intakeSensor2.get()){
      ballCount += 1;
    }
    return(ballCount);
}
/*
  public void resetFeed(){
    intakeSensor1.reset();
    intakeSensor2.reset();
    feederSensor1.reset();
    feederSensor2.reset();
    feederSensor3.reset();
  }

  public void resetIntake1(){
    intakeSensor1.reset();
  }
  public void resetIntake2(){
    intakeSensor2.reset();
  }
  public void resetFeed1(){
    feederSensor1.reset();
  }
  public void resetFeed2(){
    feederSensor2.reset();
  }
  public void resetFeed3(){
    feederSensor3.reset();
  }
*/


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
    SmartDashboard.putNumber("Ball Count", getBallCount());
  }
}