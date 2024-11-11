package org.firstinspires.ftc.teamcode.support;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="AutoJoules", group="Robot")
public class AutoJoules extends LinearOpMode
{
    JoulesRobot robot = new JoulesRobot(this);

    public void runOpMode()
    {
        robot.init();
        robot.claw.setPosition(ServoMotorPosConstants.CLAW_CLOSED_POSITION);
        updateTelemetryData();
        waitForStart();


        robot.setPowers(0.5, 0.5, 0.5, 0.5);
        sleep(1000);
        robot.setPowers(0, 0, 0, 0);
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
