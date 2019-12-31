/**
 * @author Hunter Pugh
 */

package frc.robot.misc;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public final class ControlChooser {

    int safetyChecker;

    //Define Joystick Names
    Joystick exampleJoystick;

    //Define buttons here
    Button exampleButton;

    //These serve as temporary fillers to show how the Chooser methods work.
    //DELETE THESE!
    double exampleRobotDriveX;
    double exampleRobotDriveY;

    public void ControlInit(int controlMethod){
        switch(controlMethod){
            case 0: //Example name: General Flightsticks with DIDBoard
                //Define Joysticks First!
                exampleJoystick = new Joystick(0);

                //Define Buttons Next
                exampleButton = new JoystickButton(exampleJoystick, 1);
                break;

            case 1://Example name: Arlene
                //Define Joysticks First!
                exampleJoystick = new Joystick(1);

                //Define Buttons Next
                exampleButton = new JoystickButton(exampleJoystick, 4);
                break;
        }
    }

    private void ControlPeriodic(int controlMethod){
        
        safetyChecker = controlMethod;
        
        switch(controlMethod){
            case 0: //Example name: General Flightsticks with DIDBoard
                exampleRobotDriveX = exampleJoystick.getX();
                exampleRobotDriveY = exampleJoystick.getY();
                break;
            
            case 1://Example name: Arlene
                exampleRobotDriveX = exampleJoystick.getZ();
                exampleRobotDriveY = exampleJoystick.getX();
                break;
        }
    }

    //This checks to see if the driver has changed control input and will re-initialize if so.
    public void ControlSafety(int controlMethod){
        if(controlMethod == safetyChecker){
            ControlPeriodic(controlMethod);
        } else {
            ControlInit(controlMethod);
            ControlPeriodic(controlMethod);
        }
    }
}