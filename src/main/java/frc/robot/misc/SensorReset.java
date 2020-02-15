/**
 * @author Hunter Pugh
 */

package frc.robot.misc;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotContainer;

public class SensorReset {
    
    public void ResetSensors(){

        //Place sensor reset code here. Some examples:
        //RobotContainer.ahrs.enableBoardlevelYawReset(true);
        //RobotContainer.ahrs.reset();
        //RobotContainer.armEncoder.reset();
        //RobotContainer.manipulatorEncoder.reset();
        //RobotContainer.m_driveTrain.resetGyro(); //enableBoardlevelYawReset(true) ?
        RobotContainer.m_driveTrain.getLIDAR();
        System.out.println("Sensors Reset");
        SmartDashboard.putBoolean("Reset Sensors", false);
    }
}