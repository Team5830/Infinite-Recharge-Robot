/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
//import frc.robot.misc.ControlChooser;
import frc.robot.subsystems.DriveTrain;

public class DriveTrain_TankDrive extends CommandBase {
  /**
   * Creates a new DriveTrain_TankDrive.
   */
  public DriveTrain_TankDrive(DriveTrain subsystem) {
    addRequirements(RobotContainer.m_driveTrain);
  }

  public void drive(double left, double right){
    RobotContainer.m_driveTrain.move(left,right);
  }

  public void rotate(double spValue){ 
    // Positive rotation is rotating to the left, 
    // negative is rotating to the right
    RobotContainer.m_driveTrain.rotate(spValue);
  }

  public void periodic() {
    
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() { 
    RobotContainer.m_driveTrain.getNavx();
    RobotContainer.m_driveTrain.getLIDAR();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //RobotContainer.m_driveTrain.TankDrive(ControlChooser.leftJoy.getY(), ControlChooser.rightJoy.getY());
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
