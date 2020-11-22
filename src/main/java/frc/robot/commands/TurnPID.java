package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.TurnPIDsub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A command that will turn the robot to the specified angle.
 */
public class TurnPID extends CommandBase {
  /**
   * Turns to robot to the specified angle.
   *
   * @param targetAngleDegrees The angle to turn to
   * @param drive              The drive subsystem to use
   * @param G                  The Gryo to get measurements from 
   */
  //private final DriveTrain m_drivetrain;
  //private final Gyro m_gyro;
  private final TurnPIDsub tpid;
  double kTurnF, kTurnToleranceDeg, kTurnRateToleranceDegPerS;
  //private final double kTurnP, kTurnI, kTurnD, kTurnF;
  //private final double kTurnToleranceDeg, kTurnRateToleranceDegPerS;
  public TurnPID(double targetAngleDegrees, DriveTrain drive, Gyro G) {
    //m_drivetrain = drive;
    //m_gyro = G;
    // double kTurnP, double kTurnI, double kTurnD, double kTurnF, 
    tpid = new TurnPIDsub(targetAngleDegrees,drive, G) ;
    addRequirements(drive);
    addRequirements(G);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Set values
    tpid.pidcontrol.setP( SmartDashboard.getNumber("kTurnP",DriveConstants.kTurnP));
    tpid.pidcontrol.setI( SmartDashboard.getNumber("kTurnI",DriveConstants.kTurnI));
    tpid.pidcontrol.setD( SmartDashboard.getNumber("kTurnD",DriveConstants.kTurnD));
    tpid.kTurnF = SmartDashboard.getNumber("kTurnF",DriveConstants.kTurnF);
    kTurnToleranceDeg  = SmartDashboard.getNumber("kTurnF",DriveConstants.kTurnToleranceDeg);
    kTurnRateToleranceDegPerS  = SmartDashboard.getNumber("kTurnF",DriveConstants.kTurnRateToleranceDegPerS);
    tpid.pidcontrol.setTolerance(kTurnToleranceDeg, kTurnRateToleranceDegPerS);
    tpid.enable();
  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return tpid.pidcontrol.atSetpoint();
  }

}

