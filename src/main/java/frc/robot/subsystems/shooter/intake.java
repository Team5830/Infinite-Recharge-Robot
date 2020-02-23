/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

//import edu.wpi.first.wpilibj.Talon;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
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
  
  public void startFirstIntake(){
    firstIntake.set(-0.5);
    firstIntakeON = true;
    SmartDashboard.putBoolean("FirstIntakeOn", firstIntakeON);
  }

  public void startSecondIntake(){
    secondIntake.set(-0.5);
    secondIntakeON = true;
    SmartDashboard.putBoolean("SecondIntakeOn", secondIntakeON);
  }

  public void stopFirstIntake(){
   firstIntake.set(0);
   firstIntakeON = false;
   SmartDashboard.putBoolean("FirstIntakeOn", firstIntakeON);
  }

  public void stopSecondIntake(){
    secondIntake.set(0);
    secondIntakeON = false;
    SmartDashboard.putBoolean("SecondIntakeOn", secondIntakeON);
   }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
