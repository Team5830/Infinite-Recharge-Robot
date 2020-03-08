/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.indexing;

public class feeder extends SubsystemBase {
  /**
   * Creates a new feeder.
   */

  public static WPI_VictorSPX feeder = new WPI_VictorSPX(Constants.CANBusID.ShooterFeedMotor);
  //DigitalInput ballsensor = new DigitalInput(Constants.Ports.shooterProximitySensor  );
  public boolean isfeederon = false;
  public void feedoneball(){
   // ballsensor.requestInterrupts();
    //ballsensor.setUpSourceEdge(false, true);  // Set to trigger on falling edge
    indexing.shooterSensor.requestInterrupts();
    indexing.shooterSensor.setUpSourceEdge(false, true);  // Set to trigger on falling edge
    feederon();
    //ballsensor.waitForInterrupt(Constants.ShooterConstants.waitforshootersecs);
    //ballsensor.cancelInterrupts(); // Reset so we can use it again 
    indexing.shooterSensor.waitForInterrupt(Constants.ShooterConstants.waitforshootersecs);
    indexing.shooterSensor.cancelInterrupts(); // Reset so we can use it again 
   
    // Wait until ball passes -> ballsensor
    feederoff();
  }//

  public void feederon(){
    feeder.set(-Constants.ShooterConstants.feedmotorspeed);
    isfeederon = true;
    SmartDashboard.putBoolean("feederon", true);
   }

   public void feederreverse(){
    feeder.set(Constants.ShooterConstants.feedmotorspeed);
    isfeederon = true;
    SmartDashboard.putBoolean("feederon", true);
    SmartDashboard.putBoolean("feedereversed", true);
   }

  public void feederoff(){
   feeder.set(0);
   isfeederon = false;
   SmartDashboard.putBoolean("feederon", false);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run    
    
  }
}
  