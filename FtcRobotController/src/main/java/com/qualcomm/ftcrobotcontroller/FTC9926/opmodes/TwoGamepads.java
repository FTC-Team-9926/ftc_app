package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

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

    @Override
    public void start() {
        // Call the PushBotHardware (super/base class) start method.
        super.start ();
    }

    @Override
    public void loop() {
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
        // Use left trigger to speed Up
        // Use right trigger to speed down
//        float GoUp = Range.clip(-gamepad1.left_stick_y,-1,1);
//        float M2Power = Range.clip(gamepad1.right_stick_y,-1,1);

//        double GoUp = (gamepad2.left_stick_y + 1) / 2 - (gamepad2.right_stick_y + 1) / 2;
//        double M3Power = Range.clip(-gamepad2.right_stick_y, -1, 1);
//        MoveArm(scaleInput(M3Power));

        double M3Power = (gamepad2.right_stick_y);
        M3Power = Range.clip(M3Power, -.25, .1);
        M3Power = (float)scaleInput(M3Power);
        MoveArm(M3Power);

        //Moves Hand
        leftTrigger = gamepad2.left_trigger;
        rightTrigger = gamepad2.right_trigger;
        if (gamepad2.left_trigger >= leftTrigger | gamepad2.right_trigger >= rightTrigger) {
            double Servo1Gamepad = (gamepad2.left_trigger + 1) / 2 - (gamepad2.right_trigger + 1) / 2;
            SM1_Position = Range.clip(Servo1Gamepad, 0, 1);
            Servo1.setPosition(SM1_Position);
            leftTrigger = gamepad2.left_trigger;
            rightTrigger = gamepad2.right_trigger;
        }
        else {
            double Servo1Gamepad = (leftTrigger + 1) / 2 - (rightTrigger + 1) / 2;
            SM1_Position = Range.clip(Servo1Gamepad, 0, 1);
            Servo1.setPosition(SM1_Position);
            leftTrigger = gamepad2.left_trigger;
            rightTrigger = gamepad2.right_trigger;
        }

        //Moves claw
        if (gamepad2.right_bumper) {
            Set_Servo2_position(.8);
        }
        else if (gamepad2.left_bumper) {
            Set_Servo2_position(.1);
        }
        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
//        right = (float)scaleInput(right);
//        left =  (float)scaleInput(left);


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