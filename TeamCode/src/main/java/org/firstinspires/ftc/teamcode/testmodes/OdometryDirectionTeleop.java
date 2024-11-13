package org.firstinspires.ftc.teamcode.testmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.support.PIDRobot;

@TeleOp(name="OdometryDirection",group="Testing")

public class OdometryDirectionTeleop extends LinearOpMode {

    final double PRECISE_MOVEMENT = 0.2;           // Maximum Motor Output?

    PIDRobot robot = new PIDRobot(this,false);

    @Override
    public void runOpMode()
    {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        robot.init(true);

        while(opModeInInit())
        {
            telemetry.addData(">","Touch Play to Start");

            robot.readSensors();
            telemetry.update();
        }

        while(opModeIsActive())
        {
            // Read the current position, heading, and other sensors
            robot.readSensors();

            // Manually reset gyros and positions
            if(gamepad1.options && gamepad1.share)
            {
                robot.resetHeading();
                robot.resetOdometry();
            }

            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;

            // Check turn direction, might need to make this negative
            double turn = gamepad1.right_stick_x;

            // Use the d-pad for fine tuned movements
            // TODO: check that these directions are correct
            if(gamepad1.dpad_left)
            {
                x = -PRECISE_MOVEMENT;
            }
            else if(gamepad1.dpad_right)
            {
                x = PRECISE_MOVEMENT;
            }
            else if(gamepad1.dpad_up)
            {
                y = -PRECISE_MOVEMENT;
            }
            else if(gamepad1.dpad_down)
            {
                y = PRECISE_MOVEMENT;
            }

            robot.moveRobot(x,y,turn,1);
        }
    }
}
