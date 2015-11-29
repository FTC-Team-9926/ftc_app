package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.util.Range;

/**
 * Created by Nicolas Bravo on 11/28/15
 * Uses two gamepads to control the robot
 * For use in the Driver Controlled section of the competition
 * Based on MoveTank.java
 */

public class TwoGamepads extends Telemetry9926{

    @Override
    public void init() {
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        float M1 = gamepad1.right_stick_y;
        float M2 = gamepad1.left_stick_y;
        float M3 = gamepad2.right_stick_y;
        float SM1 = gamepad2.left_stick_y;

        M1 = Range.clip(M1, -1, 1);
        M2 = Range.clip(M2, -1, 1);
        M3 = Range.clip(M3, -1, 1);
        SM1_Position = SM1;

        Motor1.setPower(M1);
        Motor2.setPower(M2);
        Motor3.setPower(M3);
        Set_Servo_position(SM1_Position);

        UpdateTelemetry();
        telemetry.addData("M1", "Right: " + M1);
        telemetry.addData("M2", "Left: " + M2);
        telemetry.addData("M3", "Arm: " + M3);
        telemetry.addData("SM1", "Hand: " + SM1);
        if (gamepad1 != null) {
            telemetry.addData("GP1", "Gamepad 1: connected");
        }
        else {
            telemetry.addData("GP1", "Gamepad 1: disconnected");
        }
        if (gamepad2 != null) {
            telemetry.addData("GP2", "Gamepad 2: connected");
        }
        else {
            telemetry.addData("GP2", "Gamepad 2: disconnected");
        }
    }

    @Override
    public void stop() {
    }
}
