package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.support.PIDRobot;

public class SampleTeleop extends LinearOpMode {

    final double PRECISE_MOVEMENT = 0.2;

    // Used to keep the robot facing the same direction if bumped
    boolean autoHeading = false;

    PIDRobot robot = new PIDRobot(this,false);

    @Override
    public void runOpMode() throws InterruptedException
    {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        robot.init(true);

        while(opModeInInit())
        {
            telemetry.addData(">", "Touch Play to Start");
            robot.readSensors();
            telemetry.update();
        }

        while(opModeIsActive())
        {
            robot.readSensors();

            gamepad1Controls();

            gamepad2Controls();

            telemetry.update();
        }
    }

    /**
     * gamepad1 Controls
     *  - Left Joystick for Drive/Strafe
     *  - Right Joystick for Turn
     *  - D-Pad for controlled movement
     *  - Buttons for snap to angle turning
     */
    private void gamepad1Controls() {
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double turn = -gamepad1.right_stick_x;

        // If using the dPad, overwrite the values of x and y
        if (gamepad1.dpad_left) {
            x = -PRECISE_MOVEMENT;
        } else if (gamepad1.dpad_right) {
            x = PRECISE_MOVEMENT;
        } else if (gamepad1.dpad_up) {
            y = -PRECISE_MOVEMENT;
        } else if (gamepad1.dpad_down) {
            y = PRECISE_MOVEMENT;
        }

        // Turn off Auto Heading when driver trying to turn
        if (Math.abs(turn) > 0.05) {
            autoHeading = false;
        } else
        {
            // Driver not turning the robot and not locked into heading
            if(!autoHeading && Math.abs(robot.getTurnRate()) < 2.0)
            {
                robot.yawController.reset(robot.getHeading());
                autoHeading = true;
            }
        }

        // 90 degree rotations from starting orientation
        if(gamepad1.y)
        {
            robot.yawController.reset(0);
            autoHeading = true;
        }
        else if(gamepad1.x)
        {
            robot.yawController.reset(Math.PI/2);
            autoHeading = true;
        }
        else if(gamepad1.a)
        {
            robot.yawController.reset(Math.PI);
            autoHeading = true;
        }
        else if(gamepad1.b)
        {
            robot.yawController.reset(-Math.PI / 2);
            autoHeading = true;
        }

        // Telemetry on desired heading and current heading
        telemetry.addData("Heading :: ",robot.yawController.getSetpoint());
        telemetry.addData("Current Angle :: ",robot.heading);

        if(autoHeading)
        {
            turn = robot.yawController.getOutput(robot.getHeading());
        }
        robot.moveRobot(x,y,turn,1);
    }

    public void gamepad2Controls()
    {
        // Nothing to do here
    }
}
