/**
 * @author Hunter Pugh
 */

package frc.robot.misc;

import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import com.revrobotics.SparkMax;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.RobotContainer;
import frc.robot.commands.align.TurnToAngle;
import frc.robot.subsystems.shooter;
import frc.robot.commands.shotterdelivery;
import frc.robot.commands.shotterfeeder;
import frc.robot.commands.shotterintake;
public final class ControlChooser {

    int safetyChecker;

    //Define Joystick Names
    Joystick exampleJoystick;

    public static Joystick leftJoy = new Joystick(0);
    public static Joystick rightJoy = new Joystick(1);
    //Define buttons here
    Button leftjoy3 = new JoystickButton(leftJoy,3).whenPressed(new TurnToAngle(20,RobotContainer.m_driveTrain,RobotContainer.m_gyro,true ).withTimeout(5));
    Button leftjoy4 = new JoystickButton(leftJoy,4).whenPressed(new TurnToAngle(-20,RobotContainer.m_driveTrain,RobotContainer.m_gyro,true ).withTimeout(5));
    Button rightjoy1 = new JoystickButton(rightJoy,3).whenPressed(new shotterdelivery());
    Button rightjoy2 = new JoystickButton(rightJoy,1).whileHeld(new shotterfeeder()); 
    Button leftjoy1 = new JoystickButton(leftJoy,1).whileHeld(new shotterintake());
    //These serve as temporary fillers to show how the Chooser methods work.
    //DELETE THESE!
    double exampleRobotDriveX;
    double exampleRobotDriveY;

    public void ControlInit(final int controlMethod) {
        switch (controlMethod) {
        case 0: // Example name: General Flightsticks with DIDBoard
            // Define Joysticks First!
            //exampleJoystick = new Joystick(0);

            // Define Buttons Next
            //exampleButton = new JoystickButton(exampleJoystick, 1);
            break;

        case 1:// Example name: Arlene
               // Define Joysticks First!
            //exampleJoystick = new Joystick(1);

            // Define Buttons Next
           // exampleButton = new JoystickButton(exampleJoystick, 4);
            break;
        }
    }

    private void ControlPeriodic(final int controlMethod) {

        safetyChecker = controlMethod;

        switch (controlMethod) {
        case 0: // Example name: General Flightsticks with DIDBoard
            exampleRobotDriveX = exampleJoystick.getX();
            exampleRobotDriveY = exampleJoystick.getY();
            break;

        case 1:// Example name: Arlene
            exampleRobotDriveX = exampleJoystick.getZ();
            exampleRobotDriveY = exampleJoystick.getX();
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