package org.firstinspires.ftc.teamcode.support;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

public class JoulesRobot extends PIDRobot
{
    public DcMotor linearSlide = null;
    public Servo wrist = null;
    public CRServo intake = null;
    public Servo right = null;
    public Servo left = null;
    public Servo claw = null;
    public Servo bucket = null;

    public JoulesRobot(LinearOpMode opMode) {super(opMode, false);}

    public void init() {
        // Define and Initialize Motors (note: need to use reference to actual OpMode).
        linearSlide = myOpMode.hardwareMap.get(DcMotor.class, "linearSlide");
        linearSlide.setDirection(DcMotor.Direction.REVERSE);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Define and Initialize Servos
        wrist = myOpMode.hardwareMap.get(Servo.class, "wrist");
        wrist.setDirection(Servo.Direction.FORWARD);

        intake = myOpMode.hardwareMap.get(CRServo.class, "intake");
        intake.setDirection(CRServo.Direction.FORWARD);

        right = myOpMode.hardwareMap.get(Servo.class, "right");
        right.setDirection(Servo.Direction.FORWARD);

        left = myOpMode.hardwareMap.get(Servo.class, "left");
        left.setDirection(Servo.Direction.FORWARD);

        claw = myOpMode.hardwareMap.get(Servo.class, "claw");
        claw.setDirection(Servo.Direction.FORWARD);

        bucket = myOpMode.hardwareMap.get(Servo.class, "bucket");
        bucket.setDirection(Servo.Direction.FORWARD);

        myOpMode.telemetry.addData(">", "Hardware Initialized");


        super.init(true);
    }
}