package com.qualcomm.ftcrobotcontroller.opmodes;


/**
 * Created by sam on 14-Dec-15.
 */
public class TeleOpusUniversal extends Programbot {
    double FThrottle;
    double BThrottle;
    double Offset;
    double LOffset;
    double ROffset;

    public void initializeRobot() {
        super.initializeRobot();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initializeRobot();
        waitForStart();
        while (opModeIsActive()) {
            FThrottle = gamepad1.right_trigger;
            BThrottle = -gamepad1.left_trigger;
            Offset = gamepad1.left_stick_x;

            if (BThrottle > 0.01 && (BThrottle + Offset <= 1 && BThrottle + Offset >= -1)) {
                FL.setPower(BThrottle - Offset);
                BL.setPower(BThrottle - Offset);
                FR.setPower(BThrottle + Offset);
                BR.setPower(BThrottle + Offset);
            }
            if (FThrottle > 0.01 && (FThrottle + Offset <= 1 && FThrottle + Offset >= -1)) {
                FL.setPower(FThrottle + Offset);
                BL.setPower(FThrottle + Offset);
                BR.setPower(FThrottle - Offset);
                FR.setPower(FThrottle - Offset);
            }
        }
    }
}
