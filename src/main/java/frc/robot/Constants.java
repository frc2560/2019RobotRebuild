/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.SerialPort.Port;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 * <p>
 * It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants 
{
    //Joystick
    public static int joy1_id = 0;
    public static int joy2_id = 1;

    //can id
    public static int leftMotor = 1;
    public static int leftFollowMotor = 3;
    public static int rightMotor = 2;
    public static int rightFollowMotor = 4;
    public static int elevatorMotor = 5;

    //multiplier values
    public static double arcaSpeedMulti = 0.85;
    public static double arcaRotateMulti = 0.95;

    //Buttons Controller 1
    public static int oneHP = 1;
    public static int twoHP = 2;
    public static int oneC = 4;
    public static int twoC = 3;
    public static int thrHP = 5;
    public static int thrC = 6;
    //public static int endgameOne = 7;
    //public static int endgameTwo = 8;
    public static int stall = 9;

    //buttons Controller 2
    public static int downElbow = 1;
    public static int liftElbow = 3;
    public static int down = 2;
    public static int up = 4;
    public static int popIn = 5;
    public static int popOut = 6;
    public static int intake = 7;
    public static int outtake = 8;

    //PWM Values
    public static int drop = 0;
    public static int leftIntake = 1;
    public static int rightIntake = 2;

    //DIO Values
    public static int channelA = 0;
    public static int channelB = 1;
    public static int itemLimit = 2;
    public static int ninetyLimit = 3;
    public static int zeroLimit = 4;

    //Solenoid Ports
    public static int hpPortOne = 4;
    public static int hpPortTwo = 5;

    //Navx Port
    public static Port navxPort = Port.kUSB;

    //PID Values
    public static final double kStabilizationP = 1;
    public static final double kStabilizationI = 0.5;
    public static final double kStabilizationD = 0;

    public static final double kTurnP = 1;
    public static final double kTurnI = 0;
    public static final double kTurnD = 0;

    public static final double kTurnToleranceDeg = 5;
    public static final double kTurnRateToleranceDegPerS = 10; // degrees per second
}