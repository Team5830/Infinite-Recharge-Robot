/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  public static final DriveTrain m_drivetrain = new DriveTrain();
  public static final Move m_move = new Move();
  public static final Rotate m_rotate = new Rotate();
  public static final Joystick m_leftJoy = new Joystick(0);
  public static final Joystick m_rightJoy = new Joystick(1);
  public static final CommandBase m_autonomousCommand = new Autonomous(m_rotate,m_move);
  public static final Gyro m_gyro = new Gyro();
  public static final LIDAR m_lidar = new LIDAR();
  public static final Shooter m_shooter = new Shooter();
  public static final Intake m_intake = new Intake();
  public static final Indexing m_index = new Indexing();
  public static final Feeder m_feeder = new Feeder();
  public static final Climber m_climber = new Climber();
  public static final Winch m_winch = new Winch();
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_shooter.init();
    initializeSensors();
    addBoxes();
    // Configure the button bindings
    configureButtonBindings();
    m_drivetrain.setDefaultCommand(new TankDrive(() -> m_leftJoy.getY(), () -> m_rightJoy.getY(), m_drivetrain ));
  }

  public void addBoxes(){
    SmartDashboard.putNumber("Feet", 0);
    SmartDashboard.putNumber("Angle", 0);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Right Joystick
    Button shooter_button = new JoystickButton(m_rightJoy,1).whenPressed(new ShooterToggle(m_shooter));
    Button intake_a_button = new JoystickButton(m_rightJoy,3).whenPressed(new FirstintakeToggle());
    Button feeder_button = new JoystickButton(m_rightJoy,4).whenPressed(new FeederToggle(m_feeder)); 
    Button intake_b_button = new JoystickButton(m_rightJoy,5).whenPressed(new SecondintakeToggle());
    Button feeder_reverse_Button = new JoystickButton(m_rightJoy,6).whenPressed(new FeederReverse(m_feeder));
    Button extend_climber = new JoystickButton(m_rightJoy,8).whenPressed(new ExtendHook(m_climber));
    Button retract_climber = new JoystickButton(m_rightJoy,9).whenPressed(new RetractHook(m_climber));
    Button extend_winch = new JoystickButton(m_rightJoy, 10).whenPressed(new WinchToggle(m_winch));
    Button auto_button = new JoystickButton(m_rightJoy,11).whenPressed(new Autonomous(m_rotate,m_move));
    Button pickup_button = new JoystickButton(m_rightJoy,12).whenPressed(new PickupPC(m_index, m_intake));
    // Left Joystick
    Button movetobutton = new JoystickButton(m_leftJoy,5).whenPressed(new MoveInFeet(m_move).withTimeout(5)) ;
    Button face_right = new JoystickButton(m_leftJoy,3).whenPressed(new Rotate90(m_rotate).withTimeout(5));
    Button face_left = new JoystickButton(m_leftJoy,4).whenPressed(new Rotatem90(m_rotate).withTimeout(5));
    Button turn_to_angle = new JoystickButton(m_leftJoy,6).whenPressed(new TurnAbsDegrees(m_rotate,45.0).withTimeout(5));
    
  }
  
  private void initializeSensors(){
    m_gyro.init();
    m_lidar.init();
    m_drivetrain.initEncoder();
    m_shooter.init();
    m_index.init();
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
