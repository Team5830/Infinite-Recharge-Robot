/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalGlitchFilter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Indexing extends SubsystemBase {
  /**
   * Creates a new indexing.
   */
  public static DigitalInput entranceSensor;
  public static DigitalInput ShooterSensor;
  public static DigitalInput intakeSensor1;
  public static DigitalInput intakeSensor2;
  public static DigitalInput feederSensor1;
  public static DigitalInput feederSensor2;
  public static DigitalInput feederSensor3;
  public static DigitalGlitchFilter glitchFilter;
  public Boolean[] ballsensors = new Boolean[5];
  
  public void init(){
    try{
      intakeSensor1 = new DigitalInput(Constants.Ports.intakeProximitySensor1);
      intakeSensor2 = new DigitalInput(Constants.Ports.intakeProximitySensor2);
      feederSensor1 = new DigitalInput(Constants.Ports.feederProximitySensor1);
      feederSensor2 = new DigitalInput(Constants.Ports.feederProximitySensor2);
      feederSensor3 = new DigitalInput(Constants.Ports.feederProximitySensor3);

      glitchFilter = new DigitalGlitchFilter();
      glitchFilter.setPeriodCycles(5); // Increase to improve glitch filtering number of FPGA cycles input must be high or low
      glitchFilter.add(intakeSensor1);
      glitchFilter.add(intakeSensor2);
      glitchFilter.add(feederSensor1);
      glitchFilter.add(feederSensor2);
      glitchFilter.add(feederSensor3);
      
      ballsensors[0] = intake1Get();
      ballsensors[1] = intake2Get();
      ballsensors[2] = feeder1Get();
      ballsensors[3] = feeder2Get();
      ballsensors[4] = feeder3Get();
      
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error in indexing init: " + ex.getMessage(), true);
    }
  }
  public void intake1Wait(){
    intakeSensor1.requestInterrupts();
    intakeSensor1.setUpSourceEdge(true, false);  // Set to trigger on rising edge
    intakeSensor1.waitForInterrupt(60); //Wait up to 60 seconds
    intakeSensor1.cancelInterrupts(); // Reset so we can use it again
  }
  public void intake2Wait(){
    intakeSensor2.requestInterrupts();
    intakeSensor2.setUpSourceEdge(true, false);  // Set to trigger on rising edge
    intakeSensor2.waitForInterrupt(60); //Wait up to 60 seconds
    intakeSensor2.cancelInterrupts(); // Reset so we can use it again
  }
  public boolean intake1Get(){
    return(!intakeSensor1.get());
  }
  public boolean intake2Get(){
    return(!intakeSensor2.get());
  }
  public boolean feeder1Get(){
    return(!feederSensor1.get());
  }
  public boolean feeder2Get(){
    return(!feederSensor2.get());
  }
  public boolean feeder3Get(){
    return(!feederSensor3.get());
  }
public int getBallCount(){
    int ballCount = 0;

    if (!feederSensor3.get()){
      ballCount += 1;
    }
    if (!feederSensor2.get()){
      ballCount += 1;
    }
    if (!feederSensor1.get() ){
      ballCount += 1;
    }
    if (!intakeSensor1.get()){
      ballCount += 1;
    }
    if (!intakeSensor2.get()){
      ballCount += 1;
    }
    ballsensors[0] = intake1Get();
    ballsensors[1] = intake2Get();
    ballsensors[2] = feeder1Get();
    ballsensors[3] = feeder2Get();
    ballsensors[4] = feeder3Get();

    SmartDashboard.putBooleanArray("Balls", ballsensors);
    SmartDashboard.putNumber("Ball Count", ballCount);
    return(ballCount);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    getBallCount();
  }
}