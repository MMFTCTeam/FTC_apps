package com.qualcomm.ftcrobotcontroller.opmodes;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by sam on 29-Dec-15.
 * This Program follows a white line
 */
public class FollowTheLine extends Programbot {
    boolean currDir = true;
    private enum runState {
        RUN_STATE_FIND_LINE,
        RUN_STATE_LINE_FOUND,
        RUN_STATE_DEAD,
    }
    runState r = runState.RUN_STATE_FIND_LINE;
    @Override
    public void init() {
        super.init();
    }

    @Override
    public void init_loop() {
        super.init_loop();
    }

    @Override
    public void loop() {
        Line.enableLed(true);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        switch (r) {
            case RUN_STATE_FIND_LINE:
                BL.setPower(1);
                BR.setPower(1);
                FL.setPower(1);
                FR.setPower(1);
                if (Light.blue() > 4) {
                    ResetEncoders();
                    r = runState.RUN_STATE_LINE_FOUND;
                }
                break;
            case RUN_STATE_LINE_FOUND:
            if (Line.blue() >= 4.5 && !currDir) {
                BL.setPower(-0.2);
                FL.setPower(-0.2);
                BR.setPower(0.2);
                FR.setPower(0.2);
                System.out.println("Left");
                BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                System.out.println("Reset BR");
                currDir = true;
            } else if (Line.blue() >= 2 && Line.blue() < 4.5) {
                if (!currDir) {
                    BR.setPower(-0.2);
                    FR.setPower(-0.2);
                    BL.setPower(0.2);
                    FL.setPower(0.2);
                    System.out.println("Right");
                }
                if (currDir) {
                    BL.setPower(-0.2);
                    FL.setPower(-0.2);
                    BR.setPower(0.2);
                    FR.setPower(0.2);
                    System.out.println("Left");
                    BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                    System.out.println("Reset BR");
                }
                if (BR.getCurrentPosition() <= -60) {
                    currDir = false;
                }
                if (BR.getCurrentPosition() > 60) {
                    currDir = true;
                }
            } else {
                BR.setPower(-0.2);
                FR.setPower(-0.2);
                BL.setPower(-0.2);
                FL.setPower(-0.2);
                System.out.println("No turn");
            }
                break;
        }
        System.out.println(Line.red());
        System.out.println("Encoder" + BR.getCurrentPosition());
        telemetry.addData("Color", "#" + Integer.toHexString(Line.argb()));
    }

    @Override
        public void start() {
        super.start();
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