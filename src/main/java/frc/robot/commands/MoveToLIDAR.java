/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LIDAR;

/**
 * A command that will turn the robot to the specified angle.
 */
public class MoveToLIDAR extends PIDCommand {
  /**
   * Moves robot to the distance measured on the LIDAR
   *
   * @param targetDistanceFeet  The Target distance for the LIDAR
   * @param drive               The drive subsystem to use
   * @param lidar               The LIDAR subsystem to use
   * @param kMoveP              Proportional feedback parameter
   * @param kMoveI              Integral feedback parameter
   * @param kMoveD              Derivative feedback parameter
   */

  public MoveToLIDAR(double targetDistanceFeet, DriveTrain drive, LIDAR lidar, double kMoveP, double kMoveI, double kMoveD) {
    super(new PIDController(kMoveP, kMoveI, kMoveD), 
    lidar::getDistance, targetDistanceFeet, output -> drive.ArcadeDrive(output, 0), drive);
        
    // Set the controller to be continuous (because it is an angle controller)
    getController().enableContinuousInput(-180, 180);
    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController()
        .setTolerance(DriveConstants.AlignTolerance, DriveConstants.MaxAlignSpeed);
  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return getController().atSetpoint();
  }
}

