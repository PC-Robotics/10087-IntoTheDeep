package org.firstinspires.ftc.teamcode.support;

import com.acmerobotics.dashboard.config.Config;

/**
 * Constants using the @Config tag that enables them to be modified on the FTC Dashboard
 */
@Config
public class ConfigConstants
{
    // PID Control constants
    public static double DRIVE_KP = 0.14;   // ( possible 0.45)
    public static double DRIVE_KI = 0.6;
    public static double DRIVE_KD = 0.03;


    public static double STRAFE_KP = 0.2;
    public static double STRAFE_KI = 0.33;
    public static double STRAFE_KD = 0.08;


    public static double YAW_KP = 2.15;
    public static double YAW_KI = 0.63;
    public static double YAW_KD = 0.31;

    public static String TESTED_SERVO = "wrist";

}
