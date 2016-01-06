package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by alejandro on 1/4/2016.
 */
public class TeleOp extends OpMode {

    public boolean armmove = false;
    public DcMotor arm;
    public double contractThrottle;

    public void init (){
        arm = hardwareMap.dcMotor.get("a1");
        contractThrottle = gamepad1.right_stick_y;
    }
    public void loop (){
        if(gamepad1.b){
            armmove = true;
            //while(armmove){
                arm.setPower(-1);
        }
        if (gamepad1.a){
            armmove=false;
            //while(contractThrottle<0){
                arm.setPower(1);
            //}
        }
    }
}
