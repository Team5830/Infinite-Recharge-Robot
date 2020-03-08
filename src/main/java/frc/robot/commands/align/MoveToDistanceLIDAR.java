/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.align;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpiutil.math.MathUtil;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LIDAR;
import frc.robot.Constants.DriveConstants;


public class MoveToDistanceLIDAR extends PIDCommand {
  // This will turn to a fixed angle determined by where the gyro was set to zero  
  public MoveToDistanceLIDAR( double targetDistanceInches, DriveTrain drive, LIDAR lidar, boolean relativeDistance) {
    super( 
         new PIDController(DriveConstants.kDriveP, DriveConstants.kDriveI, DriveConstants.kDriveD),
          // Close loop on Distance from LIDAR
          lidar::getDistance,
          // If relative Distance then add to current position
          targetDistanceInches+(relativeDistance ? lidar.getDistance() : 0),
          // Pipe output to move robot
          output -> drive.TankDrive( MathUtil.clamp(output, -DriveConstants.MaxAlignSpeed, 
          DriveConstants.MaxAlignSpeed),MathUtil.clamp(output, -DriveConstants.MaxAlignSpeed, 
          DriveConstants.MaxAlignSpeed)),
          // Require the drive
          drive);

    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController()
        .setTolerance(DriveConstants.kPositionToleranceInches, DriveConstants.kPositionRateToleranceInchesPerS);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

