/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Winch extends SubsystemBase {
  /**
   * Creates a new winch.
   */
  public static WPI_VictorSPX climbermotor = new WPI_VictorSPX(Constants.CANBusID.winchMotor);
  public boolean iswinchon = false;

  public void WinchOn(){
    climbermotor.set(-Constants.WinchConstants.winchmotorspeed);
    iswinchon = true;
    SmartDashboard.putBoolean("Winch On", iswinchon);
   }
   public void winchReverse(){
    climbermotor.set(Constants.ClimberConstants.climbmotorspeed);
    iswinchon = true;
    SmartDashboard.putBoolean("winch On", iswinchon);
    SmartDashboard.putBoolean("Winch Reversed", true);
   }

  public void winchOff(){
   climbermotor.set(0);
   iswinchon = false;
   SmartDashboard.putBoolean("Winch On", iswinchon);{

   }
  }
 @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}