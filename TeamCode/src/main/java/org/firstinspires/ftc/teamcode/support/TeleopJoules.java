package org.firstinspires.ftc.teamcode.support;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleopJoules", group="Robot")
public class TeleopJoules extends LinearOpMode
{
    JoulesRobot robot = new JoulesRobot(this);
    boolean firstTime = true;
    public void runOpMode() {
        robot.init();
        updateTelemetryData();
        waitForStart();

        //goDefaultPositions();


        while (opModeIsActive())
        {
            if (firstTime)
            {
                onFirstRun();
                firstTime = false;
            }

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

    public void onFirstRun()
    {
        robot.wrist.setPosition(ServoMotorPosConstants.WRIST_DRIVING_POSITION);
        robot.right.setPosition(ServoMotorPosConstants.ARM_IN_POSITION);
        robot.left.setPosition(ServoMotorPosConstants.ARM_IN_POSITION);
        robot.bucket.setPosition(ServoMotorPosConstants.BUCKET_PICKUP_POSITION);
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
        if (Math.abs(gamepad2.right_stick_y) > ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            int targPos =  robot.linearSlide.getTargetPosition() + (int) (-5 * gamepad2.right_stick_y);
            if (targPos > ServoMotorPosConstants.LINEAR_SLIDE_SECOND_BUCKET_POSITION)
                targPos = ServoMotorPosConstants.LINEAR_SLIDE_SECOND_BUCKET_POSITION;
            else if (targPos < ServoMotorPosConstants.LINEAR_SLIDE_STARTING_POSITION)
                targPos = ServoMotorPosConstants.LINEAR_SLIDE_STARTING_POSITION;
            robot.linearSlide.setTargetPosition(targPos);
            robot.linearSlide.setPower(-gamepad2.right_stick_y * ServoMotorPosConstants.LINEAR_SLIDE_POWER);
        }
        else
        {
            robot.linearSlide.setPower(0.0);
        }
    }

    public void slidesPresetHeights()
    {
        // TODO: This may cause a skip of the middle position if it reads one button press as several
        // I don't think we need a middle position
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
        else
        {
            robot.intake.setPower(0);
        }
    }

    public void driving()
    {
        double y = -gamepad1.left_stick_y;
        double x = -gamepad1.left_stick_x;
        double turn = -gamepad1.right_stick_x;

        if (Math.abs(y) < ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            y = 0;
        }
        if (Math.abs(x) < ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            x = 0;
        }
        if (Math.abs(turn) < ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            turn = 0;
        }


        if (gamepad1.left_bumper)
        {
            robot.moveRobot(x, y, turn, ServoMotorPosConstants.MAX_DRIVING_POWER / 2);
        }
        else
        {
            robot.moveRobot(x, y, turn, ServoMotorPosConstants.MAX_DRIVING_POWER);
        }
    }

    static <T extends Comparable<T>> T clamp(T value, T min, T max) {
        if (value.compareTo(min) < 0) {
            return min;
        } else if (value.compareTo(max) > 0) {
            return max;
        } else {
            return value;
        }
    }
    public void trolley()
    {
        double pad1RightTrigger = gamepad1.right_trigger;
        double pad1LeftTrigger = gamepad1.left_trigger;

        if (Math.abs(gamepad1.right_trigger) < ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            pad1RightTrigger = 0;
        }
        if (Math.abs(gamepad1.left_trigger) < ServoMotorPosConstants.DEADZONE_THRESHOLD)
        {
            pad1LeftTrigger = 0;
        }

        double newArmPosition = (robot.right.getPosition() + robot.left.getPosition()) / 2;

        if (pad1RightTrigger != 0) {
            newArmPosition -= (pad1RightTrigger /
                    300);
        } else if (pad1LeftTrigger != 0) {
            newArmPosition += (pad1LeftTrigger / 300);
        }
        // Makes sure the arm can't go out of the min or max
        newArmPosition = clamp(newArmPosition, ServoMotorPosConstants.ARM_OUT_POSITION, ServoMotorPosConstants.ARM_IN_POSITION);
        robot.right.setPosition(newArmPosition);
        robot.left.setPosition(newArmPosition);
    }

    public void claw()
    {
        if (gamepad1.x)
        {
            robot.claw.setPosition(ServoMotorPosConstants.CLAW_CLOSED_POSITION);
        }
        else if (gamepad1.b)
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
