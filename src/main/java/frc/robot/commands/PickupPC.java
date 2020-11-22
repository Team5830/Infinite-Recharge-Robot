/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexing;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;

public class PickupPC extends CommandBase {
  /**
   * Creates a new pickupPC.
   */
  private final Indexing m_indexing;
  private final Intake m_intake;
  public Timer pickupTimer = new Timer();
  public DriveTrain m_drive;
  public Joystick leftJoy;
  public Joystick rightJoy;

  public PickupPC(Indexing indexing_in, Intake intake_in, DriveTrain drive_in, Joystick lJoy, Joystick rJoy) {
    m_indexing = indexing_in;
    m_intake = intake_in;
    m_drive = drive_in;
    leftJoy = lJoy;
    rightJoy = rJoy;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intake, m_indexing, m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pickupTimer.reset();
    // m_SmartDashboardInterface.ballCount = getBallCount();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while (m_indexing.getBallCount() < 5){
      // While the 3 positions closest to the shooter are not full run both Intakes
      if (!m_indexing.ballsensors[2] && !m_indexing.ballsensors[3] && !m_indexing.ballsensors[3]) {
        m_intake.startFirstIntake();
        m_intake.startSecondIntake();
      }else{
        if (!m_indexing.ballsensors[0] && !m_indexing.ballsensors[1]){
        //while (!m_indexing.intake2Get()) {
          m_intake.startFirstIntake();
          m_intake.startSecondIntake();
        }else{
          if (m_indexing.ballsensors[0] && !m_indexing.ballsensors[1]){
            // wait for ball to be in front of sensor
            m_intake.startFirstIntake();
            m_intake.startSecondIntake();
            m_indexing.intake2Wait(); 
            m_intake.stopSecondIntake();
          }else{
            m_intake.startFirstIntake();
            m_indexing.intake1Wait();
            m_intake.stopSecondIntake();
          } 
        }
      }
      while (pickupTimer.get() < 1 ){
        new TankDrive( () -> leftJoy.getY(), () -> rightJoy.getY(), m_drive );
        //Timer.delay(1.0 - pickupTimer.get());
        //new PickupPC(m_indexing, m_intake);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_indexing.getBallCount();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_indexing.getBallCount()==5){
      return true;
    }else{
      return false;
    }
  }
}