package org.firstinspires.ftc.teamcode.support;

import static org.firstinspires.ftc.teamcode.support.ServoMotorPosConstants.BUCKET_PICKUP_POSITION;
import static org.firstinspires.ftc.teamcode.support.ServoMotorPosConstants.CLAW_CLOSED_POSITION;
import static org.firstinspires.ftc.teamcode.support.ServoMotorPosConstants.CLAW_OPEN_POSITION;
import static org.firstinspires.ftc.teamcode.support.ServoMotorPosConstants.LINEAR_SLIDE_POWER;
import static org.firstinspires.ftc.teamcode.support.ServoMotorPosConstants.WRIST_DRIVING_POSITION;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Preload Specimen", group="Robot")
public class PreloadSpecimen extends LinearOpMode {
    JoulesRobot robot = new JoulesRobot(this, 0, false);

    public void runOpMode() throws InterruptedException {
        robot.init();
        robot.imu.resetYaw();
        waitForStart();

        robot.bucket.setPosition(BUCKET_PICKUP_POSITION);
        robot.claw.setPosition(CLAW_CLOSED_POSITION);
        robot.wrist.setPosition(WRIST_DRIVING_POSITION);
        robot.bucket.setPosition(BUCKET_PICKUP_POSITION);

        robot.linearSlide.setTargetPosition(1000);
        robot.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.linearSlide.setPower(LINEAR_SLIDE_POWER);

        sleep(10000);
    }
}
