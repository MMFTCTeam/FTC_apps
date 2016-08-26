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
public class PracticeAutonv1 extends LinearOpMode{
    public DcMotor BL;
    public DcMotor BR;
    public DcMotor FR;
    public DcMotor FL;

    public ColorSensor BColor;
    public ColorSensor FColor;

    float hsvValues[] = {0F,0F,0F};
    final float values[] = hsvValues;

    float hsvValues1[] = {0F,0F,0F};
    final float values1[] = hsvValues1;

    public void initializeRobot(){
        BL = hardwareMap.dcMotor.get("Bl");
        BR = hardwareMap.dcMotor.get("Br");
        FL = hardwareMap.dcMotor.get("Fl");
        FR = hardwareMap.dcMotor.get("Fr");

        BColor = hardwareMap.colorSensor.get("BSensor");
        FColor = hardwareMap.colorSensor.get("FSensor");

        FR.setDirection(DcMotor.Direction.REVERSE);
        BR.setDirection(DcMotor.Direction.REVERSE);
    }

    public void runOpMode() throws InterruptedException {
        initializeRobot();
        waitForStart();
        waitOneFullHardwareCycle();
        while(opModeIsActive()) {

            telemetry.addData("White", BColor.alpha());

            while(gamepad1.x == false)
            {

                if(BColor.alpha() > 150)
                {

                    BL.setPower(50);
                    FL.setPower(50);

                    BR.setPower(0);
                    BL.setPower(0);

                }

                else if(BColor.alpha() <= 149 && BColor.alpha() >= 107)
                {

                    BL.setPower(50);
                    FL.setPower(50);

                    BR.setPower(50);
                    FR.setPower(50);

                }

                else if(BColor.alpha() < 106)
                {

                    BL.setPower(0);
                    FL.setPower(0);

                    BR.setPower(50);
                    FR.setPower(50);

                }

            }

            if(gamepad1.x == true)
            {

                //opModeIsActive() = false;

            }
        }

        waitForNextHardwareCycle();
    }
}