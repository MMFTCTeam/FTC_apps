package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ServiceConfigurationError;

/**
 * Created by kevinobabb on 12/26/15.
 */
public class BeaconAuton_v1 extends OpMode {

    public boolean IfRed = false;
    public boolean IfBlue = false;

    public DcMotor FL;
    public DcMotor FR;
    public DcMotor OtherMotor;
    public DcMotor BL;
    public DcMotor BR;

    public Servo LBump;
    public Servo RBump;

    public OpticalDistanceSensor OD;
    public ColorSensor Light;

    public void init()
    {BL = hardwareMap.dcMotor.get("Bl");
    FL = hardwareMap.dcMotor.get("Fl");
    BR = hardwareMap.dcMotor.get("Br");
    FR = hardwareMap.dcMotor.get("Fr");

    OtherMotor = hardwareMap.dcMotor.get("a1");

    RBump = hardwareMap.servo.get("s1");
    LBump = hardwareMap.servo.get("s2");
    OD = hardwareMap.opticalDistanceSensor.get("od");
    Light = hardwareMap.colorSensor.get("Color");

        FR.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);
    }


    public void loop()
    {

        RBump.setPosition(0.1);
        LBump.setPosition(0.1);

        telemetry.addData("Color", Light.argb());
        telemetry.addData("Red", IfRed);
        telemetry.addData("Blue", IfBlue);

        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);


        BR.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        BR.setTargetPosition(1000);
        FR.setPower(0.5);
        FL.setPower(0.5);
        BR.setPower(0.5);
        BL.setPower(0.5);


        while(BR.getPower() != 0)
        {}

        FR.setPower(0);
        FL.setPower(0);
        BR.setPower(0);
        BL.setPower(0);

            if ((Light.red() > 0) && (Light.blue() <= 0)) {
                IfRed = true;
                IfBlue = false;
            }

            if ((Light.blue() > 0) && (Light.red() <= 0)) {
                IfBlue = true;
                IfRed = false;
            }



        if((IfRed == true) && (IfBlue == false))
        {RBump.setPosition(0.9);}

        if((IfBlue == true) && (IfRed == false))
        {LBump.setPosition(0.9);}

        if((IfRed == false) && (IfBlue == false))
        {RBump.setPosition(0.5);
        LBump.setPosition(0.5);}


    }



}
