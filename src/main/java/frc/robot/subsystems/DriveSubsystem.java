package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;


public class DriveSubsystem extends SubsystemBase {

// Any variables/fields used in the constructor must appear before the "INSTANCE" variable
// so that they are initialized before the constructor is called.

    private WPI_TalonSRX left, right;
    private WPI_VictorSPX leftFollow, rightFollow;
    private AHRS navx;
    private DifferentialDrive myDrive;

    private final static DriveSubsystem INSTANCE = new DriveSubsystem();

    public DriveSubsystem()
    {
        left = new WPI_TalonSRX(Constants.leftMotor);
        left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        right = new WPI_TalonSRX(Constants.rightMotor);
        right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);


        //follower drive motors
        leftFollow = new WPI_VictorSPX(Constants.leftFollowMotor);
        rightFollow = new WPI_VictorSPX(Constants.rightFollowMotor);

        //setting the controllers to follow the others
        leftFollow.follow(left);
        rightFollow.follow(right);

        myDrive = new DifferentialDrive(left, right);

        try
        {
            navx = new AHRS(Constants.navxPort);
        }
        catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
        }


    }

    public void aDrive(double speed, double rotate)
    {
        myDrive.arcadeDrive(speed, rotate);
    }

    public static DriveSubsystem getInstance() {
        return INSTANCE;
    }

    public void setMaxOutput(double v) {
        myDrive.setMaxOutput(v);
    }

    public double getTurnRate() {
        return navx.getRate();
    }

    public double getHeading() {
        return navx.getYaw();
    }
}

