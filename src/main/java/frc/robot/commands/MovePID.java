package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LIDAR;
import frc.robot.subsystems.MovePIDsub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

    /**
     * A command that will turn the robot to the specified angle.
     */
    public class MovePID extends CommandBase {
      /**
       * Turns to robot to the specified angle.
       *
       * @param targetDistanceInches The angle to turn to
       * @param drive                The drive subsystem to use
       * @param lidar                The lidar to get measurements from 
       * @param uselidar            Boolean if false will use drivetrain encoders
       */
      
      private final MovePIDsub tpid;
      public MovePID(double targetDistanceInches, DriveTrain drive, LIDAR lidar,boolean uselidar) {
        tpid = new MovePIDsub(drive, lidar,uselidar);
        addRequirements(drive);
        addRequirements(lidar);
        tpid.setSetpoint(targetDistanceInches);
      }
    
      // Called when the command is initially scheduled.
      @Override
      public void initialize() {
        // Set values
        tpid.pidcontrol.setP( SmartDashboard.getNumber("kDriveP",DriveConstants.kDriveP));
        tpid.pidcontrol.setI( SmartDashboard.getNumber("kDriveI",DriveConstants.kDriveI));
        tpid.pidcontrol.setD( SmartDashboard.getNumber("kDriveD",DriveConstants.kDriveD));
        tpid.kDriveF = SmartDashboard.getNumber("kDriveF",DriveConstants.kDriveF);
        tpid.pidcontrol.setTolerance(SmartDashboard.getNumber("AlignTolInches",DriveConstants.AlignTolerance), SmartDashboard.getNumber("AlignSpeed",DriveConstants.MaxAlignSpeed));
        tpid.enable();
      }

      @Override
      public boolean isFinished() {
        // End when the controller is at the reference.
        return tpid.pidcontrol.atSetpoint();
      }
    
    }
    
    