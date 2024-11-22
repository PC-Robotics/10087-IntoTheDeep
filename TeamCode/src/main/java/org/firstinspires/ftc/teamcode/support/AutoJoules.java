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
        robot.linearSlide.setTargetPosition(2100);
        robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.linearSlide.setPower(1);
        sleep(700);
        robot.setPowers(0.429, 0.429, 0.429, 0.429);
        sleep(1450);
        robot.setPowers(0,0,0,0);
        sleep(300);
        robot.linearSlide.setTargetPosition(1186);//1350 is on the bar//     1183 is cliped to bar    1210 is release
        sleep(500);
        robot.linearSlide.setTargetPosition(1230);
        robot.linearSlide.setPower(0.7);
        sleep(400);
        //1350 is on the bar//     1183 is cliped to bar    1210 is release

        robot.claw.setPosition(ServoMotorPosConstants.CLAW_OPEN_POSITION);
        sleep(1000);
        robot.setPowers(-0.42, -0.42, -0.42, -0.42);
        sleep(1000);
        robot.setPowers(0,0,0,0);
        //robot.drive(21, .3, 1000);
        robot.linearSlide.setTargetPosition(0);
        sleep(1000);
        robot.setPowers(.65, -.65, -.65,.65);
        sleep(2500);
        robot.setPowers(0,0,0,0);
        sleep(500);
        robot.setPowers(0.7,-0.7,0.7,-0.7);
        sleep(1000);
        robot.claw.setPosition(ServoMotorPosConstants.CLAW_CLOSED_POSITION);
        robot.setPowers(.1,.1,.1,.1);
        /*
        robot.setPowers(.43,-.43,.43,-.43);
        sleep(1000);
        robot.setPowers(-.7,-.7,-.7,-.7);
        sleep(1500);
        robot.setPowers(0,0,0,0);
        */



        // robot.strafe(-24, .3, 1000);
    }


    // This method moves the linear slide to a specified target position
    private void linearSlideMove(int linearSlideMove)
    {
        robot.linearSlide.setTargetPosition(linearSlideMove);
        robot.linearSlide.setPower(0.7);
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
