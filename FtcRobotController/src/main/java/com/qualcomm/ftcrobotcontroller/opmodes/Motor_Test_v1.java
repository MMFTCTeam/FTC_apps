package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by trevo+aleja on 02/19/2016.
 */
public class Motor_Test_v1 extends LinearOpMode{

    double cThrottle;
    double eThrottle;

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
        Ext.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        Ext.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        Crane.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        Crane.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

    }
    public double scaleInput(double value) {
        double[] powerval = {0, 0.1, 0.2, 0.2, 0.5, 0.5, 0.5, 0.7, 0.7, 0.9, 1.0};
        double retVal;
        int index;
        if (value > 0) {
            index = (int) Math.abs(Range.clip(value * 10, 0, 10));
            retVal = powerval[index];
        } else {
            index = (int) Math.abs(Range.clip(value * -10, 0, 10));
            retVal = -powerval[index];
        }
        return retVal;
    }
    @Override
    public void runOpMode() throws InterruptedException{
        initializeRobot();
        waitForStart();
        waitOneFullHardwareCycle();
        while(opModeIsActive()) {
            //drive train
            FL.setPower(scaleInput(-gamepad1.left_stick_y));
            BL.setPower(scaleInput(-gamepad1.left_stick_y));
            FR.setPower(scaleInput(gamepad1.right_stick_y));
            BR.setPower(scaleInput(gamepad1.right_stick_y));
            //arm
            if (!gamepad2.guide) {
                cThrottle = -gamepad2.right_stick_y;
                if (Crane.getCurrentPosition() < 0) {
                    Crane.setPower(cThrottle > 0 ? cThrottle : 0);
                } else if (Crane.getCurrentPosition() > 24000) {
                    Crane.setPower(cThrottle < 0 ? cThrottle : 0);
                } else {
                    Crane.setPower(cThrottle);
                }
                // end Crane Limits
                // begin Extension limits
                eThrottle = -gamepad2.left_stick_y;
                if (Ext.getCurrentPosition() > 0) {
                    Ext.setPower(eThrottle > 0 ? eThrottle : 0);
                } else if (Ext.getCurrentPosition() < -14750) {
                    Ext.setPower(eThrottle < 0 ? eThrottle : 0);
                } else {
                    Ext.setPower(eThrottle);
                }
            }
            // Override limits
            else {
                Crane.setPower(-gamepad2.right_stick_y);
                Ext.setPower(-gamepad2.left_stick_y);
            }
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
            telemetry.addData("Extension Encoder", Ext.getCurrentPosition());
            telemetry.addData("Crane Encoder", Crane.getCurrentPosition());//-13852
            waitForNextHardwareCycle();
        }
    }
}