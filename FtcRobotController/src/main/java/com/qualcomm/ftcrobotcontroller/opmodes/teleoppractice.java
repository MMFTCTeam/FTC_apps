package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Vector;

/**
 * Created by aleja on 7/10/2016.
 */
public class teleoppractice extends LinearOpMode{
    public DcMotor BL;
    public DcMotor BR;
    public DcMotor FR;
    public DcMotor FL;

    public ColorSensor sensor;
    public ColorSensor sensor1;

    float hsvValues[] = {0F,0F,0F};
    final float values[] = hsvValues;

    float hsvValues1[] = {0F,0F,0F};
    final float values1[] = hsvValues1;

    public void initializeRobot(){
        BL = hardwareMap.dcMotor.get("Bl");
        BR = hardwareMap.dcMotor.get("Br");
        FL = hardwareMap.dcMotor.get("Fl");
        FR = hardwareMap.dcMotor.get("Fr");

        sensor = hardwareMap.colorSensor.get("BColor");
        sensor1 = hardwareMap.colorSensor.get("FColor");

        FR.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.REVERSE);
    }

    public void runOpMode() throws InterruptedException {
        initializeRobot();
        waitForStart();
        waitOneFullHardwareCycle();
        while(opModeIsActive()) {
            BL.setPower(gamepad1.left_stick_y);
            FL.setPower(gamepad1.left_stick_y);
            BR.setPower(gamepad1.right_stick_y);
            FR.setPower(gamepad1.right_stick_y);



        }

        /*Color.RGBToHSV(sensor.red(), sensor.green(), sensor.blue(), hsvValues);
        telemetry.addData("Clear: ", sensor.alpha());
        telemetry.addData("Green: ", sensor.green());
        telemetry.addData("Blue: ", sensor.blue());
        telemetry.addData("Red: ", sensor.red());
        telemetry.addData("Hue: ", hsvValues[0]);
        Color.RGBToHSV(sensor1.red(), sensor1.green(), sensor1.blue(), hsvValues1);
        telemetry.addData("Clear: ", sensor1.alpha());
        telemetry.addData("Green: ", sensor1.green());
        telemetry.addData("Blue: ", sensor1.blue());
        telemetry.addData("Red: ", sensor1.red());
        telemetry.addData("Hue: ", hsvValues1[0]);*/

        waitForNextHardwareCycle();
    }
}