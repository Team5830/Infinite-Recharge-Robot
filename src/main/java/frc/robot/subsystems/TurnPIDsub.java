package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpiutil.math.MathUtil;
import edu.wpi.first.wpilibj.controller.PIDController;
public class TurnPIDsub extends PIDSubsystem{
 /**
   * Turns to robot to the specified angle.
   *
   * @param targetAngleDegrees The angle to turn to
   * @param drive              The drive subsystem to use
   * @param G                  The Gyro to get measurements from 
   * @param kTurnP              Proportional feedback parameter
   * @param kTurnI              Integral feedback parameter
   * @param kTurnD              Derivative feedback parameter
   * @param kTurnToleranceDeg   PID tolerance in degrees
   * @param kTurnRateToleranceDegPerS PID rate tolerance in degrees/second
   */
  private Gyro gyro;
  private DriveTrain drive;
  public double kTurnP=0.0, kTurnI=0.0, kTurnD=0.0, kTurnF=0.0; 
  public double kTurnToleranceDeg, kTurnRateToleranceDegPerS;
  public PIDController pidcontrol;
  public TurnPIDsub(double targetAngleDegrees, DriveTrain D, Gyro G) {
    super(new PIDController(0.0, 0.0, 0.0)); 
        setSetpoint(targetAngleDegrees);
        gyro = G;
        drive = D;
        pidcontrol = getController();
      }

    @Override
      public double getMeasurement() {
        double heading = gyro.getHeading();
        // -1000 means no measurement from gyro
        if (heading == -1000){
          disable();
          System.out.println("TurnPID disabled, Failed to get heading from Gyro");
          return(0.0);
        }else{
          return(heading);
        }
    }

    @Override
    public void useOutput(double output, double setpoint) {
        //drive.ArcadeDrive(0, MathUtil.clamp(output,-1.0,1.0));
        drive.ArcadeDrive(0,(output<0)?(MathUtil.clamp(output-kTurnF,-1.0,1.0)):(MathUtil.clamp(output+kTurnF,-1.0,1.0)));
    }
    
      
    


}