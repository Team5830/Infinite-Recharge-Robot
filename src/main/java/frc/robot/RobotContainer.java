/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_drivetrain = new DriveTrain();
  private final Joystick m_leftJoy = new Joystick(0);
  private final Joystick m_rightJoy = new Joystick(1);
  private final CommandBase m_autonomousCommand = new Autonomous(m_drivetrain);
  private final Gyro m_gyro = new Gyro();
  private final LIDAR m_lidar = new LIDAR();
  private final Shooter m_shooter = new Shooter();
  public static final Intake m_intake = new Intake();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    initializeSensors();
    // Configure the button bindings
    configureButtonBindings();
    m_drivetrain.setDefaultCommand(new TankDrive(() -> m_leftJoy.getY(), () -> m_rightJoy.getY(), m_drivetrain ));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Button intake_a_button = new JoystickButton(m_rightJoy,3).whenPressed(new first_intake_instant());
    Button intake_b_button = new JoystickButton(m_rightJoy,5).whenPressed(new second_intake_instant());
    Button movetobutton = new JoystickButton(m_leftJoy,3).whenPressed(new MoveToDistanceLIDAR(12, m_drivetrain, m_lidar, true ).withTimeout(5)) ;
    Button auto_button = new JoystickButton(m_rightJoy,11).whenPressed(new Autonomous(m_drivetrain));
    /*
    Button turnright5 = new JoystickButton(m_leftJoy,3).whenPressed(new TurnToAngle(5,RobotContainer.m_driveTrain,RobotContainer.m_gyro,true ).withTimeout(5));
    Button turnleft5 = new JoystickButton(m_leftJoy,4).whenPressed(new TurnToAngle(-5,RobotContainer.m_driveTrain,RobotContainer.m_gyro,true ).withTimeout(5));
    Button shooter_button = new JoystickButton(m_rightJoy,1).whenPressed(new shooter_instant());
    Button feeder_button = new JoystickButton(m_rightJoy,4).whenPressed(new feederinstant()); 
    Button feeder_b_Button = new JoystickButton(m_rightJoy,6).whenPressed(new feeder_b_revirse());
    
    Button extend_climber = new JoystickButton(m_rightJoy,8).whenPressed(new ExtendHook());
    Button retract_climber = new JoystickButton(m_rightJoy,9).whenPressed(new RetractHook());
    Button extend_winch = new JoystickButton(m_rightJoy, 10).whenPressed(new Extendwinch());
    */
  }
  
  private void initializeSensors(){
    m_gyro.init();
    m_lidar.init();
    m_drivetrain.initEncoder();
    m_shooter.init();

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous

    return m_autonomousCommand;
  }
}
