package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by alejandro on 1/4/2016.
 */
public class TeleOp extends OpMode {

    public DcMotor arm;
    double contractThrottle;

    public void init (){
        arm = hardwareMap.dcMotor.get("a1");
        contractThrottle = gamepad1.right_stick_y;
        arm.setDirection(DcMotor.Direction.REVERSE);
        gamepad1.setJoystickDeadzone(0.02f);
    }
    public void loop (){
        arm.setPower(gamepad1.right_stick_y);
    }
}
