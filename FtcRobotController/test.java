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

/*
        robot.setPowers(0.5,0.5,0.5,0.5);
        sleep(1000);
        robot.setPowers(0, 0, 0, 0);
*/

        //testing area
        robot.linearSlide.setTargetPosition(2000);
        //robot.setPowers(0.45, 0.45, 0.45, 0.45);
        //sleep(1000);
        robot.linearSlide.setTargetPosition(1186);//1350 is on the bar//     1183 is cliped to bar    1210 is release
        sleep(500);
        robot.linearSlide.setTargetPosition(1210);
        robot.claw.setPosition(ServoMotorPosConstants.CLAW_OPEN_POSITION);
        //robot.setPowers(-0.4, -0.4, -0.4, -0.4);
        sleep(1000);

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

        //dont we not want this since wont it break the motor
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

