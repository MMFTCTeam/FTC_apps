package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by kevinobabb on 12/29/15.
 */
public class LineFollow_v1 extends OpMode {



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
    {

        BL = hardwareMap.dcMotor.get("Bl");
        FL = hardwareMap.dcMotor.get("Fl");
        BR = hardwareMap.dcMotor.get("Br");
        FR = hardwareMap.dcMotor.get("Fr");

        FL.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.REVERSE);

        OtherMotor = hardwareMap.dcMotor.get("a1");

        RBump = hardwareMap.servo.get("s1");
        LBump = hardwareMap.servo.get("s2");
        OD = hardwareMap.opticalDistanceSensor.get("od");
        Light = hardwareMap.colorSensor.get("Color");

        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);

    }

    public void loop ()
    {


        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        if(BR.getCurrentPosition() < 500)
        {

            FR.setPower(0.25);
            FL.setPower(0.25);
            BR.setPower(0.25);
            BL.setPower(0.25);

        }

        else
        {

            FR.setPower(0);
            FL.setPower(0);
            BR.setPower(0);
            BL.setPower(0);

        }


    }


}
