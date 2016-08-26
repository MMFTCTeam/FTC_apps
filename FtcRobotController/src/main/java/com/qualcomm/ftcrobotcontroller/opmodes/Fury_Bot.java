package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by sam on 07-Dec-15.
 * This has all methods defined by Fury Bot
 */
public abstract class Fury_Bot extends OpMode {
    public static final double PI = 3.1415926535897932384626433832795d;
    public static final int LEFT_MOTORS_STOP = 0x01;
    public static final int RIGHT_MOTORS_STOP = 0x02;
    public static final int ALL_MOTORS_STOP = 0x03;
    public DcMotor FL;
    public DcMotor FR;
    public DcMotor ExtensionMotor;
    public DcMotor Roller;
    public DcMotor BL;
    public DcMotor BR;
    // public ServoController sr;
    public Servo TiltBoxL;
    public Servo TiltBoxR;
    public OpticalDistanceSensor OD;
    public ColorSensor Light;
    public double Lthrottle = gamepad1.left_stick_y;
    public double Rthrottle = gamepad1.right_stick_y;
    boolean enabled = false;

    @Override
    public void init() {
        BL = hardwareMap.dcMotor.get("m1");
        FL = hardwareMap.dcMotor.get("m2");
        BR = hardwareMap.dcMotor.get("m3");
        FR = hardwareMap.dcMotor.get("m4");
        ExtensionMotor = hardwareMap.dcMotor.get("m5");
        Roller = hardwareMap.dcMotor.get("m6");
        TiltBoxR = hardwareMap.servo.get("s1");
        TiltBoxL = hardwareMap.servo.get("s2");
        OD = hardwareMap.opticalDistanceSensor.get("a0");
        Light = hardwareMap.colorSensor.get("Color");
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        ExtensionMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        ExtensionMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    void Move() {
        BL.setPower(1);
        FL.setPower(1);
        FR.setPower(1);
        BR.setPower(1);
    }

    void Move(double power) {
        BL.setPower(power);
        FL.setPower(power);
        FR.setPower(power);
        BR.setPower(power);
    }

    public void Move(double power, int distance) {
        while (BL.getCurrentPosition() < distance) {
            BL.setPower(power);
            FL.setPower(power);
            FR.setPower(power);
            BR.setPower(power);
        }
    }

    void turnLeft() {
        BL.setPower(-1);
        FL.setPower(-1);
        FR.setPower(1);
        BR.setPower(1);
    }

    void turnLeftRadians(float rad) {
        BL.setPower(-1);
        FL.setPower(-1);
        FR.setPower(1);
        BR.setPower(1);
    }

    void turnRightRadians(float rad) {
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        while (BL.getCurrentPosition() < rad) {
            BL.setPower(1);
            FL.setPower(1);
            FR.setPower(-1);
            BR.setPower(-1);
        }
        haltMotors();
    }
    void turnRightRadians(float rad, boolean reset) {
        if (reset) {
            BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        while (BL.getCurrentPosition() < rad) {
            BL.setPower(1);
            FL.setPower(1);
            FR.setPower(-1);
            BR.setPower(-1);
        }
        haltMotors();
    }

    void haltMotors() {
        BL.setPower(0);
        FL.setPower(0);
        BR.setPower(0);
        FR.setPower(0);
    }

    int getHaltMotorStatus() {
        boolean lstop = false;
        boolean rstop = false;
        if (BL.getPower() == 0 & FL.getPower() == 0) {
            lstop = true;
        }
        if (FR.getPower() == 0 & BR.getPower() == 0) {
            rstop = true;
        }
        if (lstop) {
            return LEFT_MOTORS_STOP;
        }
        if (rstop) {
            return RIGHT_MOTORS_STOP;
        }
        if (lstop & rstop) {
            return ALL_MOTORS_STOP;
        }
        return 0;
    }
    void ResetEncoders() {
        FL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }
}