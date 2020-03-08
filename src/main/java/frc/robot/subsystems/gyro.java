package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.DriverStation;
/**
 * 
 * @author Hunter P.
 *
 */
public class gyro extends SubsystemBase {
    public static AHRS ahrs;
    public void init() {
        try {
            ahrs = new AHRS(SerialPort.Port.kUSB1);
            //ahrs = new AHRS(SerialPort.Port.kMXP, SerialDataType.kProcessedData, (byte)50);
            ahrs.enableLogging(true);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
    }
    
    //Converts the absolute gyro value to one between -180 and 180
    //Divides the gyro angle by 360 and uses the remainder as the return value
    public double getHeading() {
        try {
         return Math.IEEEremainder(ahrs.getAngle(), 360);
        } catch (RuntimeException ex) 
        {
            DriverStation.reportError("Error Getting heading:  " + ex.getMessage(), true);
            return 0;
        }

    }
    
    public void zero(){
        // Offsets gyro so current heading is zero
        // No affect if currently calibrating 
        ahrs.zeroYaw();
    }

    public void reset(){
        // Causes Navx to recalibrate which will reset heading to zero
        ahrs.reset();
    }
    public void periodic() {
      SmartDashboard.putNumber("Gyro Angle", getHeading());
      
    }
}