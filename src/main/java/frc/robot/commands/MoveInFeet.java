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
import frc.robot.subsystems.Move;

public class MoveInFeet extends CommandBase {
  /**
   * Creates a new Move12.
   */
  private final Move m_move;
  private double startposition;

  public MoveInFeet(Move drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_move = drivetrain;
    addRequirements(RobotContainer.m_move);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startposition = RobotContainer.m_move.getMeasurement(); //Inches
    RobotContainer.m_move.setTarget(startposition + 12*SmartDashboard.getNumber("Feet", 0));
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.m_move.enable();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.m_move.finished();
  }
}
