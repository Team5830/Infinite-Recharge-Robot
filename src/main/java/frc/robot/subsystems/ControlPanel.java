/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlPanel extends SubsystemBase {
  
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final Spark controlPanel = new Spark(0);
  Color detectedColor;
  String colorString;
  public static final ColorMatch m_colorMatcher = new ColorMatch();
  private final Color kBlueTarget = ColorMatch.makeColor(0.12, 0.42, 0.46);
  private final Color kGreenTarget = ColorMatch.makeColor(0.167, 0.57, 0.26);
  private final Color kRedTarget = ColorMatch.makeColor(0.53, 0.34, 0.14);
  private final Color kYellowTarget = ColorMatch.makeColor(0.32, 0.555, 0.125);
  private Encoder m_encoder = new Encoder(4, 5);
  
  /**
   * Creates a new ExampleSubsystem.
   */
  public ControlPanel() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget); 
    m_encoder.setDistancePerPulse(5000); //TODO set to 1 rotation of control panel
  }

  public void controlPanelMoveIndex(){
    controlPanel.set(0.5);
  }

  public void controlPanelMoveRotate(){
    controlPanel.set(0.75);
  }

  public void controlPanelStop(){
    controlPanel.set(0);
  }

  public void resetEncoder(){
    m_encoder.reset();
  }

  public String getColor(){
    

    return colorString;
  }

  public double getEncoder(){
    return m_encoder.getDistance();
  }

  @Override
  public void periodic() {
    Color detectedColor = m_colorSensor.getColor();

    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    // This method will be called once per scheduler run
  }
}
