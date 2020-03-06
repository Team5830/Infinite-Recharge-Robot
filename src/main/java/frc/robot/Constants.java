/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class Ports {
     
      // DIO Ports
      
      public static final int rightDriveEncoder1 = 0;
      public static final int rightDriveEncoder2 = 1;
      public static final int leftDriveEncoder1 =  2;
      public static final int leftDriveEncoder2 =  3;
      public static final int ControlPanelEncoder1 = 4;
      public static final int ControlPanelEncoder2 = 5;
      public static final int LIDAR = 6;
      
      //public static final int DIO7  = 7;
      public static final int shooterProximitySensor = 8;
      public static final int intakeProximitySensor1 = 9;
      public static final int intakeProximitySensor2 = 10;
      public static final int feederProximitySensor1 = 11;
      public static final int feederProximitySensor2 = 12;
      public static final int feederProximitySensor3 = 13;
      public static final int entranceProximitysensor = 14;
    }
    public static final class CANBusID{
      public static final int rightShooterMotor = 1;
      public static final int leftShooterMotor = 2;
      public static final int winchMotor = 3;
      public static final int kLeftMotor1  = 4;
      public static final int kLeftMotor2  = 5;
      public static final int secondintakeMotor =  9; //Talon SRX
      public static final int ShooterFeedMotor = 10;
      public static final int controlpanelMotor = 7;
      public static final int hookMotor = 8; 
      public static final int firstintakeMotor =  6; //Talon SRX
      public static final int kRightMotor1 = 11;
      public static final int kRightMotor2 = 12;
      
    }
    public static final class DriveConstants {
      public static final boolean kLeftEncoderReversed = false;
      public static final boolean kRightEncoderReversed = true;
  
      public static final int kEncoderCPR = 20;
      public static final double kWheelCircInches = 18.5;
      public static final double kEncoderDistancePerPulse =
          // Assumes the encoders are directly mounted on the wheel shafts
          (kWheelCircInches) / (double) kEncoderCPR/10.71;//Gear Ratio = 1:10.71
  
      public static final boolean kGyroReversed = false;
     
      //public static final double kStabilizationP = 1;
      //public static final double kStabilizationI = 0.5;
      //public static final double kStabilizationD = 0;

      public static final double MaxAlignSpeed = 0.2; 
      public static final double kDriveP = 1;
      public static final double kDriveI = 0;
      public static final double kDriveD = 0;

      public static final double kPositionToleranceInches = 3;
      public static final double feetPerSec = 0.5;
      public static final double kPositionRateToleranceInchesPerS = feetPerSec*12;

      public static final double kTurnP = 1;
      public static final double kTurnI = 0;
      public static final double kTurnD = 0;
  
      public static final double kMaxTurnRateDegPerS = 100;
      public static final double kMaxTurnAccelerationDegPerSSquared = 300;
      public static final double kMaxTurnPIDTurnSpeed = 0.2;
  
      public static final double kTurnToleranceDeg = 0.5;
      public static final double kTurnRateToleranceDegPerS = 10; // degrees per second
    }
    public static final class ShooterConstants {
      public static final int waitforshootersecs = 5;
      public static final double feedmotorspeed = 0.5;
      public static final double shootermotorspeed = 1000;
  
    }
    //public static final class IntakeConstants{}
    public static final class ClimberConstants {
      public static final double climbmotorspeed = 0.2;
    }
    public static final class OIConstants {
      public static final int kDriverControllerPort = 1;
    }
}
