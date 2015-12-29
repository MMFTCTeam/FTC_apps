package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by sam on 14-Nov-15.
 */
public class TeleOpus extends OpMode {
    public DcMotor FL;
    public DcMotor FR;
    public DcMotor OtherMotor;
    public DcMotor BL;
    public DcMotor BR;
    // public ServoController sr;
    public Servo Lbump;
    public Servo Rbump;
    public OpticalDistanceSensor OD;
    public ColorSensor Light;
    private boolean reversed = false;

    @Override
    public void init() {
        BL = hardwareMap.dcMotor.get("Bl");
        FL = hardwareMap.dcMotor.get("Fl");
        BR = hardwareMap.dcMotor.get("Br");
        FR = hardwareMap.dcMotor.get("Fr");
        OtherMotor = hardwareMap.dcMotor.get("a1");
        Rbump = hardwareMap.servo.get("s1");
        Lbump = hardwareMap.servo.get("s2");
        OD = hardwareMap.opticalDistanceSensor.get("od");
        Light = hardwareMap.colorSensor.get("Color");
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        OtherMotor.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        OtherMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.REVERSE);
        gamepad1.setJoystickDeadzone(0.01f);
        Rbump.setPosition(1);
        Lbump.setPosition(0);
    }

    @Override
    public void loop() {
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        OtherMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        double Lthrottle = gamepad1.left_stick_y;
        double Rthrottle = gamepad1.right_stick_y;
        /* FL.setPower(Lthrottle);
        BL.setPower(Lthrottle);
        FR.setPower(Rthrottle);
        BR.setPower(Rthrottle); */
        if (!reversed) {
            FL.setPower(gamepad1.left_stick_y);
            BL.setPower(gamepad1.left_stick_y);
            FR.setPower(gamepad1.right_stick_y);
            BR.setPower(gamepad1.right_stick_y);
        }
        else {
            FL.setPower(gamepad1.right_stick_y);
            BL.setPower(gamepad1.right_stick_y);
            FR.setPower(gamepad1.left_stick_y);
            BR.setPower(gamepad1.left_stick_y);
        }
        if (gamepad1.back) {
            OD.enableLed(true);
        }
        if (gamepad1.dpad_left) {
            OD.enableLed(false);
        }
        if (gamepad1.a) {
            OtherMotor.setPower(0);
        }
        if (gamepad1.dpad_up) {
            OtherMotor.setPower(1);
        }
        if (gamepad1.dpad_down) {
            OtherMotor.setPower(-1);
        }
        if (gamepad1.left_bumper) {
            Rbump.setPosition(0);
            Lbump.setPosition(1);
        }
        if (gamepad1.right_bumper) {
            Lbump.setPosition(0);
            Rbump.setPosition(1);
        }
        if (gamepad1.y) {
            Light.enableLed(true);
        }
        if (gamepad1.x) {
            Light.enableLed(false);
        }
        if (gamepad1.left_stick_button) {
            BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            // BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            // BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
        if (gamepad1.guide) {
            do {
                if (!reversed) {
                    reversed = true;
                } else {
                    reversed = false;
                }
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
            } while(gamepad1.guide);
        }
        telemetry.addData("Color Sensor Red", Light.red());
        telemetry.addData("Color Sensor Green", Light.green());
        telemetry.addData("Color Sensor Blue", Light.blue());
        telemetry.addData("Power", OtherMotor.getPower());
        telemetry.addData("Distance", OD.getLightDetected());
        telemetry.addData("Distance Raw", OD.getLightDetectedRaw());
        telemetry.addData("Encoders", BL.getCurrentPosition() + "\n" + BR.getCurrentPosition());
        telemetry.addData("Reversed?", reversed);
    }
}
