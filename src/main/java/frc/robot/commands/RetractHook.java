/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;
public class RetractHook extends InstantCommand {
  /**
   * Creates a new shooterfeeder.
   */
  
  public RetractHook() {
    addRequirements(RobotContainer.m_climber);
    // Use addRequirements() here to declare subsystem dependencies.
  }
  
  boolean hookOn = false;
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
     if(!RobotContainer.m_climber.isclimberon){
      RobotContainer.m_climber.ClimberReverse();
    } else {
      RobotContainer.m_climber.ClimberOff();
    }
    
  }

  
}



