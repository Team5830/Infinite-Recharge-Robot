/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.RobotContainer;
import frc.robot.Constants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Move extends ProfiledPIDSubsystem {
  /**
   * Creates a new Move.
   */
  //private final SimpleMotorFeedforward m_DriveFeedforward =
  //    new SimpleMotorFeedforward(Constants.DriveConstants.kSVolts, Constants.DriveConstants.kVVoltSecondsPerRotation);
  double distanceInches;
  public Move() {
    super(
      new ProfiledPIDController(Constants.DriveConstants.kDriveP, Constants.DriveConstants.kDriveI, Constants.DriveConstants.kDriveD,
            new TrapezoidProfile.Constraints(Constants.DriveConstants.MaxAlignSpeed, Constants.DriveConstants.MaxAlignAccel) ) ); 
        getController().setTolerance(Constants.DriveConstants.kPositionToleranceInches);
  }

  @Override
  public void useOutput(double output, TrapezoidProfile.State setpoint) {
    // Use the output (and optionally the setpoint) here
    RobotContainer.m_drivetrain.ArcadeDrive(MathUtil.clamp(output,-Constants.DriveConstants.MaxAlignSpeed ,Constants.DriveConstants.MaxAlignSpeed),0 );
    //+ m_DriveFeedforward.calculate(setpoint ,0);
    DriverStation.reportWarning("Using Output: " + output ,false);
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    DriverStation.reportWarning("Getting Measurement" ,false);
    return RobotContainer.m_drivetrain.getAverageDistance();
   }

  public void setTarget(double targetInches){
    DriverStation.reportWarning("Setting Target: " + targetInches ,false);
    getController().setGoal(targetInches);
  }
  
  // Returns true when the command should end.
  public boolean finished(){
    if (getController().atSetpoint()){
      DriverStation.reportWarning("Finished  " ,false);
    }
    return(getController().atSetpoint());
  }
}
