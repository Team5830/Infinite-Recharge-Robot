/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;

/**
 * A command that will turn the robot to the specified angle.
 */
public class TurnToAngle extends PIDCommand {
  /**
   * Turns to robot to the specified angle.
   *
   * @param targetAngleDegrees The angle to turn to
   * @param drive              The drive subsystem to use
   * @param G                  The Gryo to get measurements from 
   * @param kTurnP              Proportional feedback parameter
   * @param kTurnI              Integral feedback parameter
   * @param kTurnD              Derivative feedback parameter
   * @param kTurnToleranceDeg   PID tolerance in degrees
   * @param kTurnRateToleranceDegPerS PID rate tolerance in degrees/second
   */

  public TurnToAngle(double targetAngleDegrees, DriveTrain drive, Gyro G, 
  double kTurnP, double kTurnI, double kTurnD, double kTurnF, 
  double kTurnToleranceDeg, double kTurnRateToleranceDegPerS) {
    super(new PIDController(kTurnP, kTurnI, kTurnD), 
    G::getHeading, targetAngleDegrees, output -> drive.ArcadeDrive(0,(output<0)?(MathUtil.clamp(output-kTurnF,-1.0,1.0)):(MathUtil.clamp(output+kTurnF,-1.0,1.0))), drive);
       //drive.ArcadeDrive(0,(output<0)?(MathUtil.clamp(output-kTurnF,-1.0,1.0)):(MathUtil.clamp(output+kTurnF,-1.0,1.0)))
        
        // Close loop on heading
        //drive::getHeading,
        // Set reference to target
        //targetAngleDegrees,
        // Pipe output to turn robot
        //output -> drive.arcadeDrive(0, output),
        // Require the drive
        //drive();

    // Set the controller to be continuous (because it is an angle controller)
    getController().enableContinuousInput(-180, 180);
    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController()
        .setTolerance(kTurnToleranceDeg, kTurnRateToleranceDegPerS);
  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return getController().atSetpoint();
  }
}




//public class Rotate90 extends CommandBase {
  /**
   * Creates a new Rotate90.
   */
  /*
   private final Rotate m_drivetrain;
  private double startangle;

  public Rotate90(Rotate drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startangle = m_drivetrain.getMeasurement();
    m_drivetrain.setTarget(startangle+90);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.enable();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return(m_drivetrain.finished());
  }
}
*/