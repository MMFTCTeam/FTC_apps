package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by sam on 07-Dec-15.
 * This has all methods defined by Fury Bot (The competition Robot)
 *
 */
public abstract class Fury_Bot extends LinearOpMode {
    public DcMotor FL;
    public DcMotor FR;
    public DcMotor Ext;
    // public DcMotor Roller;
    public DcMotor BL;
    public DcMotor BR;
    public DcMotor Crane;
    public ColorSensor BeaconFinder;
    public ColorSensor LeftFloor;
    // public ServoController sr;
    // public Servo TiltBoxL;
    // public Servo TiltBoxR;
    // public OpticalDistanceSensor OD;
    // public ColorSensor RightFloor;
    public double Lthrottle = gamepad1.left_stick_y;
    public double Rthrottle = gamepad1.right_stick_y;
    boolean enabled = false;

    /**
     * init statement
     */
    public void initializeRobot() {
        BL = hardwareMap.dcMotor.get("Bl");
        FL = hardwareMap.dcMotor.get("Fl");
        BR = hardwareMap.dcMotor.get("Br");
        FR = hardwareMap.dcMotor.get("Fr");
        Crane = hardwareMap.dcMotor.get("arm");
        Ext = hardwareMap.dcMotor.get("ext");
        /*TiltBoxR = hardwareMap.servo.get("s1");
        TiltBoxL = hardwareMap.servo.get("s2");
        OD = hardwareMap.opticalDistanceSensor.get("a0");
        BeaconFinder = hardwareMap.colorSensor.get("Beacon");
        LeftFloor = hardwareMap.colorSensor.get("Left");
        RightFloor = hardwareMap.colorSensor.get("Right");
        Ext.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        Ext.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);*/
        FL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        FL.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);
    }

    public double scaleInput(double value) {
        double[] powerval = {0, 0.1, 0.2, 0.2, 0.5, 0.5, 0.5, 0.7, 0.7, 0.9, 1.0};
        double retVal;
        int index;
        if (value > 0) {
            index = (int) Math.abs(Range.clip(value * 10, 0, 10));
            retVal = powerval[index];
        } else {
            index = (int) Math.abs(Range.clip(value * -10, 0, 10));
            retVal = -powerval[index];
        }
        return retVal;
    }
    void ResetEncoders() {
        FL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }
}