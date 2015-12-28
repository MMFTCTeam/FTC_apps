package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by sam on 27-Dec-15.
 */
public class BasicAuto extends OpMode{
    public static final int LEFT_MOTORS_STOP = 0x01;
    public static final int RIGHT_MOTORS_STOP = 0x02;
    public static final int ALL_MOTORS_STOP = 0x03;
    public DcMotor FL;
    public DcMotor FR;
    public DcMotor OtherMotor;
    public DcMotor BL;
    public DcMotor BR;
    // public ServoController sr;
    public Servo Lbump;
    public Servo Rbump;
    public OpticalDistanceSensor OD;
    public ColorSensor Light;
    public double Lthrottle = gamepad1.left_stick_y;
    public double Rthrottle = gamepad1.right_stick_y;
    boolean enabled = false;

    @Override
    public void init() {
        BL = hardwareMap.dcMotor.get("Bl");
        FL = hardwareMap.dcMotor.get("Fl");
        BR = hardwareMap.dcMotor.get("Br");
        FR = hardwareMap.dcMotor.get("Fr");
        OtherMotor = hardwareMap.dcMotor.get("a1");
        Rbump = hardwareMap.servo.get("s1");
        Lbump = hardwareMap.servo.get("s2");
        OD = hardwareMap.opticalDistanceSensor.get("od");
        Light = hardwareMap.colorSensor.get("Color");
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        OtherMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        OtherMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        BL.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        telemetry.addData("Runtime", getRuntime());
        OtherMotor.setPower(1);
    }
}
