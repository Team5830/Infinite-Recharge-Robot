/**
 * @author Hunter Pugh
 */

package frc.robot.misc;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton; 
//import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.DriveTrain;     //Temporary Should only be import commands not subsystems....
                                            // should move TurnToAngle into Drivetrain commands
public final class ControlChooser {

    int safetyChecker;

    //Define Joystick Names

    public static Joystick leftJoy = new Joystick(0);
    public static Joystick rightJoy = new Joystick(1);
    Button leftJoy1  = new JoystickButton(leftJoy, 1); 
    Button leftJoy2  = new JoystickButton(leftJoy, 2);
    Button leftJoy3  = new JoystickButton(leftJoy, 3);
    Button leftJoy4  = new JoystickButton(leftJoy, 4);
    Button leftJoy5  = new JoystickButton(leftJoy, 5);
    Button leftJoy6  = new JoystickButton(leftJoy, 6);
    Button leftJoy7  = new JoystickButton(leftJoy, 7);
    Button leftJoy8  = new JoystickButton(leftJoy, 8);
    Button leftJoy9  = new JoystickButton(leftJoy, 9);

    Button rightJoy1  = new JoystickButton(rightJoy, 1);
    Button rightJoy2  = new JoystickButton(rightJoy, 2);
    Button rightJoy3  = new JoystickButton(rightJoy, 3);
    Button rightJoy4  = new JoystickButton(rightJoy, 4);
    Button rightJoy5  = new JoystickButton(rightJoy, 5);
    Button rightJoy6  = new JoystickButton(rightJoy, 6);
    Button rightJoy7  = new JoystickButton(rightJoy, 7);
    Button rightJoy8  = new JoystickButton(rightJoy, 8);
    Button rightJoy9  = new JoystickButton(rightJoy, 9);
    DriveTrain m_driveTrain;

    public void ControlInit(final int controlMethod) {
        switch (controlMethod) {
        case 0: // Example name: General Flightsticks with DIDBoard
            // Define Joysticks First!
            // Define Buttons Next
            leftJoy3.whenPressed(new TurnToAngle(90.0, m_driveTrain,true).withTimeout(5));
            // Turn to -90 degrees with a profile when the 'A' button is pressed, with a 5 second timeout
            leftJoy4.whenPressed(new TurnToAngle(-90, m_driveTrain,true).withTimeout(5));
            break;

        case 1:// Example name: Arlene
               // Define Joysticks First!
            //exampleJoystick = new Joystick(1);

            // Define Buttons Next
            //exampleButton = new JoystickButton(leftJoy, 4);
            break;
        }
    }

    private void ControlPeriodic(final int controlMethod) {

        safetyChecker = controlMethod;

        switch (controlMethod) {
        case 0: // Example name: General Flightsticks with DIDBoard
            //exampleRobotDriveX = exampleJoystick.getX();
            //exampleRobotDriveY = exampleJoystick.getY();
            break;

        case 1:// Example name: Arlene
            //exampleRobotDriveX = exampleJoystick.getZ();
            //exampleRobotDriveY = exampleJoystick.getX();
            break;
        }
    }

    // This checks to see if the driver has changed control input and will
    // re-initialize if so.
    public void ControlSafety(final int controlMethod) {
        if(controlMethod == safetyChecker){
            ControlPeriodic(controlMethod);
        } else {
            ControlInit(controlMethod);
            ControlPeriodic(controlMethod);
        }
    }
}