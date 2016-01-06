package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by sam on 05-Jan-16.
 */
public class AutonomousClimbRed extends Programbot {
    @Override
    public void initializeRobot() {
        super.initializeRobot();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initializeRobot();
        waitForStart();
        while (BL.getCurrentPosition() < 1024) {
            BL.setPower(1);
            BR.setPower(1);
            FL.setPower(1);
            FR.setPower(1);
        }
        BL.setPower(0);
        BR.setPower(0);
        FL.setPower(0);
        FR.setPower(0);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        /*BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);*/
        while (BR.getCurrentPosition() < 2048) {
            BL.setPower(-1);
            FL.setPower(-1);
            BR.setPower(1);
            FR.setPower(1);
        }
        haltMotors();
        if(opModeIsActive()) {
            telemetry.addData("Encoders", BL.getCurrentPosition() + "\n" + BR.getCurrentPosition());
        }else {
            haltMotors();
        }
        waitOneFullHardwareCycle();
    }
}
