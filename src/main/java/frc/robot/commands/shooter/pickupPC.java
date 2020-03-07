/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.indexing;
import frc.robot.subsystems.shooter.intake;

public class pickupPC extends CommandBase {
  /**
   * Creates a new pickupPC.
   */
  private int ballcount = 0;
  private final indexing m_indexing;
  private final intake m_intake; 
  public pickupPC(indexing indexing_in, intake intake_in) {
    m_indexing = indexing_in;
    m_intake = intake_in;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intake,m_indexing);
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ballcount = m_indexing.getBallCount();
    //m_SmartDashboardInterface.ballCount = getBallCount();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while (m_indexing.getBallCount() < 3){
      m_intake.startFirstIntake();
      m_intake.startSecondIntake();
    };
    while (!m_indexing.intake2Get()){
      m_intake.startFirstIntake();
      m_intake.startSecondIntake();
    };
    m_intake.stopSecondIntake();
    //RobotContainer.m_indexing.resetIntake1();
    while (!m_indexing.intake1Get()){
      m_intake.startFirstIntake();
    };
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
