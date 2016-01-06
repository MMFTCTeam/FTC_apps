package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by sam on 05-Jan-16.
 */
public class AutonomousClimbRed extends Programbot {
    @Override
    public void initializeRobot() throws InterruptedException {
        super.initializeRobot();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initializeRobot();
        waitForStart();
        while (opModeIsActive()) {
            while (BL.getCurrentPosition() < 1024) {
                BL.setPower(1);
                BR.setPower(1);
                FL.setPower(1);
                FR.setPower(1);
            }
            final int TURNTARGET = 2048;
            BL.setPower(0);
            BR.setPower(0);
            FL.setPower(0);
            FR.setPower(0);
            BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            waitOneFullHardwareCycle();
            BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            waitOneFullHardwareCycle();
            /**
             * Both encoders must have reached TURNTARGET
             */
            while (!(BR.getCurrentPosition() > TURNTARGET && BL.getCurrentPosition() < -TURNTARGET)) {
                if (BL.getCurrentPosition() <= -TURNTARGET) {
                    BL.setPower(0);
                    FL.setPower(0);
                } else {
                    BL.setPower(-1);
                    FL.setPower(-1);
                }
                if (BR.getCurrentPosition() >= TURNTARGET) {
                    BR.setPower(0);
                    FR.setPower(0);
                } else {
                    BR.setPower(1);
                    FR.setPower(1);
                }
            }
            BL.setPower(0);
            BR.setPower(0);
            FL.setPower(0);
            FR.setPower(0);
            haltMotors();
            waitOneFullHardwareCycle();
            telemetry.addData("Encoder State", BL.getCurrentPosition() + "\n" + BR.getCurrentPosition());
            break;
        }
    }
}