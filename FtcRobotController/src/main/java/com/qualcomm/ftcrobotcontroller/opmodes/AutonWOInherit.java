package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by sam on 27-Dec-15.
 */
public class AutonWOInherit extends OpMode {
    public static final int LEFT_MOTORS_STOP = 0x01;
    public static final int RIGHT_MOTORS_STOP = 0x02;
    public static final int ALL_MOTORS_STOP = 0x03;
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
    public double Lthrottle = gamepad1.left_stick_y;
    public double Rthrottle = gamepad1.right_stick_y;
    boolean enabled = false;
    private RunState runState = RunState.RUN_STATE_IDLE;

    enum RunState {
        RUN_STATE_RUNNING,
        RUN_STATE_DEAD,
        RUN_STATE_IDLE
    }

    @Override
    /**
     *
     */
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
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        FR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        OtherMotor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        OtherMotor.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        BL.setDirection(DcMotor.Direction.REVERSE);
        FR.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop() {
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        switch (runState) {
            case RUN_STATE_IDLE:
                ResetEncoders();
                while (!gamepad1.a) {

                }
                runState = RunState.RUN_STATE_RUNNING;
                break;
            case RUN_STATE_RUNNING:
                while (BR.getCurrentPosition() < 1125) {
                    BR.setPower(1);
                    FR.setPower(1);
                    BL.setPower(1);
                    FL.setPower(1);
                    if (Light.red() > 0.5 || Light.blue() > 0.5) {
                        runState = RunState.RUN_STATE_DEAD;
                        break;
                    }
                }
                if (Light.red() > 0.5 || Light.blue() > 0.5) {
                    runState = RunState.RUN_STATE_DEAD;
                }

                runState = RunState.RUN_STATE_IDLE;
                break;
            case RUN_STATE_DEAD:
                // do nothing
                for(;;) {
                    if (getRuntime() >= 30) {
                        break;
                    }
                }
                System.exit(0);
        }
        telemetry.addData("Blue", Light.blue());
        telemetry.addData("Red", Light.red());
        telemetry.addData("ALL COLOR INFO #AARRGGBB", "#" + Integer.toHexString(Light.argb()));
        telemetry.addData("Run state", runState.toString());
    }
    public void Move() {
        BL.setPower(1);
        FL.setPower(1);
        FR.setPower(1);
        BR.setPower(1);
    }

    public void Move(double power) {
        BL.setPower(power);
        FL.setPower(power);
        FR.setPower(power);
        BR.setPower(power);
    }

    public void Move(double power, int distance) {
        ResetEncoders();
        while (BL.getCurrentPosition() < distance) {
            BL.setPower(power);
            FL.setPower(power);
            FR.setPower(power);
            BR.setPower(power);
        }
        haltMotors();
    }

    public void Move(double power, int distance, boolean halt) {
        while (BL.getCurrentPosition() < distance) {
            BL.setPower(power);
            FL.setPower(power);
            FR.setPower(power);
            BR.setPower(power);
        }
        if (halt) {
            haltMotors();
        }
    }

    public void turnLeft() {
        BL.setPower(-1);
        FL.setPower(-1);
        FR.setPower(1);
        BR.setPower(1);
    }

    public void turnLeftRadians(float rad) {
        BL.setPower(-1);
        FL.setPower(-1);
        FR.setPower(1);
        BR.setPower(1);
    }

    public void turnRightRadians(float rad) {
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        while (BL.getCurrentPosition() < rad) {
            BL.setPower(1);
            FL.setPower(1);
            FR.setPower(-1);
            BR.setPower(-1);
        }
        haltMotors();
    }

    public void turnRightRadians(float rad, boolean reset) {
        if (reset) {
            BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
        BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        while (BL.getCurrentPosition() < rad) {
            BL.setPower(1);
            FL.setPower(1);
            FR.setPower(-1);
            BR.setPower(-1);
        }
        haltMotors();
    }

    public void haltMotors() {
        BL.setPower(0);
        FL.setPower(0);
        BR.setPower(0);
        FR.setPower(0);
    }

    public int getHaltMotorStatus() {
        boolean lstop = false;
        boolean rstop = false;
        if (BL.getPower() == 0 && FL.getPower() == 0) {
            lstop = true;
        }
        if (FR.getPower() == 0 && BR.getPower() == 0) {
            rstop = true;
        }
        if (lstop) {
            return LEFT_MOTORS_STOP;
        }
        if (rstop) {
            return RIGHT_MOTORS_STOP;
        }
        if (lstop & rstop) {
            return ALL_MOTORS_STOP;
        }
        return 0;
    }

    public void ResetEncoders() {
        // FL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        // FR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        // FL.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        // BL.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        // BR.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        // FR.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    }
}
