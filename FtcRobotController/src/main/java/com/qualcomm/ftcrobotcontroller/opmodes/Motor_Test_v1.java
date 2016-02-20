package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by trevo+aleja on 02/19/2016.
 */
public class Motor_Test_v1 extends LinearOpMode{


    public DcMotor FL;
    public DcMotor FR;
    public DcMotor Ext;
    // public DcMotor Roller;
    public DcMotor BL;
    public DcMotor BR;
    public DcMotor Crane;
    //public ColorSensor Line;
    public DcMotor hook;


    public void initializeRobot() {

        BL = hardwareMap.dcMotor.get("Bl");
        FL = hardwareMap.dcMotor.get("Fl");
        BR = hardwareMap.dcMotor.get("Br");
        FR = hardwareMap.dcMotor.get("Fr");
        Crane = hardwareMap.dcMotor.get("arm");
        Ext = hardwareMap.dcMotor.get("ext");
        //Line = hardwareMap.colorSensor.get("Line");
        hook = hardwareMap.dcMotor.get("Hook");
        hook.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        hook.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

    }
    @Override
    public void runOpMode() throws InterruptedException{
        initializeRobot();
        waitForStart();
        waitOneFullHardwareCycle();
        while(opModeIsActive()) {
            //drive train
            FL.setPower(-gamepad1.left_stick_y);
            BL.setPower(-gamepad1.left_stick_y);
            FR.setPower(gamepad1.right_stick_y);
            BR.setPower(gamepad1.right_stick_y);
            //arm
            Ext.setPower(-gamepad2.left_stick_y);
            Crane.setPower(-gamepad2.right_stick_y);

            //hook right and left bumper
            if(gamepad1.right_bumper){
               // while(hook.getCurrentPosition()<500) {
                    hook.setPower(.2);
                //}

                //hook.setPower(0);
            }
            if(gamepad1.left_bumper){
                //while(hook.getCurrentPosition()>0) {
                    hook.setPower(-.2);
                //}

                //hook.setPower(0);
            }
            if(!(gamepad1.right_bumper) && ! (gamepad1.left_bumper)){
                hook.setPower(0);
            }
            telemetry.addData("Hook Encoder", hook.getCurrentPosition());
            waitForNextHardwareCycle();
        }
    }
}