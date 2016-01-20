package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by sam on 28-Dec-15.
 * Autonomous (II) ProgramBot Makes robot move at full power for 1 sec
 */
public class Autonomous2 extends Programbot {
    double d = getRuntime();

    @Override
    public void initializeRobot() {
        super.initializeRobot();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initializeRobot();
        waitForStart();
        while (opModeIsActive()) {
            BL.setPower(1);
            FL.setPower(1);
            BR.setPower(1);
            FR.setPower(1);
            Rbump.setPosition(1);
            Lbump.setPosition(1);
            if (getRuntime() >= 1) {
                BL.setPower(0);
                BR.setPower(0);
                FL.setPower(0);
                FR.setPower(0);
                return;
            }
            telemetry.addData("Encoder Click/sec", BR.getCurrentPosition() + "\r\n" + BL.getCurrentPosition());
            telemetry.addData("Detailed Color Hex #AARRGGBB", "#" + Integer.toHexString(Light.argb()));
            telemetry.addData("Light intensity", OD.getLightDetectedRaw());
        }
    }

    @Override
    public void ResetEncoders() {
        super.ResetEncoders();
    }
}
