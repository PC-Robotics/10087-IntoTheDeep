package org.firstinspires.ftc.teamcode.support;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleopJoules", group="Robot")
public class TeleopJoules extends LinearOpMode
{
    JoulesRobot robot = new JoulesRobot(this);

    public void runOpMode() {
        robot.init();
        waitForStart();

        goDefaultPositions();

        // TODO: This codd only runs once, it needs to be in a While Loop. See BasicOpMode_Linear sample

        while (opModeIsActive())
        {
            manualLinSlideMove();
            slidesPresetHeights();
            bucketTilts();
            wristMovement();
            intakeSpins();
            driving();
            trolley();
            claw();

            updateTelemetryData();
        }
    }

    public void goDefaultPositions()
    {
        //set intake servo positions
        robot.left.setPosition(ServoMotorPosConstants.ARM_IN_POSITION);
        robot.right.setPosition(ServoMotorPosConstants.ARM_IN_POSITION);
        robot.wrist.setPosition(ServoMotorPosConstants.WRIST_DRIVING_POSITION);

        //set outtake servo positions
        robot.bucket.setPosition(ServoMotorPosConstants.BUCKET_PICKUP_POSITION);
        robot.claw.setPosition(ServoMotorPosConstants.CLAW_OPEN_POSITION);

        robot.linearSlide.setTargetPosition(ServoMotorPosConstants.LINEAR_SLIDE_STARTING_POSITION);
        robot.linearSlide.setPower(ServoMotorPosConstants.LINEAR_SLIDE_POWER);
        while (robot.linearSlide.isBusy()) {
            sleep(10);
        }
        robot.linearSlide.setPower(0);
    }

    public void manualLinSlideMove()
    {
        // TODO: this should be checked with the slides in middle position in case the stick is reversed
        if (Math.abs(gamepad2.right_stick_y) > ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            int targPos =  robot.linearSlide.getTargetPosition() + (int) (5 * gamepad2.right_stick_y);
            if (targPos > ServoMotorPosConstants.LINEAR_SLIDE_SECOND_BUCKET_POSITION)
                targPos = ServoMotorPosConstants.LINEAR_SLIDE_SECOND_BUCKET_POSITION;
            else if (targPos < ServoMotorPosConstants.LINEAR_SLIDE_STARTING_POSITION)
                targPos = ServoMotorPosConstants.LINEAR_SLIDE_STARTING_POSITION;
            robot.linearSlide.setTargetPosition(targPos);
            robot.linearSlide.setPower(gamepad2.right_stick_y * ServoMotorPosConstants.LINEAR_SLIDE_POWER);
        }
    }

    public void slidesPresetHeights()
    {
        // TODO: This may cause a skip of the middle position if it reads one button press as several
        if (gamepad2.dpad_up)
        {
            robot.linearSlide.setTargetPosition(ServoMotorPosConstants.LINEAR_SLIDE_SECOND_BUCKET_POSITION);
            robot.linearSlide.setPower(ServoMotorPosConstants.LINEAR_SLIDE_POWER);
        }

        else if (gamepad2.dpad_down)
        {
            robot.linearSlide.setTargetPosition(ServoMotorPosConstants.LINEAR_SLIDE_STARTING_POSITION);
            robot.linearSlide.setPower(ServoMotorPosConstants.LINEAR_SLIDE_POWER);
        }
    }

    public void bucketTilts()
    {
        if (gamepad2.triangle)
        {
            robot.bucket.setPosition(ServoMotorPosConstants.BUCKET_RELEASE_POSITION);
        }
        else if (gamepad2.cross)
        {
            robot.bucket.setPosition(ServoMotorPosConstants.BUCKET_PICKUP_POSITION);
        }
    }

    public void wristMovement()
    {
        if (Math.abs(gamepad2.right_trigger) > ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            robot.wrist.setPosition(ServoMotorPosConstants.WRIST_INTAKE_POSITION);
        }
        else if (Math.abs(gamepad2.left_trigger) > ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            robot.wrist.setPosition(ServoMotorPosConstants.WRIST_RELEASE_POSITION);
        }
    }

    public void intakeSpins()
    {
        if (gamepad2.right_bumper)
        {
            robot.intake.setPower(ServoMotorPosConstants.MAX_INTAKE_POWER);
        }
        else if (gamepad2.left_bumper)
        {
            robot.intake.setPower(-ServoMotorPosConstants.MAX_INTAKE_POWER);
        }
    }

    public void driving()
    {
        int vertBool = 0;
        int horBool = 0;
        int turnBool = 0;

        // TODO: These need to be combined to make ONE call to robot.moveRobot with all three values.
        if (Math.abs(gamepad1.left_stick_y) > ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            vertBool = 1;
        }
        if (Math.abs(gamepad1.left_stick_x) > ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            horBool = 1;
        }
        if (Math.abs(gamepad1.right_stick_x) > ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            turnBool = 1;
        }

        robot.moveRobot(gamepad1.left_stick_x * horBool, gamepad1.left_stick_y * vertBool, gamepad1.right_stick_x * turnBool, ServoMotorPosConstants.MAX_DRIVING_POWER);
        // TODO: Suggestion to add some button controls for more precise movement to make small adjustments
    }

    public void trolley()
    {
        if (Math.abs(gamepad1.right_trigger) > ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            double pos = robot.left.getPosition() - 0.02;
            if (pos < ServoMotorPosConstants.ARM_OUT_POSITION)
                pos = ServoMotorPosConstants.ARM_OUT_POSITION;
            robot.left.setPosition(pos);
            robot.right.setPosition(pos);
        }
        if (Math.abs(gamepad1.left_trigger) > ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            double pos = robot.left.getPosition() + 0.02;
            if (pos > ServoMotorPosConstants.ARM_IN_POSITION)
                pos = ServoMotorPosConstants.ARM_OUT_POSITION;
            robot.left.setPosition(pos);
            robot.right.setPosition(pos);
        }
    }

    public void claw()
    {
        if (gamepad1.x)
        {
            robot.claw.setPosition(ServoMotorPosConstants.CLAW_CLOSED_POSITION);
        }
        if (gamepad1.b)
        {
            robot.claw.setPosition(ServoMotorPosConstants.CLAW_OPEN_POSITION);
        }
    }

    private void updateTelemetryData() {
        telemetry.addData("Subsystem Data ", "-----")
                .addData("Slide Position: ", robot.linearSlide.getCurrentPosition())
                .addData("Bucket Position: ", robot.bucket.getPosition())
                .addData("Trolley Positions: \nLeft: ", robot.left.getPosition())
                .addData("Right: ", robot.right.getPosition())
                .addData("Claw Position: ", robot.claw.getPosition())
                .addData("Wrist Position: ", robot.wrist.getPosition())
                .addData("Happiness Score: ", "10/10");



        telemetry.update();
    }
}
