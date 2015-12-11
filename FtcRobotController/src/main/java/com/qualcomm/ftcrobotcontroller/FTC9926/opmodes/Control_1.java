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

    int MyReverse = 1;
    double Dpad = 1;
    boolean ChangeTopSpeed = true;
    boolean Claw = true;

    @Override
    public void start() {
        //
        // Call the PushBotHardware (super/base class) start method.
        //
        super.start ();
    }

    @Override
    public void loop() {

        if (gamepad1.a) {
            MyReverse = -1;
        }
        else if (gamepad1.b) {
            MyReverse = 1;
        }
        else {
            MyReverse = 1;
        }

        if (gamepad1.dpad_down && Dpad > 0.1) {
            if (ChangeTopSpeed) {
                Dpad = Dpad + 0.1;
                ChangeTopSpeed = false;
            }
        }
        else if (gamepad1.dpad_up) {
            if (ChangeTopSpeed) {
                Dpad = Dpad - 0.1;
                ChangeTopSpeed = false;
            }
        }
        else {
            ChangeTopSpeed = true;
        }

            //Backwards = true;

//        if (Backwards == false) {
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
        float M1Power = Range.clip(-gamepad1.left_stick_y * MyReverse * (float) Dpad, -1, 1 * (float) 0.8);
        float M2Power = Range.clip(gamepad1.right_stick_y * MyReverse * (float) Dpad, -1, 1 * (float) 0.8);

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

        if (gamepad2.right_bumper) {
            if (Claw) {
                if (Servo2.getPosition() == .5) {
                    Set_Servo2_position(.1);
                }
                else if (Servo2.getPosition() == .1) {
                    Set_Servo2_position(.5);
                }
                else {
                    Set_Servo2_position(.5);
                }
            }
        }

        UpdateTelemetry();
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Servo", "Servo/Arm:  " + String.format("%.2f", ServoPosition) + "/" + String.format("%.2f", GoUp));
        telemetry.addData("Power", "Power (L/R/Max): " + String.format("%.2f", M1Power) + "/" + String.format("%.2f", M2Power)+ "/" + String.format("%.2f", Dpad));
    }
}
