/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class Rotate extends ProfiledPIDSubsystem {
  /*
   * Creates a new Rotate.
   */
  //TO start:  Rotate.setGoal(TargetAngleDegrees); enable? 
  private Gyro gyro;
  private DriveTrain drive;
  public Rotate() {
    super(
        // The ProfiledPIDController used by the subsystem
        new ProfiledPIDController(Constants.DriveConstants.kTurnP, Constants.DriveConstants.kTurnI, Constants.DriveConstants.kTurnD,
            // The motion profile constraints
          new TrapezoidProfile.Constraints(Constants.DriveConstants.kMaxTurnRateDegPerS, Constants.DriveConstants.kMaxTurnAccelerationDegPerSSquared)) );
        getController().setTolerance(Constants.DriveConstants.kTurnToleranceDeg,Constants.DriveConstants.kTurnRateToleranceDegPerS);
        getController().enableContinuousInput(-180, 180);
  }

  @Override
  public void useOutput(double output, TrapezoidProfile.State setpoint) {
    // Use the output (and optionally the setpoint) here
    //Arcarde Drive rotation Positive is Clockwise
    //Verify Gyro output matches
    RobotContainer.m_drivetrain.ArcadeDrive(0, MathUtil.clamp(output,-Constants.DriveConstants.MaxRotateSpeed,Constants.DriveConstants.MaxRotateSpeed));
  }

  @Override
  public double getMeasurement() {
    double heading = gyro.getHeading();
        return (heading != -1000) ? heading : -1000.0;
        // -1000 means no measurement from gyro
  }

  public void setTarget(double TargetAngleDegrees){
    getController().setGoal(Math.IEEEremainder(TargetAngleDegrees, 360));
  }

  public boolean finished(){
    return getController().atSetpoint();
  }
}
