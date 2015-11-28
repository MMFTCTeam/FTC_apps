package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by sam on 14-Nov-15.
 */
public class TeleOpus extends OpMode {
    public DcMotor FL;
    public DcMotor FR;
    public DcMotor OtherMotor;
    public DcMotor BL;
    public DcMotor BR;
    // public ServoController sr;
    public Servo Lbump;
    public Servo Rbump;
    public OpticalDistanceSensor OD;


    @Override
    public void init() {
        BL = hardwareMap.dcMotor.get("m1");
        FL = hardwareMap.dcMotor.get("m2");
        BR = hardwareMap.dcMotor.get("m3");
        FR = hardwareMap.dcMotor.get("m4");
        OtherMotor = hardwareMap.dcMotor.get("m5");
        Rbump = hardwareMap.servo.get("s1");
        Lbump = hardwareMap.servo.get("s2");
        OD = hardwareMap.opticalDistanceSensor.get("a0");
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        OtherMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        OtherMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.REVERSE);
        gamepad1.setJoystickDeadzone(0.02f);
        Rbump.setPosition(1);
        Lbump.setPosition(0);
    }

    @Override
    public void loop() {
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        OtherMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        double Lthrottle = gamepad1.left_stick_y;
        double Rthrottle = gamepad1.right_stick_y;
        FL.setPower(Lthrottle);
        BL.setPower(Lthrottle);
        FR.setPower(Rthrottle);
        BR.setPower(Rthrottle);
        if(gamepad1.back) {
            OD.enableLed(true);
        }
        else if (gamepad1.dpad_left) {
            OD.enableLed(false);
        }
        if (gamepad1.a) {
            OtherMotor.setPower(0);
        }
        if (gamepad1.dpad_up) {
            OtherMotor.setPower(1);
        }
        if (gamepad1.dpad_down) {
            OtherMotor.setPower(-1);
        }
        if (gamepad1.left_bumper) {
            Rbump.setPosition(0);
            Lbump.setPosition(1);
        }
        if (gamepad1.right_bumper) {
            Lbump.setPosition(0);
            Rbump.setPosition(1);
        }
        telemetry.addData("Power", OtherMotor.getPower());
        telemetry.addData("Distance", OD.getLightDetected());
        telemetry.addData("Distance Raw", OD.getLightDetectedRaw());
    }
}