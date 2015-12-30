package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by sam on 07-Dec-15.
 * Sample Autonomous Programme
 */
public class AutoTest extends Programbot {
    enum Runstate {
        FindBlocks,
        ClimbBar,
        FindLight,
        Pushbutton
    }
    boolean stuck = false;
    Runstate d;
    @Override
    public void init() {
        super.init();
        d = Runstate.FindBlocks;
    }

    @Override
    public void loop() {
        while (d == Runstate.FindBlocks) {
            OD.enableLed(true);
            Move(1, 200);
            haltMotors();
            telemetry.addData("OD", OD.getLightDetected());
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(OD.getLightDetected() < 26) {
                d = Runstate.FindLight;
            }
        }
        telemetry.addData("End of program","AYY LMAO!");
    }
    @Override
    public void haltMotors() {
        super.haltMotors();
    }

    @Override
    public void turnLeft() {
        super.turnLeft();
    }

    @Override
    public void turnRightRadians(float rad) {
        super.turnRightRadians(rad);
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
    public void turnLeftRadians(float rad) {
        super.turnLeftRadians(rad);
    }

    @Override
    public int getHaltMotorStatus() {
        return super.getHaltMotorStatus();
    }

    @Override
    public void ResetEncoders() { super.ResetEncoders(); }
}