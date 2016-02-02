package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * <h1>
 * Created by Sam on 30-Dec-15. <br><br>
 * Updated on 01-Feb-16
 * TeleOpus Program V1.9.5</h1>
 * <h2>
 * Change log:
 * </h2>
 * <p>
 * V1.9.5 -- <br>
 * Added limits to Crane and extension
 * V1.9.4 -- <br>
 * Fixed bugs in arm and extension<br>
 * V1.9.3 -- <br>
 * Added Extension support
 * V1.9.1 -- <br>
 * Added Arm input <br>
 * V1.8.2 -- <br>
 * Modified scaleInput<br>
 * V1.8.1 -- <br>
 * Removed motors and sensors defined by programbot <br>
 * Migrated code to beta version of Fury Bot <br>
 * </p>
 * <h2>
 * Key Mapping:
 * </h2>
 * <h6>
 * Player 1:
 * </h6>
 * <p>
 * Analog Sticks: Move Robot <br>
 * Home button: Reverse Motors <br>
 * </p>
 * <h6>
 * Player 2:
 * </h6>
 * <p>
 * Right Stick forward/back: Raise/Lower arm
 * Left Stick forward/back: Extend/Retract arm
 * </p>
 */
public class TeleOpusLinear extends Fury_Bot {
    private boolean reversed = false;

    /**
     * <p>
     * Corresponds to OpMode's init(); <br>
     * Starting code of Program
     * </p>
     */
    @Override
    public void initializeRobot() {
        super.initializeRobot();
    }

    /**
     * Scales input from the joystick <br>
     * Note! Joystick forward returns negative values.
     *
     * @param value raw gamepad values
     * @return Scaled power values
     */
    public double scaleInput(double value) {
        double[] powerval = {0, 0, 0.25, 0.25, 0.5, 0.5, 0.5, 0.75, 0.75, 1.0, 1.0};
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

    /**
     * Robot Main Loop
     *
     * @throws InterruptedException
     */
    @Override
    public void runOpMode() throws InterruptedException {
        initializeRobot();
        waitForStart();
        waitOneFullHardwareCycle();
        while (opModeIsActive()) {
            BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            FL.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            FR.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            if (!reversed) {
                FL.setPower(scaleInput(gamepad1.left_stick_y));
                BL.setPower(scaleInput(gamepad1.left_stick_y));
                FR.setPower(scaleInput(gamepad1.right_stick_y));
                BR.setPower(scaleInput(gamepad1.right_stick_y));
            } else {
                FL.setPower(scaleInput(gamepad1.right_stick_y));
                BL.setPower(scaleInput(gamepad1.right_stick_y));
                FR.setPower(scaleInput(gamepad1.left_stick_y));
                BR.setPower(scaleInput(gamepad1.left_stick_y));
            }
            if (gamepad1.guide) {
                telemetry.addData("Guide pressed?", gamepad1.guide);
                while (gamepad1.guide) {
                    reversed = !reversed;
                    if (FL.getDirection() == DcMotor.Direction.FORWARD) {
                        FL.setDirection(DcMotor.Direction.REVERSE);
                    } else {
                        FL.setDirection(DcMotor.Direction.FORWARD);
                    }
                    if (BL.getDirection() == DcMotor.Direction.FORWARD) {
                        BL.setDirection(DcMotor.Direction.REVERSE);
                    } else {
                        BL.setDirection(DcMotor.Direction.FORWARD);
                    }
                    if (FR.getDirection() == DcMotor.Direction.FORWARD) {
                        FR.setDirection(DcMotor.Direction.REVERSE);
                    } else {
                        FR.setDirection(DcMotor.Direction.FORWARD);
                    }
                    if (BR.getDirection() == DcMotor.Direction.FORWARD) {
                        BR.setDirection(DcMotor.Direction.REVERSE);
                    } else {
                        BR.setDirection(DcMotor.Direction.FORWARD);
                    }
                }
            }
            if (Crane.getCurrentPosition() <= 400 && Crane.getCurrentPosition() >= 0) {
                Crane.setPower(-gamepad2.right_stick_y);
            }
            if (Ext.getCurrentPosition() >= -400 && Crane.getCurrentPosition() <= 0) {
                Ext.setPower(gamepad2.left_stick_y);
            }
            // telemetry.addData("Encoders", BL.getCurrentPosition() + "\n" + BR.getCurrentPosition());
            // telemetry.addData("Reversed?", reversed);
            waitForNextHardwareCycle();
        }
    }
}