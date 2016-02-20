package com.qualcomm.ftcrobotcontroller.opmodes;


/**
 * Created by trevo on 02/19/2016.
 */
public class TeleopV2_0 extends Fury_Bot {

    public void initializeRobot() {
        super.initializeRobot();
    }

    public void runOpMode() throws InterruptedException {
        initializeRobot();
        waitForStart();
        waitOneFullHardwareCycle();
        while (opModeIsActive()) {


            waitForNextHardwareCycle();
        }
    }
}
