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
        RobotContainer.m_gyro.reset();
        RobotContainer.m_LIDAR.reset();
        //RobotContainer.armEncoder.reset();
        //RobotContainer.manipulatorEncoder.reset();

        System.out.println("Sensors Reset");
        SmartDashboard.putBoolean("Reset Sensors", false);
    }
}