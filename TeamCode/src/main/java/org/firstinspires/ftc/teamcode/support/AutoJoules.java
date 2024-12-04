package org.firstinspires.ftc.teamcode.support;



import static org.firstinspires.ftc.teamcode.support.ServoMotorPosConstants.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="AutoJoules", group="Robot")
public class AutoJoules extends LinearOpMode
{
    JoulesRobot robot = new JoulesRobot(this);

    public void runOpMode()
    {
        robot.init();
        robot.claw.setPosition(CLAW_CLOSED_POSITION);
        robot.wrist.setPosition(WRIST_DRIVING_POSITION);
        updateTelemetryData();
        waitForStart();

        robot.claw.setPosition(CLAW_CLOSED_POSITION);

        //Linear Slide position to above bar
        linearSlideMove(LINEAR_SLIDE_SPECIMEN_UPPER_POSITION);

        //Goes to the specimen hanging bar
        robot.drive(20, MAX_DRIVING_POWER, 0.2);

        //Putting the specimen on the bar
        linearSlideMove(LINEAR_SLIDE_SPECIMEN_LOWER_POSITION);
        sleep(1000);
        robot.linearSlide.setPower(0);
        robot.claw.setPosition(CLAW_OPEN_POSITION);

        for (int i = 0; i < 3; i++)
        {
            //pull away from bar
            robot.drive(-10, MAX_DRIVING_POWER, 0.2);

            //positioning above a sample about to push into human zone
            robot.strafe(36, MAX_DRIVING_POWER, 0.2);
            robot.drive(30, MAX_DRIVING_POWER, 0.2);
            robot.strafe(10 + 6 * i, MAX_DRIVING_POWER, 0.2);

            //go to human zone while dragging a pixel
            robot.turnTo(radians(180), MAX_DRIVING_POWER, 0.2);
            robot.drive(50, MAX_DRIVING_POWER, 0.2);

            //go to specimen grabbing spot
            robot.strafe(3 + 6 * i, MAX_DRIVING_POWER, 0.2);

            //mission success?
            robot.claw.setPosition(CLAW_CLOSED_POSITION);




        }
    }

    private void linearSlideMove(int targPos)
    {
        robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.linearSlide.setTargetPosition(targPos);
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
