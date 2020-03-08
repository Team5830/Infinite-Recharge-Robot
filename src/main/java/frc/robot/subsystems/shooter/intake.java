/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class intake extends SubsystemBase {
  /**
   * Creates a new intake.
   */
  public static WPI_TalonSRX firstIntake = new WPI_TalonSRX(Constants.CANBusID.firstintakeMotor);
  DigitalInput intakesensor1 = new DigitalInput(Constants.Ports.intakeProximitySensor1) ;
  public boolean isfirstintakeon = false;
  public void intakeoneball(){
    intakesensor1.requestInterrupts();
    intakesensor1.setUpSourceEdge(false, true);  // Set to trigger on falling edge
    startFirstIntake();
    intakesensor1.waitForInterrupt(0);
    intakesensor1.cancelInterrupts(); // Reset so we can use it again 
    // Wait until ball passes -> ballsensor
    stopFirstIntake();
  }
  public static WPI_TalonSRX secondIntake = new WPI_TalonSRX(Constants.CANBusID.secondintakeMotor);
  DigitalInput itakesensor2 = new DigitalInput(Constants.Ports.intakeProximitySensor2) ;
  public boolean isSecondintakeon = false;
  public void intakeball(){
    itakesensor2.requestInterrupts();
    itakesensor2.setUpSourceEdge(false, true);  // Set to trigger on falling edge
    startFirstIntake();
    itakesensor2.waitForInterrupt(Constants.ShooterConstants.waitforshootersecs);
    itakesensor2.cancelInterrupts(); // Reset so we can use it again 
    // Wait until ball passes -> ballsensor
    stopFirstIntake();
  }
  

  public boolean firstIntakeON = false;
  public boolean secondIntakeON = false;
  public boolean firstINtakeReversed = false;
  public boolean secondINtakeReversed = false;

  
  public void startFirstIntake(){
    firstIntake.set(-0.5);
    firstIntakeON = true;
    SmartDashboard.putBoolean("FirstIntakeOn", firstIntakeON);
  }

  public void startSecondIntake(){
    secondIntake.set(-0.6);
    secondIntakeON = true;
    SmartDashboard.putBoolean("SecondIntakeOn", secondIntakeON);
  }

  public void reverseFirstIntake(){
    firstIntake.set(0.5);
    firstIntakeON = true;
    firstINtakeReversed = true;
    SmartDashboard.putBoolean("FirstIntakeOn", firstIntakeON);
    SmartDashboard.putBoolean("FirstIntakeReversed", firstINtakeReversed);
  }

  public void reverseSecondIntake(){
    secondIntake.set(0.6);
    secondIntakeON = true;
    secondINtakeReversed = true;
    SmartDashboard.putBoolean("SecondIntakeOn", secondIntakeON);
    SmartDashboard.putBoolean("SecondIntakeReversed", secondINtakeReversed);
  }

  public void stopFirstIntake(){
   firstIntake.set(0);
   firstIntakeON = false;
   firstINtakeReversed = false;
   SmartDashboard.putBoolean("FirstIntakeOn", firstIntakeON);
   SmartDashboard.putBoolean("FirstIntakeReversed", firstINtakeReversed);
  }

  public void stopSecondIntake(){
    secondIntake.set(0);
    secondIntakeON = false;
    secondINtakeReversed = false;
    SmartDashboard.putBoolean("SecondIntakeOn", secondIntakeON);
    SmartDashboard.putBoolean("SecondIntakeReversed", secondINtakeReversed);
   }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
