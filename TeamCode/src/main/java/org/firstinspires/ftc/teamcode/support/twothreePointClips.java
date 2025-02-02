package org.firstinspires.ftc.teamcode.support;



import static org.firstinspires.ftc.teamcode.support.ServoMotorPosConstants.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="twothreePointClips", group="Robot")
public class twothreePointClips extends LinearOpMode
{
    JoulesRobot robot = new JoulesRobot(this, 0, false);

    public void runOpMode()
    {
        ElapsedTime timer = new ElapsedTime();
        robot.init();
        robot.imu.resetYaw();
        updateTelemetryData();
        waitForStart();

        robot.bucket.setPosition(BUCKET_PICKUP_POSITION);
        robot.claw.setPosition(CLAW_CLOSED_POSITION);
        robot.wrist.setPosition(WRIST_DRIVING_POSITION);
        robot.bucket.setPosition(BUCKET_PICKUP_POSITION);

        //Linear Slide position to above bar
        linearSlideMove(LINEAR_SLIDE_SPECIMEN_UPPER_POSITION);

        //Goes to the specimen hanging bar
        sleep(150);
        robot.drive(-31.3, .7, AUTON_PID_HOLD_TIME, 1.5);
        robot.linearSlide.setPower(0);

        robot.clipSpecimenAction();

        //going to wall to grab spec #2
        robot.drive(15, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME, 1.0);
        robot.strafe(45, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME, 2.5);
        robot.turnTo(radians(180), MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME, 2.0);
        robot.drive(-20, .7, AUTON_PID_HOLD_TIME, 1.5);
        robot.claw.setPosition(CLAW_CLOSED_POSITION);
        sleep(1400);

        //go back to bar to put spec #2 on
        linearSlideMove(50);
        robot.drive(10.25, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME, 1.0);
        linearSlideMove(0);
        robot.strafe(50, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME, 3.0);
        robot.turnTo(0, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME, 1.5);

        linearSlideMove(LINEAR_SLIDE_SPECIMEN_UPPER_POSITION);
        sleep(1000);
        robot.drive(-23.4, .7, AUTON_PID_HOLD_TIME, 2.0);

        robot.clipSpecimenAction();

        robot.drive(23, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME, 2.0);
        robot.strafe(60, 1, AUTON_PID_HOLD_TIME, 2.0);
        sleep(1000);
        robot.wrist.setPosition(WRIST_INTAKE_POSITION);
        robot.bucket.setPosition(BUCKET_RELEASE_POSITION);


/*
        linearSlideMove(50);
        robot.drive(10, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);
        linearSlideMove(0);
        robot.strafe(-35, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);
        robot.turnTo(0, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);
        linearSlideMove(LINEAR_SLIDE_SPECIMEN_UPPER_POSITION);
        sleep(180);
        robot.drive(-15, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);
        linearSlideMove(LINEAR_SLIDE_SPECIMEN_LOWER_POSITION);
        robot.claw.setPosition(CLAW_OPEN_POSITION);
        sleep(2000);



/*


        //able to close claw and grab first of four specimens
        robot.drive(10, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);
        robot.strafe(-10, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);
        linearSlideMove(LINEAR_SLIDE_SPECIMEN_WALL_POSITION);
        robot.drive(-10, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);

        for (int i = 0; i < 4; i++)
        {
            robot.claw.setPosition(CLAW_CLOSED_POSITION);

            //pull away from wall with specimen
            robot.drive(10, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);
            robot.linearSlide.setPower(0);
            robot.strafe(-25 + 2 * i, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);
            robot.turnTo(radians(180), MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);

            //put specimen over bar
            linearSlideMove(LINEAR_SLIDE_SPECIMEN_UPPER_POSITION);
            robot.drive(-20, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);
            robot.linearSlide.setPower(0);

            //put it on bar and let go
            linearSlideMove(LINEAR_SLIDE_SPECIMEN_LOWER_POSITION);
            sleep(1000);
            robot.linearSlide.setPower(0);
            robot.claw.setPosition(CLAW_OPEN_POSITION);

            //go back to about to grab specimen from wall
            robot.drive(20, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);
            robot.strafe(-25 + 2 * i, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);
            robot.turnTo(radians(180), MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);

            if (i != 3)
                linearSlideMove(LINEAR_SLIDE_SPECIMEN_WALL_POSITION);

            robot.drive(-10, MAX_DRIVING_POWER, AUTON_PID_HOLD_TIME);



        }

*/
    }

    private void linearSlideMove(int targPos)
    {
        robot.linearSlide.setTargetPosition(targPos);
        robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.linearSlide.setPower(LINEAR_SLIDE_POWER);
    }

    private double radians(double degrees)
    {
        double radians = degrees * Math.PI / 180;
        return radians;
    }

    private void updateTelemetryData() {
        telemetry.addData("Subsystem Data ", "-----")
                .addData("Slide Position: ", robot.linearSlide.getCurrentPosition())
                .addData("Bucket Position: ", robot.bucket.getPosition())
                .addData("Trolley Positions: \nleft: ", robot.left.getPosition())
                .addData("Right: ", robot.right.getPosition())
                .addData("Claw Position: ", robot.claw.getPosition())
                .addData("Wrist Position: ", robot.wrist.getPosition())
                .addData("Happiness Score: ", "10/10");



        telemetry.update();
    }
}
