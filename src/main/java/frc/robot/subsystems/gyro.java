package frc.robot.subsystems;
<<<<<<< HEAD

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;


=======
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
>>>>>>> 830216b79c58d5a917c53291fc96fce21512f61b
/**
 * 
 * @author Hunter P.
 *
 */
public class gyro extends SubsystemBase {
<<<<<<< HEAD

    private double gyroAngleClamped;
    private double gyroAngleClampedShifted;
    public static AHRS ahrs;

=======
    private double gyroAngleClamped;
    private double gyroAngleClampedShifted;
    public static AHRS ahrs;
>>>>>>> 830216b79c58d5a917c53291fc96fce21512f61b
    public void initDefaultCommand() {}
    
    //Converts the absolute gyro value to one between -180 and 180
    //Divides the gyro angle by 360 and uses the remainder as the return value
    public double getGyroClampedNeg180To180() {
        return 0;//Math.IEEEremainder(RobotMap.gyro.getAngle(), 360);
    }
    
    //Converts the absolute gyro value to one between 0 and 360
    //Divides the gyro angle by 360. If remainder is less than 0, add 360, otherwise leave it alone. Return that value.
    public double getGyroClampedTo360() {
        
        gyroAngleClamped = 0;//Math.IEEEremainder(RobotMap.gyro.getAngle(), 360);
        
        if(gyroAngleClamped < 0) {
            gyroAngleClampedShifted = Math.abs(gyroAngleClamped + 360);
        } else {
            gyroAngleClampedShifted = gyroAngleClamped;
        }
        return gyroAngleClampedShifted;
    }
    public void periodic() {
      SmartDashboard.putNumber("Gyro Angle", ahrs.getAngle());
      
    }
}