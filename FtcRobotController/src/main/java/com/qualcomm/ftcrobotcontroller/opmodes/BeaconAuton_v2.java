package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by kevinobabb on 12/27/15.
 */
public class BeaconAuton_v2 extends OpMode {


    public boolean IfRed = false;
    public boolean IfBlue = false;
    public boolean LightFound = false;
    public boolean InPosition = false;



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

        FL.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.REVERSE);

        OtherMotor = hardwareMap.dcMotor.get("a1");

        RBump = hardwareMap.servo.get("s1");
        LBump = hardwareMap.servo.get("s2");
        OD = hardwareMap.opticalDistanceSensor.get("od");
        Light = hardwareMap.colorSensor.get("Color");

        LF = hardwareMap.colorSensor.get("Lf");

    }

    public void loop ()

    {

        telemetry.addData("Distance", OD.getLightDetectedRaw());

        telemetry.addData("RIntensity", LF.red());
        telemetry.addData("BIntensity", LF.blue());
        telemetry.addData("GIntensity", LF.green());
/*
        if (LightFound == false) {


            telemetry.addData("Red", IfRed);
            telemetry.addData("Blue", IfBlue);
            telemetry.addData("Distance", OD.getLightDetectedRaw());

            RBump.setPosition(0.1);
            LBump.setPosition(0.1);





            if (OD.getLightDetectedRaw() < 9) {

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

                        FR.setPower(0.3);
                        BR. setPower(0.3);

                        FL.setPower(-0.23);
                        BL.setPower(-0.23);

                    }

                    if(LF.blue() < 4.5 && LF.blue() > 2)
                    {

                        FR.setPower(0.3);
                        BR.setPower(0.3);

                        FL.setPower(-0.1);
                        BL.setPower(-0.1);

                    }

                    if(LF.blue() > 8)
                    {

                        FL.setPower(0.3);
                        BL. setPower(0.3);

                        FR.setPower(-0.23);
                        BR.setPower(-0.23);

                    }

                    if(LF.blue() > 5.5 && LF.blue() < 8)
                    {

                        FL.setPower(0.3);
                        BL.setPower(0.3);

                        FR.setPower(-0.1);
                        BR.setPower(-0.1);

                    }

                }

            }

            else
            {

                FR.setPower(0.0);
                FL.setPower(0.0);
                BR.setPower(0.0);
                BL.setPower(0.0);

                InPosition = true;

            }




            if (InPosition == true) {

                if ((Light.red() > 0) && (Light.blue() <= 0)) {
                    IfRed = true;
                    IfBlue = false;
                }

                if ((Light.blue() > 0) && (Light.red() <= 0)) {
                    IfBlue = true;
                    IfRed = false;
                }


                if ((IfRed == true) && (IfBlue == false)) {
                    RBump.setPosition(0.9);
                }

                if ((IfBlue == true) && (IfRed == false)) {
                    LBump.setPosition(0.9);
                }

                if ((IfRed == false) && (IfBlue == false)) {
                    RBump.setPosition(0.5);
                    LBump.setPosition(0.5);
                }


                LightFound = true;

            }

            else
            {



            }

       }


        else
        {



        }*/
    }

}