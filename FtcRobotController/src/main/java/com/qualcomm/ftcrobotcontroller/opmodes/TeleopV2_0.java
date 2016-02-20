package com.qualcomm.ftcrobotcontroller.opmodes;


/**
 * Created by trevo+aleja on 02/19/2016.
 */
public class TeleopV2_0 extends Fury_BotV2 {
    @Override
    public void initializeRobot() {
        super.initializeRobot();
    }
    @Override
    public void runOpMode() throws InterruptedException{
        initializeRobot();
        waitForStart();
        waitOneFullHardwareCycle();
        while(opModeIsActive()) {
            //drive train
            FL.setPower(gamepad1.left_stick_y);
            BL.setPower(gamepad1.left_stick_y);
            FR.setPower(gamepad1.right_stick_y);
            BR.setPower(gamepad1.right_stick_y);
            //arm
            Ext.setPower(-gamepad2.left_stick_y);
            Crane.setPower(-gamepad2.right_stick_y);

            //hook right and left bumper
            if(gamepad1.right_bumper){
                if(hook.getCurrentPosition()<100) {
                    hook.setPower(1);
                }
            }
            if(gamepad1.left_bumper){
                if(hook.getCurrentPosition()>0) {
                    hook.setPower(-1);
                }
            }
            waitForNextHardwareCycle();
        }
    }
}