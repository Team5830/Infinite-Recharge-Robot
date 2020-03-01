
package frc.robot.subsystems.shooter;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANError;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class shooter extends SubsystemBase{
/**
 * create new shooter.
 */
 //shooter Controlers(right side)
private CANSparkMax m_leftleadMotor;
private CANSparkMax m_rightfollowMotor;
private CANEncoder m_encoder;
public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAcc, allowedErr;
public double motorspeed;
public void init(){
    try{
        m_leftleadMotor = new CANSparkMax(Constants.CANBusID.leftShooterMotor, MotorType.kBrushless);
        m_rightfollowMotor = new CANSparkMax(Constants.CANBusID.rightShooterMotor, MotorType.kBrushless);
        m_leftleadMotor.restoreFactoryDefaults();
        m_rightfollowMotor.restoreFactoryDefaults();
        //m_rightfollowMotor.follow(m_leftleadMotor);
        //m_rightfollowMotor.setInverted(true);  Doesn't work for follower
        } catch (RuntimeException ex){
            DriverStation.reportError("error loading failed" + ex.getMessage(), true);
        }  
    m_encoder = m_leftleadMotor.getEncoder();
    motorspeed = Constants.ShooterConstants.shootermotorspeed;
    SmartDashboard.putNumber("Shooter Velocity", motorspeed);
  
  }

  @Override
  public void periodic() {
  
    double velocity_out;
    motorspeed = SmartDashboard.getNumber("Shooter Velocity", 0);
    try {
      velocity_out = m_encoder.getVelocity();
    } catch (RuntimeException ex){
      DriverStation.reportError("Shooter: Not able to get velocity " + ex.getMessage(),true);
      velocity_out = 0;
    }
  
    SmartDashboard.putNumber("Shooter motor Velocity",velocity_out);

    //SmartDashboard.putNumber("Output", m_leftleadMotor.getAppliedOutput());
  }
 public boolean isshooteron = false;
 public void shooteron(){
   
   m_leftleadMotor.set(motorspeed);
   m_rightfollowMotor.set(-motorspeed);
   SmartDashboard.putBoolean("shooteron", true);
   isshooteron = true;
 }
 public void shooteroff(){
   
  m_leftleadMotor.set(0);
  m_rightfollowMotor.set(0);

  SmartDashboard.putBoolean("shooteron", false);
  isshooteron = false;
 }
}


   



