/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class intake extends SubsystemBase {
  /**
   * Creates a new intake.
   */
  public static WPI_TalonSRX firstIntake = new WPI_TalonSRX(Constants.CANBusID.firstintakeMotor);
  public static WPI_TalonSRX secondIntake = new WPI_TalonSRX(Constants.CANBusID.secondintakeMotor);
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
