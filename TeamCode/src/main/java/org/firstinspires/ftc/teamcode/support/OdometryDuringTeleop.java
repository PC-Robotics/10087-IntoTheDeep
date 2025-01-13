package org.firstinspires.ftc.teamcode.support;

import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class OdometryDuringTeleop
{
    PIDRobot robot = null;

    public OdometryDuringTeleop(PIDRobot robot)
    {
        this.robot = robot;
    }

    public static double odometryChangeInPos(double newDrivePos, double newStrafePos, double oldHeading, double newHeading)
    {
        return 0.05;
    }

}