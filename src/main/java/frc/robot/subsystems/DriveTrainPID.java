/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/*
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.CANBusID;

public class DriveTrainPID extends PIDSubsystem {
  /**
   * Creates a new DriveTrainPID.
   */
  // Right Side Motor Controllers
  /*
  WPI_VictorSPX m_rightlead = new WPI_VictorSPX(CANBusID.kRightMotor1);
  WPI_VictorSPX m_rightfollow = new WPI_VictorSPX(CANBusID.kRightMotor2);
  
  // Left Side Motor Controllers
  
  WPI_VictorSPX m_leftlead = new WPI_VictorSPX(CANBusID.kLeftMotor1);
  WPI_VictorSPX m_leftfollow = new WPI_VictorSPX(CANBusID.kLeftMotor2);

  DifferentialDrive m_drive = new DifferentialDrive(m_leftlead, m_rightlead);
  private Encoder m_leftencoder = new Encoder(Constants.Ports.leftDriveEncoder1, Constants.Ports.leftDriveEncoder2);
  private Encoder m_rightencoder = new Encoder(Constants.Ports.rightDriveEncoder1, Constants.Ports.rightDriveEncoder2);
  private final SimpleMotorFeedforward m_DriveFeedforward =
      new SimpleMotorFeedforward(Constants.DriveConstants.kSVolts, Constants.DriveConstants.kVVoltSecondsPerRotation);
  double distanceInches;

  public DriveTrainPID() {
    super(
        // The PIDController used by the subsystem
        new PIDController(Constants.DriveConstants.kDriveP, Constants.DriveConstants.kDriveI, Constants.DriveConstants.kDriveD));
        getController().setTolerance(Constants.DriveConstants.kPositionToleranceInches);
        initEncoder();
        getController().setSetpoint(distanceInches);
  }

  public void setTarget(double TargetDistanceInches){
    distanceInches = TargetDistanceInches;
  }

  public void setRelative(double TargetChangeInches){
    setTarget(getAverageDistance()+TargetChangeInches);
  }
  
  public void initEncoder(){
    m_leftencoder.setDistancePerPulse(Constants.DriveConstants.kEncoderDistancePerPulse); //6"/5 counts per rev
    m_rightencoder.setDistancePerPulse (Constants.DriveConstants.kEncoderDistancePerPulse); 
  }
  
  public void TankDrive(double left, double right){
    m_drive.tankDrive(left, right);
  }
  
  public void ArcadeDrive(double xSpeed, double zRotation){
    m_drive.arcadeDrive(xSpeed, zRotation, true);
  }

  public double getAverageDistance(){
  return (m_leftencoder.getDistance()+m_rightencoder.getDistance())/2;
  }

  public double getLeftDistance(){
    return (m_leftencoder.getDistance());
  }

  public double getRightDistance(){
    return (-m_rightencoder.getDistance());
  }

  public double getDifference(){
    return (getLeftDistance()-getRightDistance()); 
  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }

  public void reset() {
    m_leftencoder.reset();
    m_rightencoder.reset();
  }

  @Override
  public void useOutput(double output, double setpoint) {
    //m_shooterMotor.setVoltage(output + m_shooterFeedforward.calculate(setpoint));
    ArcadeDrive(output + m_DriveFeedforward.calculate(setpoint),0);
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return getAverageDistance();
  }

  public void periodic() {
    SmartDashboard.putNumber("Encoder Distance", getAverageDistance());
    SmartDashboard.putNumber("Right Encoder Distance",getRightDistance());
    SmartDashboard.putNumber("Left Encoder Distance",getLeftDistance());
  }
}
*/