/**
 * @author Hunter Pugh
 */

package frc.robot.misc;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class SmartDashboardInterface {

    public static SendableChooser<Integer> ballCount = new SendableChooser<>();
    public static SendableChooser<String> View = new SendableChooser<>();
    
    public void SmartDashboardInit(){ 
        ballCount.setDefaultOption("Ball Count", 0);
        //get ballcount overide?
        View.setDefaultOption("Camera View","Front");
        //View.addOption
        //controlType.addOption("EXAMPLE: Arlene", 1);
        //SmartDashboard.putData("Control Configuration", controlType);
        SmartDashboard.putNumber("Speed Percentage", 100);
     
    }

    public void SmartDashboardPeriodic(){
    }
}