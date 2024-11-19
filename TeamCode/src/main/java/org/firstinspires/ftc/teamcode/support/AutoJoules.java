package org.firstinspires.ftc.teamcode.support;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="AutoJoulesTest", group="Robot")
public class AutoJoules extends LinearOpMode
{
    JoulesRobot robot = new JoulesRobot(this);

    public void runOpMode()
    {
        robot.init();
        robot.claw.setPosition(ServoMotorPosConstants.CLAW_CLOSED_POSITION);
        robot.wrist.setPosition(ServoMotorPosConstants.WRIST_DRIVING_POSITION);
        updateTelemetryData();
        waitForStart();

        robot.bucket.setPosition(ServoMotorPosConstants.BUCKET_PICKUP_POSITION);
        linearSlideMove(2100);
        sleep(1500);
        robot.setPowers(.41,.41,.41,.41);
        sleep(1500);
        robot.setPowers(0,0,0,0);
        linearSlideMove(1200);//1350 is on the bar//     1183 is cliped to bar    1210 is release
        sleep(3000);
        linearSlideMove(1250);
        robot.claw.setPosition(ServoMotorPosConstants.CLAW_OPEN_POSITION);
        sleep(1250);
        robot.setPowers(-.40,-.40,-.40,-.40);
        sleep(1250);
        robot.setPowers(0,0,0,0);
        sleep(1250);
        linearSlideMove(0);
        sleep(1000);
        robot.turnTo(Math.PI/2, .8, 0);
        sleep(1000);
        robot.setPowers(.5, .5, .5, .5);
    }


    // This method moves the linear slide to a specified target position
    private void linearSlideMove(int targPos)
    {
        robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.linearSlide.setPower(0.7);
        robot.linearSlide.setTargetPosition(targPos);
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
