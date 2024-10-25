package org.firstinspires.ftc.teamcode.support;

import com.acmerobotics.dashboard.config.Config;

/**
 * Constants using the @Config tag that enables them to be modified on the FTC Dashboard
 */
@Config
public class ConfigConstants
{
    // PID Control constants
    public static double DRIVE_KP = 0.35;
    public static double DRIVE_KI = 0.08;
    public static double DRIVE_KD = 0.045;


    public static double STRAFE_KP = 0.36;
    public static double STRAFE_KI = 0.1;
    public static double STRAFE_KD = 0.075;


    public static double YAW_KP = 1.8;
    public static double YAW_KI = 0.1;
    public static double YAW_KD = 0.1;

    public static String TESTED_SERVO = "testServo";

}
