package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by sam on 27-Dec-15.
 */
public class AutonomousBeacon extends Programbot {
    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        //run only once
        Light.enableLed(false);
        do {
            Move(1);
        } while (Light.blue() > 0.5);
        return;
    }

    @Override
    public void Move(double power, int distance) {
        super.Move(power, distance);
    }
    @Override
    public int getHaltMotorStatus() {
        return super.getHaltMotorStatus();
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
    public void haltMotors() {
        super.haltMotors();
    }
}
