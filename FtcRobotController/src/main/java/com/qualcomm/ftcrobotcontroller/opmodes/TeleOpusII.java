package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Team 8521 on 02/19/2016.
 * TeleOpus V2.0.0 <br>
 *     <p>
 * + Added Hook Support for latching on to the churro<br>
 * *Migrated due to update in FTC SDK
 * -removed advanced features in drivetrain and buildings<br>
 *     </p>
 */
public class TeleOpusII extends LinearOpMode {


    public DcMotor FL;
    public DcMotor FR;
    public DcMotor Ext;
    // public DcMotor Roller;
    public DcMotor BL;
    public DcMotor BR;
    public DcMotor Crane;
    //public ColorSensor Line;
    public DcMotor hook;
    double cThrottle;
    double eThrottle;


    public void initializeRobot() {
        BL = hardwareMap.dcMotor.get("Bl");
        FL = hardwareMap.dcMotor.get("Fl");
        BR = hardwareMap.dcMotor.get("Br");
        FR = hardwareMap.dcMotor.get("Fr");
        Crane = hardwareMap.dcMotor.get("arm");
        Ext = hardwareMap.dcMotor.get("ext");
        // Line = hardwareMap.colorSensor.get("Line");
        hook = hardwareMap.dcMotor.get("Hook");
        Ext.setDirection(DcMotor.Direction.FORWARD);
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

            if (!gamepad2.guide) {
                cThrottle = -gamepad2.right_stick_y;
                if (Crane.getCurrentPosition() < 0) {
                    Crane.setPower(cThrottle > 0 ? cThrottle : 0);
                } else if (Crane.getCurrentPosition() > 21000) {
                    Crane.setPower(cThrottle < 0 ? cThrottle : 0);
                } else {
                    Crane.setPower(cThrottle);
                }
                // end Crane Limits
                // begin Extension limits
                eThrottle = -gamepad2.left_stick_y;
                if (Ext.getCurrentPosition() > 0) {
                    Ext.setPower(eThrottle > 0 ? eThrottle : 0);
                } else if (Ext.getCurrentPosition() < -5000) {
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
            if (gamepad2.right_bumper && gamepad2.guide) {
                Crane.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                Crane.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                if (Crane.getCurrentPosition() == 0) {
                    telemetry.addData("Crane Status", "Successful");
                } else {
                    telemetry.addData("Crane Status", "Failure");
                }
            }
            if (gamepad2.left_bumper && gamepad2.guide) {
                Ext.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                Ext.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
                if (Ext.getCurrentPosition() == 0) {
                    telemetry.addData("Ext Status", "Successful");
                } else {
                    telemetry.addData("Ext Status", "Failure");
                }
            }
            //hook right and left bumper
            if (gamepad1.right_trigger > 0) {
                hook.setPower(gamepad1.right_trigger);
            } else if (gamepad1.left_trigger > 0) {
                hook.setPower(-gamepad1.left_trigger);
            } else {
                hook.setPower(0);
            }
            /* if(gamepad1.right_bumper){
                if(hook.getCurrentPosition()<130) {
                    hook.setPower(.2);
                }
            }
            if(gamepad1.left_bumper){
                if(hook.getCurrentPosition()>0) {
                    hook.setPower(-.2);
                }
            }
            if(!(gamepad1.right_bumper) && ! (gamepad1.left_bumper)){
                hook.setPower(0);
            }*/
            telemetry.addData("Crane", Crane.getCurrentPosition());
            telemetry.addData("Ext", Ext.getCurrentPosition());
            waitForNextHardwareCycle();
        }
    }
}
