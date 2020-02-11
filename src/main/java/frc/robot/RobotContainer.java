/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.controlpanel.ControlPanelIndex;
import frc.robot.misc.ControlChooser;
import frc.robot.commands.DriveTrain_TankDrive;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final ControlPanel m_controlPanel = new ControlPanel();
  public static final DriveTrain m_driveTrain = new DriveTrain();
  public static final Command m_tankDrive = new DriveTrain_TankDrive(m_driveTrain);
  private final ControlPanelIndex m_controlPanelIndex = new ControlPanelIndex(m_controlPanel);
  public static int controlMethod = 1; //If we add other control methods then add sendable options
  public static ControlChooser m_driver_controls;
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    m_driver_controls = new ControlChooser();
    m_driver_controls.ControlInit(controlMethod);
    SmartDashboard.putData("Control Panel Index", m_controlPanelIndex);
  }

  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
