package org.firstinspires.ftc.teamcode.support;



import static org.firstinspires.ftc.teamcode.support.ServoMotorPosConstants.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="BucketAutoJoules", group="Robot")
public class BucketAutoJoules extends LinearOpMode
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

        //pull away from bar
        robot.drive(-10, MAX_DRIVING_POWER, 0.2);

        //positioning above a sample about to grab with rotator
        robot.strafe(-36, MAX_DRIVING_POWER, 0.2);
        robot.drive(30, MAX_DRIVING_POWER, 0.2);
        robot.strafe(10, MAX_DRIVING_POWER, 0.2);

        //rotate the robot and intake down
        robot.turnTo(radians(180), MAX_DRIVING_POWER, 0.2);
        robot.wrist.setPosition(WRIST_INTAKE_POSITION);

        //intake the thing
        robot.intake.setPower(MAX_INTAKE_POWER);
        robot.drive(10, MAX_DRIVING_POWER, 0.2);

        //put it in the bucket
        robot.bucket.setPosition(BUCKET_PICKUP_POSITION);
        robot.wrist.setPosition(WRIST_RELEASE_POSITION);
        robot.intake.setPower(-MAX_INTAKE_POWER);

        //drive to looking at the bucket
        robot.drive(20, MAX_DRIVING_POWER, 0.2);
        robot.turnTo(radians(-45), MAX_DRIVING_POWER, 0.2);

        //put the sample in the bucket
        linearSlideMove(LINEAR_SLIDE_HIGHEST_POSITION);
        robot.drive(10, MAX_DRIVING_POWER, 0.2);
        robot.linearSlide.setPower(0);
        robot.bucket.setPosition(BUCKET_RELEASE_POSITION);

        //return to before bucket adventure
        robot.drive(-10, MAX_DRIVING_POWER, 0.2);
        linearSlideMove(LINEAR_SLIDE_STARTING_POSITION);
        robot.turnTo(radians(-45), MAX_DRIVING_POWER, 0.2);
        robot.linearSlide.setPower(0);

        //drive to above next sample
        robot.drive(-30, MAX_DRIVING_POWER, 0.2);
        robot.strafe(10, MAX_DRIVING_POWER, 0.2);

        //intake the thing 2
        robot.intake.setPower(MAX_INTAKE_POWER);
        robot.drive(10, MAX_DRIVING_POWER, 0.2);

        //go to before bucket adventure 2
        robot.strafe(-10, MAX_DRIVING_POWER, 0.2);
        robot.turnTo(radians(-45), MAX_DRIVING_POWER, 0.2);

        //put the sample in the bucket 2
        linearSlideMove(LINEAR_SLIDE_HIGHEST_POSITION);
        robot.drive(10, MAX_DRIVING_POWER, 0.2);
        robot.linearSlide.setPower(0);
        robot.bucket.setPosition(BUCKET_RELEASE_POSITION);

        //return to before bucket adventure 2
        robot.drive(-10, MAX_DRIVING_POWER, 0.2);
        linearSlideMove(LINEAR_SLIDE_STARTING_POSITION);
        robot.turnTo(radians(-45), MAX_DRIVING_POWER, 0.2);
        robot.linearSlide.setPower(0);

        //drive to right of third sample facing it
        robot.drive(-20, MAX_DRIVING_POWER, 0.2);
        robot.strafe(20, MAX_DRIVING_POWER, 0.2);
        robot.turnTo(radians(-90), MAX_DRIVING_POWER, 0.2);

        //intake the third sample
        robot.intake.setPower(MAX_INTAKE_POWER);
        robot.drive(10, MAX_DRIVING_POWER, 0.2);


        //put the sample in the bucket 3
        linearSlideMove(LINEAR_SLIDE_HIGHEST_POSITION);
        robot.drive(10, MAX_DRIVING_POWER, 0.2);
        robot.linearSlide.setPower(0);
        robot.bucket.setPosition(BUCKET_RELEASE_POSITION);

        //drive away from wall and get ready for bucket adventure
        robot.drive(-30, MAX_DRIVING_POWER, 0.2);
        robot.turnTo(radians(90), MAX_DRIVING_POWER, 0.2);
        robot.drive(30, MAX_DRIVING_POWER, 0.2);
        robot.turnTo(radians(-45), MAX_DRIVING_POWER, 0.2);


        //put the sample in the bucket 3
        linearSlideMove(LINEAR_SLIDE_HIGHEST_POSITION);
        robot.drive(10, MAX_DRIVING_POWER, 0.2);
        robot.linearSlide.setPower(0);
        robot.bucket.setPosition(BUCKET_RELEASE_POSITION);

        //return to before bucket adventure 3
        robot.drive(-10, MAX_DRIVING_POWER, 0.2);
        linearSlideMove(LINEAR_SLIDE_STARTING_POSITION);
        robot.turnTo(radians(-45), MAX_DRIVING_POWER, 0.2);
        robot.linearSlide.setPower(0);

        //go to white pentagon area
        robot.drive(-30, MAX_DRIVING_POWER, 0.2);
        robot.turnTo(radians(-90), MAX_DRIVING_POWER, 0.2);
        robot.drive(-15, MAX_DRIVING_POWER, 0.2);

        //touch hanging thing for points
        linearSlideMove(LINEAR_SLIDE_TOUCHING_POSITION);
        sleep(1000);
        robot.linearSlide.setPower(0);



    }

    private void linearSlideMove(int targPos)
    {
        robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.linearSlide.setTargetPosition(targPos);
        robot.linearSlide.setPower(LINEAR_SLIDE_POWER);
    }

    private double radians(double degrees)
    {
        return degrees * Math.PI / 180;
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
