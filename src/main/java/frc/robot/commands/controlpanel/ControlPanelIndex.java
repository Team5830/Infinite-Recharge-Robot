/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.controlpanel;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

public class ControlPanelIndex extends CommandBase {
  private final ControlPanel m_subsystem;
  String gameData;
  boolean isItFinished = false;
  /**
   * Creates a new ControlPanelIndex.
   */
  public ControlPanelIndex(ControlPanel subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gameData = DriverStation.getInstance().getGameSpecificMessage();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(gameData.length() > 0){
      m_subsystem.controlPanelMoveIndex();

      switch(gameData.charAt(0)){
        case 'B':
        if(m_subsystem.getColor() == "Red"){
          isItFinished = true;
        }
        break;

        case 'G':
        if(m_subsystem.getColor() == "Yellow"){
          isItFinished = true;
        }
        break;

        case 'Y':
        if(m_subsystem.getColor() == "Green"){
          isItFinished = true;
        }
        break;

        case 'R':
        if(m_subsystem.getColor() == "Blue"){
          isItFinished = true;
        }
        break;
      }
    } else {
      System.out.println("Cannot run ControlPanelIndex, no FMS data!");
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.controlPanelMoveStop();
    isItFinished = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isItFinished;
  }
}
