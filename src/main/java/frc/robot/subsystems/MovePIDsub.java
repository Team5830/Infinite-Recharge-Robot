/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class MovePIDsub extends PIDSubsystem {
  /**
   * Creates a new Move.
   */
  //private final SimpleMotorFeedforward m_DriveFeedforward =
  //    new SimpleMotorFeedforward(Constants.DriveConstants.kSVolts, Constants.DriveConstants.kVVoltSecondsPerRotation);
  double distanceInches;
  private LIDAR lidar;
  private DriveTrain drive;
  public double kDriveF=0.0; 
  //public double DriveConstants.MaxAlignSpeed , DriveConstants. ; 
  public PIDController pidcontrol;
  private boolean useLIDAR = false;
  public MovePIDsub(DriveTrain m_drive, LIDAR m_lidar, boolean uselidar) {
    super(new PIDController(Constants.DriveConstants.kDriveP, Constants.DriveConstants.kDriveI, Constants.DriveConstants.kDriveD)); 
    lidar = m_lidar;
    drive = m_drive;
    useLIDAR = uselidar;
    pidcontrol = getController();
    getController().setTolerance(Constants.DriveConstants.kPositionToleranceInches,Constants.DriveConstants.MaxAlignSpeed);
  }

  @Override
  public double getMeasurement() {
    double dist; 
    if (useLIDAR){
      dist = lidar.getDistance();
      if (dist > 1 && dist < 250){
        return dist;
      }else{
        disable();
        return 0;
      }
    }else{
      return drive.getAverageDistance();
    }
   }

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output (and optionally the setpoint) here
    if (output < 0){
      output = output - kDriveF;
    }else{
      if (output > 0){
        output = output + kDriveF;
      }else{
        output = 0;
      }
    }
    drive.ArcadeDrive(MathUtil.clamp(output,-Constants.DriveConstants.MaxAlignSpeed ,Constants.DriveConstants.MaxAlignSpeed),0 );
  }
  
}
