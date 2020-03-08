/**
 * @author Hunter Pugh
 */

package frc.robot.misc;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public final class SmartDashboardInterface {

    public static SendableChooser<Integer> controlType = new SendableChooser<>();

    public void SmartDashboardInit(){ 
        controlType.setDefaultOption("EXAMPLE: Flightsticks & DIDBoard", 0);
        controlType.addOption("EXAMPLE: Arlene", 1);
        SmartDashboard.putData("Control Configuration", controlType);
        SmartDashboard.putNumber("Speed Percentage", 100);
     
    }

    public void SmartDashboardPeriodic(){
    }
}