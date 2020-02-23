
package frc.robot.subsystems.shooter;

import java.sql.Driver;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANError;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.hal.ConstantsJNI;
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
private CANPIDController m_pidController;
  private CANEncoder m_encoder;
public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, targetRPM, maxVel, minVel, maxAcc, allowedErr;
public void init(){
    try{
        m_leftleadMotor = new CANSparkMax(Constants.CANBusID.leftShooterMotor, MotorType.kBrushless);
        //m_rightfollowMotor = new CANSparkMax(Constants.CANBusID.rightShooterMotor, MotorType.kBrushless);
        m_leftleadMotor.restoreFactoryDefaults();
        //m_rightfollowMotor.restoreFactoryDefaults();
         
        //m_rightfollowMotor.follow(m_leftleadMotor);
    
        } catch (RuntimeException ex){
            DriverStation.reportError("error loading failed" + ex.getMessage(), true);
        }
        }
protected void execute() {  
    // initialze PID controller and encoder object

    m_pidController = m_leftleadMotor.getPIDController();
    m_encoder = m_leftleadMotor.getEncoder();
    
    // PID coefficients
    kP = 6e-5; 
    kI = 1e-6;
    kD = 0; 
    kIz = 0; 
    kFF = 0.000156; 
    kMaxOutput = 1; 
    kMinOutput = -1;
    maxRPM = 5700;

    // Smart Motion Coefficients
    maxVel = 2000; // rpm
    maxAcc = 1500;

    // targetRPM replacing smartmotion
    targetRPM = 0;

    // set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);
 

    /**
     * Smart Motion coefficients are set on a CANPIDController object
     * 
     * - setSmartMotionMaxVelocity() will limit the velocity in RPM of
     * the pid controller in Smart Motion mode
     * - setSmartMotionMinOutputVelocity() will put a lower bound in
     * RPM of the pid controller in Smart Motion mode
     * - setSmartMotionMaxAccel() will limit the acceleration in RPM^2
     * of the pid controller in Smart Motion mode
     * - setSmartMotionAllowedClosedLoopError() will set the max allowed
     * error for the pid controller in Smart Motion mode
     */
    /*int smartMotionSlot = 0;
    m_pidController.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
    m_pidController.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
    m_pidController.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
    m_pidController.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);
      */
    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);

    // display Smart Motion coefficients
    SmartDashboard.putNumber("Max Velocity", maxVel);
    SmartDashboard.putNumber("Min Velocity", minVel);
    SmartDashboard.putNumber("Max Acceleration", maxAcc);
    SmartDashboard.putNumber("Allowed Closed Loop Error", allowedErr);
    SmartDashboard.putNumber("Set Position", 0);
    SmartDashboard.putNumber("Set Velocity", 0);

    //display targetRPM
    SmartDashboard.putNumber("TargetRPM shooter", targetRPM);
    // button to toggle between velocity and smart motion modes
    SmartDashboard.putBoolean("Mode", true);
  }

  @Override
  public void periodic() {
    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);
    double maxV = SmartDashboard.getNumber("Max Velocity", 0);
    double minV = SmartDashboard.getNumber("Min Velocity", 0);
    double maxA = SmartDashboard.getNumber("Max Acceleration", 0);
    double allE = SmartDashboard.getNumber("Allowed Closed Loop Error", 0);
    double tRPM = SmartDashboard.getNumber("TargetRPM shooter", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != kP)) { m_pidController.setP(p); kP = p; }
    if((i != kI)) { m_pidController.setI(i); kI = i; }
    if((d != kD)) { m_pidController.setD(d); kD = d; }
    if((iz != kIz)) { m_pidController.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { m_pidController.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
      m_pidController.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max; 
    }
    //if((maxV != maxVel)) { m_pidController.setSmartMotionMaxVelocity(maxV,0); maxVel = maxV; }
    //if((minV != minVel)) { m_pidController.setSmartMotionMinOutputVelocity(minV,0); minVel = minV; }
    //if((maxA != maxAcc)) { m_pidController.setSmartMotionMaxAccel(maxA,0); maxAcc = maxA; }
    //if((allE != allowedErr)) { m_pidController.setSmartMotionAllowedClosedLoopError(allE,0); allowedErr = allE; }
   if((tRPM != targetRPM)) { m_pidController.setReference(tRPM, ControlType.kVelocity);}

    /*double setPoint, processVariable;
    boolean mode = SmartDashboard.getBoolean("Mode", false);
    if(mode) {
      setPoint = 0;
      try { 
        setPoint = SmartDashboard.getNumber("Set Velocity", 0);
        m_pidController.setReference(setPoint, ControlType.kVelocity);
      } catch ( RuntimeException ex){
        DriverStation.reportError("Shooter: Error setting PID setpoint" + ex.getMessage(),true);
      }
      try {
        processVariable = m_encoder.getVelocity();
      } catch (RuntimeException ex){
        DriverStation.reportError("Shooter: Not able to get velocity " + ex.getMessage(),true);
        processVariable = 0;
      }

    } else {
      setPoint = 0;
      try { 
        setPoint = SmartDashboard.getNumber("Set Position", 0);
      } catch ( RuntimeException ex){
        DriverStation.reportError("Shooter: Error getting PID position setpoint" + ex.getMessage(),true);
      } 
      */
      /**
       * As with other PID modes, Smart Motion is set by calling the
       * setReference method on an existing pid object and setting
       * the control type to kSmartMotion
       */
      /*processVariable = 0;
      try {
        m_pidController.setReference(setPoint, ControlType.kSmartMotion);
        processVariable = m_encoder.getPosition();
      } catch (RuntimeException ex){
        DriverStation.reportError("Shooter: Error getting PID position " + ex.getMessage(),true);
      }
    }
    */
    //SmartDashboard.putNumber("SetPoint", setPoint);
    //SmartDashboard.putNumber("Process Variable", processVariable);
    //SmartDashboard.putNumber("Output", m_leftleadMotor.getAppliedOutput());
  }
  public boolean isshooteron = false;
 public void shooteron(){
   
   //m_leftleadMotor.set(0.30);
   targetRPM = 2000;
    m_pidController.setReference(targetRPM, ControlType.kVelocity);
   SmartDashboard.putBoolean("shooteron", true);
   isshooteron = true;
 }
 public void shooteroff(){
   
  //m_leftleadMotor.set(0);
  targetRPM = 0;
  m_pidController.setReference(targetRPM, ControlType.kVelocity);
  SmartDashboard.putBoolean("shooteroff", false);
  isshooteron = false;
 }
}


   



