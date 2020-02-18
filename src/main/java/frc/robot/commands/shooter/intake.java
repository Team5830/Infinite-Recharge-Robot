/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
public class intake extends CommandBase {
  /**
   * Creates a new shotterdelivery.
   */
  public intake() {
    addRequirements(RobotContainer.m_intake);
    }

  boolean motoron = false;
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }
  
  public void teleopPeriodic(){
      if(motoron){
        RobotContainer.m_intake.intakeon();
      }else{
        RobotContainer.m_intake.intakeoff();
      }
  }
       
  public void updateToggle()
  {    
    if(motoron){
      motoron = false;
    }else{
      motoron = true;
    }  
  }
    
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() { 
     updateToggle();
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
