/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.align;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;


public class AlignAngle extends CommandBase {
  /**
   * Creates a new AlignAngle.
   */
  public boolean isItFinished = false;
	private NetworkTableInstance inst = NetworkTableInstance.getDefault();
	private NetworkTable vision = inst.getTable("SmartDashboard");
	private NetworkTableEntry xPositionNetworkTableEntry = vision.getEntry("xPosition");
	private NetworkTableEntry yPositionNetworkTableEntry = vision.getEntry("yPosition");
	public static double xPosition = 0;
  public static double yPosition = 0;
  private static double xCenter = 160;


 

  public AlignAngle() {
    // Use addRequirements() here to declare subsystem dependencies.
  addRequirements(RobotContainer.m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    xPosition = xPositionNetworkTableEntry.getDouble(0);
    yPosition = yPositionNetworkTableEntry.getDouble(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    isItFinished  = false;
    if(Math.abs((xPosition - xCenter)) > 5){ //If not centered
      if(xPosition < xCenter){ //To the left of center
        RobotContainer.m_driveTrain.TankDrive(0,.25);
      }
      if(xPosition > xCenter){ //To the right of center
        RobotContainer.m_driveTrain.TankDrive(.25, 0);
      }
    } else {
      RobotContainer.m_driveTrain.TankDrive(0,0);
      isItFinished = true;
    }
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
