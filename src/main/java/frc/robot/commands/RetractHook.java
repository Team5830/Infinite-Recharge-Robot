/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Climber;
public class RetractHook extends InstantCommand {
  /**
   * Creates a new shooterfeeder.
   */
  Climber m_climber;
  public RetractHook(Climber climber_in) {
    m_climber = climber_in;
    addRequirements(m_climber);
    // Use addRequirements() here to declare subsystem dependencies.
  }
  
  boolean hookOn = false;
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
     if(!m_climber.isclimberon){
      m_climber.ClimberReverse();
    } else {
      m_climber.ClimberOff();
    }
    
  }

  
}


