/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Indexing;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class FeedOne extends InstantCommand {
  Feeder m_feeder;
  Indexing m_index;
  public FeedOne(Feeder feeder_in, Indexing index_in) {
    m_feeder = feeder_in;
    m_index = index_in;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_feeder,m_index);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_index.ballsensors[4] || m_index.ballsensors[3] || m_index.ballsensors[2] ){
        m_feeder.feedoneball();
    }
}

}