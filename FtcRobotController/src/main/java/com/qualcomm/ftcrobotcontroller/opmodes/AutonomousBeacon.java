package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by sam on 27-Dec-15.
 * @deprecated
 */
public class AutonomousBeacon extends Programbot {
    private boolean running = true;
    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loop() {
        //run only once
        telemetry.addData("Color Sensor blue", Light.blue());
        telemetry.addData("Running?", running);
        Light.enableLed(false);
        while(running) {
            do {
                Move(0.5);
            } while ((Light.blue() <= 0.5) || (Light.red() <= 0.5));
            haltMotors();
            if(getHaltMotorStatus() == ALL_MOTORS_STOP) {
                running = false;
            }
            haltMotors();
        }
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
