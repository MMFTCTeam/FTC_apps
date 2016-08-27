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

    public boolean blue;
    public boolean green;
    public boolean red;
    public boolean lightEnabled;
    public boolean BottomLightEnabled;
    public boolean CurrentState;
    public boolean PrevState;

    public String colorname;

    public ColorSensor sensor;
    public ColorSensor sensor1;

    float hsvValues[] = {0F,0F,0F};
    final float values[] = hsvValues;
    /*
    float hsvValues1[] = {0F,0F,0F};
    final float values1[] = hsvValues1;
    */
    public void initializeRobot(){
        BL = hardwareMap.dcMotor.get("Bl");
        BR = hardwareMap.dcMotor.get("Br");
        FL = hardwareMap.dcMotor.get("Fl");
        FR = hardwareMap.dcMotor.get("Fr");

        sensor = hardwareMap.colorSensor.get("FSensor");
        sensor1 = hardwareMap.colorSensor.get("BSensor");

        blue = false;
        green = false;
        red = false;
        lightEnabled = false;
        BottomLightEnabled = false;
        CurrentState = false;
        PrevState = false;

        colorname = "null";

        FR.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.REVERSE);
    }

    public void runOpMode() throws InterruptedException {
        initializeRobot();
        waitForStart();
        waitOneFullHardwareCycle();
        sensor.enableLed(lightEnabled);
        sensor.enableLed(BottomLightEnabled);
        while(opModeIsActive()) {

            CurrentState = gamepad1.x;

            if(CurrentState == true && PrevState != CurrentState) {
                PrevState = CurrentState;
                lightEnabled = true;
                BottomLightEnabled = true;

                sensor.enableLed(lightEnabled);
                sensor1.enableLed(BottomLightEnabled);
                Thread.sleep(500);
            }
            else if(CurrentState == true && PrevState == CurrentState) {
                PrevState = false;
                lightEnabled = false;
                BottomLightEnabled = false;

                sensor.enableLed(lightEnabled);
                sensor1.enableLed(BottomLightEnabled);
                Thread.sleep(500);
            }
            BL.setPower(gamepad1.left_stick_y);
            FL.setPower(gamepad1.left_stick_y);
            BR.setPower(gamepad1.right_stick_y);
            FR.setPower(gamepad1.right_stick_y);

            if(sensor.blue()>sensor.green() && sensor.blue()>sensor.red()){
                blue = true;
            }
            else if(sensor.green()>sensor.blue() && sensor.green()>sensor.red()){
                green = true;
            }
            else if(sensor.red()>sensor.blue() && sensor.red()>sensor.green()){
                red = true;
            }

            if(blue){
                colorname = "blue";
            }
            if(green){
                colorname = "green";
            }
            if(red){
                colorname = "red";
            }
            //sensor1.enableLed(gamepad1.left_bumper);

            Color.RGBToHSV(sensor.red()*8, sensor.green()*8, sensor.blue()*8, hsvValues);

            telemetry.addData("Clear: ", sensor.alpha());
            telemetry.addData("Green: ", sensor.green());
            telemetry.addData("Blue: ", sensor.blue());
            telemetry.addData("Red: ", sensor.red());
            telemetry.addData("Hue: ", hsvValues[0]);
            /*
            Color.RGBToHSV(sensor1.red(), sensor1.green(), sensor1.blue(), hsvValues1);

            telemetry.addData("Clear1: ", sensor1.alpha());
            telemetry.addData("Green1: ", sensor1.green());
            telemetry.addData("Blue1: ", sensor1.blue());
            telemetry.addData("Red1: ", sensor1.red());
            telemetry.addData("Hue1: ", hsvValues1[0]);
            */
            telemetry.addData("Color: ", colorname);

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
