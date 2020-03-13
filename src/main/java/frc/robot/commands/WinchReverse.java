
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Winch;

public class WinchReverse extends InstantCommand {
  Winch m_winch;
  public WinchReverse(Winch winch_in) {
    m_winch = winch_in;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_winch);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(!m_winch.iswinchon){
      m_winch.winchReverse();
    } else{
      m_winch.winchOff();
    }
  }
}