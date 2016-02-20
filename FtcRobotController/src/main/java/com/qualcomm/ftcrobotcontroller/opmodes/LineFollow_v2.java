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
public class LineFollow_v2 extends OpMode{



    public DcMotor FL;
    public DcMotor FR;
    public DcMotor OtherMotor;
    public DcMotor BL;
    public DcMotor BR;

    public Servo LBump;
    public Servo RBump;

    public OpticalDistanceSensor OD;
    public ColorSensor Light;
    public ColorSensor LF;

    public void init()
    {

        BL = hardwareMap.dcMotor.get("Bl");
        FL = hardwareMap.dcMotor.get("Fl");
        BR = hardwareMap.dcMotor.get("Br");
        FR = hardwareMap.dcMotor.get("Fr");

        FR.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);

        OtherMotor = hardwareMap.dcMotor.get("a1");

        RBump = hardwareMap.servo.get("s1");
        LBump = hardwareMap.servo.get("s2");

        OD = hardwareMap.opticalDistanceSensor.get("od");
        Light = hardwareMap.colorSensor.get("Color");

        LF = hardwareMap.colorSensor.get("Lf");

    }


    public void loop ()
    {



        telemetry.addData("RIntensity", LF.red());
        telemetry.addData("BIntensity", LF.blue());
        telemetry.addData("GIntensity", LF.green());



        if(LF.blue() >= 4.5 && LF.blue() <= 5.5)
        {

            FR.setPower(0.15);
            FL.setPower(0.15);
            BR.setPower(0.15);
            BL.setPower(0.15);

        }

        else
        {

            if(LF.blue() < 2)
            {

                FL.setPower(0.3);
                BL. setPower(0.3);

                FR.setPower(-0.23);
                BR.setPower(-0.23);

            }

            if(LF.blue() < 4.5 && LF.blue() > 2)
            {

                FL.setPower(0.3);
                BL.setPower(0.3);

                FR.setPower(-0.1);
                BR.setPower(-0.1);

            }

            if(LF.blue() > 8)
            {

                FR.setPower(0.3);
                BR. setPower(0.3);

                FL.setPower(-0.23);
                BL.setPower(-0.23);

            }

            if(LF.blue() > 5.5 && LF.blue() < 8)
            {

                FR.setPower(0.3);
                BR.setPower(0.3);

                FL.setPower(-0.1);
                BL.setPower(-0.1);

            }

        }

    }
}
