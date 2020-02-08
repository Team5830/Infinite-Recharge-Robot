/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.misc.ControlChooser;
import frc.robot.subsystems.DriveTrain;
//import frc.robot.Constants.DriveConstants;

public class DriveTrain_TankDrive extends CommandBase {

  /**
   * Creates a new DriveTrain_TankDrive.
   */
  public DriveTrain_TankDrive(DriveTrain subsystem) {
    addRequirements(RobotContainer.m_driveTrain);
  }


  public void periodic() {
    SmartDashboard.putNumber("Gyro Angle", RobotContainer.m_driveTrain.getHeading());
    
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() { 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.m_driveTrain.TankDrive(ControlChooser.leftJoy.getY(), ControlChooser.rightJoy.getY());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
