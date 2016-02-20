package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ServiceConfigurationError;

/**
 * Created by kevinobabb on 12/26/15.
 */
public class BeaconAuton_v1 extends OpMode {

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
    }


    public void loop()
    {

        telemetry.addData("FR power",FR.getPower());

        telemetry.addData("Color",Light.argb());

    }



}
