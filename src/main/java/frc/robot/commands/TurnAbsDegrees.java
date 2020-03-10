/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Rotate;

public class TurnAbsDegrees extends CommandBase {
  /**
   * Creates a new TurnAbsDegrees.
   */
  private final Rotate m_drivetrain;
  private Double absAngle = 200.0;
  public TurnAbsDegrees(Rotate drivetrain, Double angle_in) {
    m_drivetrain = drivetrain;
    if (angle_in <=180 && angle_in >= -180){
      absAngle = angle_in;
    } 
    addRequirements(m_drivetrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (absAngle <=180 && absAngle >= -180){
      m_drivetrain.setTarget(absAngle);
    }else{
      m_drivetrain.setTarget(SmartDashboard.getNumber("Angle", 0));
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.enable();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return(m_drivetrain.finished());
  }
}
