package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by sam on 05-Jan-16.
 */
public class ResetEncoderDebug extends LinearOpMode {
    public DcMotor FL;
    public DcMotor FR;
    public DcMotor OtherMotor;
    public DcMotor BL;
    public DcMotor BR;

    public void initializeRobot() {
        BL = hardwareMap.dcMotor.get("Bl");
        FL = hardwareMap.dcMotor.get("Fl");
        BR = hardwareMap.dcMotor.get("Br");
        FR = hardwareMap.dcMotor.get("Fr");
        OtherMotor = hardwareMap.dcMotor.get("a1");
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initializeRobot();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("BL Reset", "Press A to reset");
            telemetry.addData("BR Reset", "Press B to reset");
            if (gamepad1.a) {
                BL.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                telemetry.addData("BL Reset", "Resetting");
                waitOneFullHardwareCycle();
                if (BL.getCurrentPosition() == 0) {
                    telemetry.addData("BL Reset", "Reset Successful");
                    sleep(2000);
                } else {
                    telemetry.addData("BL Reset", "FAIL!! Either you moved the motor or it didn't reset correctly");
                    sleep(2000);
                }
            }
            if (gamepad1.b) {
                telemetry.addData("BR Reset", "Resetting");
                BR.setMode(DcMotorController.RunMode.RESET_ENCODERS);
                waitOneFullHardwareCycle();
                if (BR.getCurrentPosition() == 0) {
                    telemetry.addData("BR Reset", "Reset Successful");
                    sleep(2000);
                } else {
                    telemetry.addData("BR Reset", "FAIL!! Either you moved the motor or it didn't reset correctly");
                    sleep(2000);
                }
            }
            waitOneFullHardwareCycle();
        }
    }
}
