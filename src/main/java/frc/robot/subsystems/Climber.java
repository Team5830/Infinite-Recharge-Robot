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
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */
  public static WPI_VictorSPX climbermotor = new WPI_VictorSPX(Constants.CANBusID.hookMotor);
  public boolean isclimberon = false;

  public void ClimberOn(){
    climbermotor.set(-Constants.ClimberConstants.climbmotorspeed);
    isclimberon = true;
    SmartDashboard.putBoolean("Climber On", isclimberon);
   }

   public void ClimberReverse(){
    climbermotor.set(Constants.ClimberConstants.climbmotorspeed);
    isclimberon = true;
    SmartDashboard.putBoolean("Climber On", isclimberon);
    SmartDashboard.putBoolean("Climber Reversed", true);
   }

  public void ClimberOff(){
   climbermotor.set(0);
   isclimberon = false;
   SmartDashboard.putBoolean("Climber On", isclimberon);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  } 
}
  