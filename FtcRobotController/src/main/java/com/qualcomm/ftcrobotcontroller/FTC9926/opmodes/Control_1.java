package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.util.Range;

/**
 * Created by ibravo on 11/26/15.
 * This program is to control the movements of the robot,
 * with the profile 1. It is based on MoveTank
 */
public class Control_1 extends Telemetry9926 {

    // position of the claw servo
    double ServoPosition;

    // amount to change the claw servo position by
    double ServoDelta = 0.01;


    @Override
    public void start() {
        //
        // Call the PushBotHardware (super/base class) start method.
        //
        super.start ();

    }

    @Override
    public void loop() {

		/*
		 * Gamepad 1
		 */

        /*
        ************************************
        *     Move Tank Robot
        ************************************
         */
        // tank drive
        // note that if y equal -1 then joystick is pushed all of the way forward.
        // clip the right/left values so that the values never exceed +/- 1
        float M1Power = Range.clip(-gamepad1.left_stick_y, -1, 1);
        float M2Power = Range.clip(gamepad1.right_stick_y, -1, 1);

        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
//        right = (float)scaleInput(right);
//        left =  (float)scaleInput(left);

        // write the values to the motors
        MoveRobot(M1Power, M2Power);
        /*
        ************************************
         */

        /*
        ************************************
        *     Move Arm Up and Down
        ************************************
         */
        // Use left trigger to speed Up
        // Use right trigger to speed down
//        float GoUp = Range.clip(-gamepad1.left_stick_y,-1,1);
//        float M2Power = Range.clip(gamepad1.right_stick_y,-1,1);

        double GoUp = (gamepad1.left_trigger + 1) / 2 - (gamepad1.right_trigger + 1) / 2;
        GoUp = Range.clip(GoUp, -1, 1);
        GoUp = (float)scaleInput(GoUp);


        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
//        right = (float)scaleInput(right);
//        left =  (float)scaleInput(left);

        // write the values to the motors
        MoveArm(GoUp);
        /*
        ************************************
         */

        /*
        **********************************
        *     Move Servo Up and Down
        **********************************
        */
        if (gamepad1.left_bumper) {
            ServoPosition += ServoDelta;
        }
        if (gamepad1.right_bumper) {
            ServoPosition -= ServoDelta;
        }
        ServoPosition = Range.clip(ServoPosition, 0, .9);

        // write position values to the wrist and claw servo
        Servo1.setPosition(ServoPosition);

        /*
        ************************************
         */

		/*
		 * Send telemetry data back to driver station. Note that if we are using
		 * a legacy NXT-compatible motor controller, then the getPower() method
		 * will return a null value. The legacy NXT-compatible motor controllers
		 * are currently write only.
		 */

        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Servo", "Servo:  " + String.format("%.2f", ServoPosition));
        telemetry.addData("arm", "arm:  " + String.format("%.2f", GoUp));
        telemetry.addData("left tgt pwr", "left  pwr: " + String.format("%.2f", M1Power));
        telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", M2Power));
    }
}
