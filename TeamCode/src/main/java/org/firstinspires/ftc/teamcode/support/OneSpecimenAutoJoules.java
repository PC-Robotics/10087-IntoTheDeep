package org.firstinspires.ftc.teamcode.support;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name="OneSpecimenAutoJoules", group="Robot")
public class OneSpecimenAutoJoules extends LinearOpMode
{
    JoulesRobot robot = new JoulesRobot(this, 0, false);

    public void runOpMode()
    {
        robot.init();
        robot.claw.setPosition(ServoMotorPosConstants.CLAW_OPEN_POSITION);
        sleep(3000);
        robot.claw.setPosition(ServoMotorPosConstants.CLAW_CLOSED_POSITION);
        updateTelemetryData();
        waitForStart();

        robot.claw.setPosition(ServoMotorPosConstants.CLAW_CLOSED_POSITION);

        //Linear Slide position to above bar
        slideToPos(ServoMotorPosConstants.LINEAR_SLIDE_SPECIMEN_UPPER_POSITION);

        //Goes to the specimen hanging bar
        robot.drive(20, ServoMotorPosConstants.MAX_DRIVING_POWER, 0.2);

        //Putting the specimen on the bar
        slideToPos(ServoMotorPosConstants.LINEAR_SLIDE_SPECIMEN_LOWER_POSITION);
        sleep(1000);
        robot.linearSlide.setPower(0);
        robot.claw.setPosition(ServoMotorPosConstants.CLAW_OPEN_POSITION);

        robot.drive(-18, ServoMotorPosConstants.MAX_DRIVING_POWER, 0.2);
        robot.strafe(24, ServoMotorPosConstants.MAX_DRIVING_POWER, 0.2);



    }

    private void slideToPos(int pos)
    {
        robot.linearSlide.setTargetPosition(pos);
        robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.linearSlide.setPower(ServoMotorPosConstants.LINEAR_SLIDE_POWER);
    }

    private double radians(double degrees)
    {
        return degrees * Math.PI / 180;
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
