package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by sam on 07-Dec-15.
 * This has all methods defined by Fury Bot (The competition Robot)
 */
public abstract class Fury_Bot extends LinearOpMode {
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
    public ColorSensor BeaconFinder;
    public ColorSensor LeftFloor;
    // public ServoController sr;
    public Servo TiltBoxL;
    public Servo TiltBoxR;
    public OpticalDistanceSensor OD;
    public ColorSensor RightFloor;
    public double Lthrottle = gamepad1.left_stick_y;
    public double Rthrottle = gamepad1.right_stick_y;
    boolean enabled = false;

    public void initializeRobot() {
        BL = hardwareMap.dcMotor.get("Bl");
        FL = hardwareMap.dcMotor.get("Fl");
        BR = hardwareMap.dcMotor.get("Br");
        FR = hardwareMap.dcMotor.get("Fr");
        ExtensionMotor = hardwareMap.dcMotor.get("a1");
        Roller = hardwareMap.dcMotor.get("R1");
        TiltBoxR = hardwareMap.servo.get("s1");
        TiltBoxL = hardwareMap.servo.get("s2");
        OD = hardwareMap.opticalDistanceSensor.get("a0");
        BeaconFinder = hardwareMap.colorSensor.get("Beacon");
        LeftFloor = hardwareMap.colorSensor.get("Left");
        RightFloor = hardwareMap.colorSensor.get("Right");
        FL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        ExtensionMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        ExtensionMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        Roller.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        Roller.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
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
        if (BL.getPower() == 0 && FL.getPower() == 0) {
            lstop = true;
        }
        if (FR.getPower() == 0 && BR.getPower() == 0) {
            rstop = true;
        }
        if (lstop) {
            return LEFT_MOTORS_STOP;
        }
        if (rstop) {
            return RIGHT_MOTORS_STOP;
        }
        if (lstop && rstop) {
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

    public void setServoPos(Servo ser, double pos) {
        if (pos <= 1 && pos >= -1)
            ser.setPosition(pos);
    }
}
