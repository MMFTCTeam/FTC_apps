package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by kevinobabb on 12/5/15.
 */
public class MakeItRun extends OpMode {

    DcMotor FL;
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;
    double LThrottle;
    double RThrottle;

    public void init() {

        FL = hardwareMap.dcMotor.get("m2");
        FR = hardwareMap.dcMotor.get("m4");
        BL = hardwareMap.dcMotor.get("m1");
        BR = hardwareMap.dcMotor.get("m3");
        FR.setDirection(DcMotor.Direction.REVERSE);
        BL.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop () {

        LThrottle = gamepad1.left_stick_y;
        RThrottle = gamepad1.right_stick_y;
        FL.setPower(LThrottle);
        BL.setPower(LThrottle);
        FR.setPower(RThrottle);
        BR.setPower(RThrottle);

    }

}
