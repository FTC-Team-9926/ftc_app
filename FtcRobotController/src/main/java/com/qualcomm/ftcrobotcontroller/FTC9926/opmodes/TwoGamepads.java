package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Nicolas Bravo on 11/29/15
 * Used to move the robot with two gamepads
 * For use in the driver controlled section of the competition
 * Based on Control_1.java
 */
public class TwoGamepads extends Telemetry9926 {

    // position of the claw servo
    double Servo2_position;
    float leftTrigger;
    float rightTrigger;
    int MyReverse;
    double Dpad;

    @Override
    public void start() {
        // Call the PushBotHardware (super/base class) start method.
        super.start ();
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            MyReverse = -1;
        }
        if (gamepad1.b) {
            MyReverse = 1;
        }
        if (gamepad1.dpad_down && Dpad > 0.1) {
            Dpad = Dpad - 0.1;
        }
        if (gamepad1.dpad_up && Dpad < 1) {
            Dpad = Dpad + 0.1;
        }
            // tank drive
            // note that if y equal -1 then joystick is pushed all of the way forward.
            // clip the right/left values so that the values never exceed +/- 1
            float M1Power = Range.clip(-gamepad1.left_stick_y * MyReverse * (float) Dpad, -1, 1);
            float M2Power = Range.clip(gamepad1.right_stick_y * MyReverse * (float) Dpad, -1, 1);

            // scale the joystick value to make it easier to control
            // the robot more precisely at slower speeds.
//          right = (float)scaleInput(right);
//          left =  (float)scaleInput(left);

            // write the values to the motors
            MoveRobot(M1Power, M2Power);
            // Use left trigger to speed Up
            // Use right trigger to speed down
//          float GoUp = Range.clip(-gamepad1.left_stick_y,-1,1);
//          float M2Power = Range.clip(gamepad1.right_stick_y,-1,1);

//          double GoUp = (gamepad2.left_stick_y + 1) / 2 - (gamepad2.right_stick_y + 1) / 2;
//          double M3Power = Range.clip(-gamepad2.right_stick_y, -1, 1);
//          MoveArm(scaleInput(M3Power));

            double M3Power = (gamepad2.right_stick_y);
            M3Power = Range.clip(M3Power, -.25, .1);
            M3Power = (float)scaleInput(M3Power);
            MoveArm(M3Power);

            //Moves Hand

            double Servo1Gamepad = (gamepad2.left_trigger + 1) / 2;
            Servo1Gamepad = Range.clip(Servo1Gamepad, -1, 1);
            Servo1Gamepad = (float)scaleInput(Servo1Gamepad);
            Set_Servo_position(Servo1Gamepad);

            if (gamepad2.a) {
                if (Servo2.getPosition() == .8) {
                    Set_Servo2_position(.1);
                }
                else if (Servo2.getPosition() == .1) {
                    Set_Servo2_position(.8);
                }
                else {
                    Set_Servo2_position(.8);
                }
            }

            // scale the joystick value to make it easier to control
            // the robot more precisely at slower speeds.
//          right = (float)scaleInput(right);
//          left =  (float)scaleInput(left);


            /*
            ************************************
            */


            UpdateTelemetry();
            telemetry.addData("Text", "*** Robot Data***");
            telemetry.addData("arm tgt pwr", "Arm: " + String.format("%.2f", M3Power));
            telemetry.addData("left tgt pwr", "left pwr: " + String.format("%.2f", M1Power));
            telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", M2Power));
    }
}
