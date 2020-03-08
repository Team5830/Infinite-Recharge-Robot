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
public class Indexing extends SubsystemBase {
  /**
   * Creates a new indexing.
   */

  public static DigitalInput intakeSensor1 = new DigitalInput(Constants.Ports.intakeProximitySensor1);
  public static DigitalInput intakeSensor2 = new DigitalInput(Constants.Ports.intakeProximitySensor2);
  public static DigitalInput shooterSensor = new DigitalInput(Constants.Ports.shooterProximitySensor);
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Ball Count", getBallCount());
  }
}