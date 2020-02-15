package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
/**
 * 
 * @author Hunter P.
 *
 */
public class gyro extends SubsystemBase {
    private double gyroAngleClamped;
    private double gyroAngleClampedShifted;
    public static AHRS ahrs;
    public void initDefaultCommand() {}
    
    //Converts the absolute gyro value to one between -180 and 180
    //Divides the gyro angle by 360 and uses the remainder as the return value
    public double getHeading() {
        return Math.IEEEremainder(ahrs.getAngle(), 360);
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