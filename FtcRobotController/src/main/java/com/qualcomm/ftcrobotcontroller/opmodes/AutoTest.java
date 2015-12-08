package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by sam on 07-Dec-15.
 */
public class AutoTest extends Fury_Bot {
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
    }

    @Override
    public void loop() {
        while (d == Runstate.FindBlocks) {
            getRuntime();
        }
    }

    @Override
    void haltMotors() {
        super.haltMotors();
    }

    @Override
    void turnLeft() {
        super.turnLeft();
    }

    @Override
    void turnRightRadians(float rad) {
        super.turnRightRadians(rad);
    }

    @Override
    void Move() {
        super.Move();
    }

    @Override
    void Move(double power) {
        super.Move(power);
    }

    @Override
    void turnLeftRadians(float rad) {
        super.turnLeftRadians(rad);
    }

    @Override
    int getHaltMotorStatus() {
        return super.getHaltMotorStatus();
    }
}