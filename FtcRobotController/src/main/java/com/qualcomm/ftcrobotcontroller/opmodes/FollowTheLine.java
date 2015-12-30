package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by sam on 29-Dec-15.
 * This Program follows a white line
 */
public class FollowTheLine extends Programbot {
    boolean currDir = true;

    @Override
    public void initializeRobot() {
        super.initializeRobot();
    }
    public void stopRobot() {
        Lbump.setPosition(1);
        Rbump.setPosition(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initializeRobot();
        waitForStart();
        Line.enableLed(true);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        /*while(BR.getCurrentPosition() > 20)
        if (Line.green() > 3) {
            FR.setPower(.2);
            BR.setPower(.2);
            FL.setPower(-.2);
            BL.setPower(-.2);
        }
        if (Line.green() < 3) {
            FR.setPower(-.2);
            BR.setPower(-.2);
            FL.setPower(.2);
            BL.setPower(.2);
        }*/
        // DEBUG
        while(opModeIsActive()) {
            System.out.println(Line.red());
            System.out.println("Encoder" + BR.getCurrentPosition());
            telemetry.addData("Color", "#" + Integer.toHexString(Line.argb()));
            waitForNextHardwareCycle();
        }
    }

    @Override
    public void haltMotors() {
        super.haltMotors();
    }

    @Override
    public void ResetEncoders() {
        super.ResetEncoders();
    }

    @Override
    public void Move() {
        super.Move();
    }

    @Override
    public void Move(double power) {
        super.Move(power);
    }

    @Override
    public void Move(double power, int distance) {
        super.Move(power, distance);
    }

    @Override
    public void Move(double power, int distance, boolean halt) {
        super.Move(power, distance, halt);
    }
}