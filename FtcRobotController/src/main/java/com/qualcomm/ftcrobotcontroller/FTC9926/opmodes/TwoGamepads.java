package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.util.Range;

/**
 * Created by Nicolas Bravo on 11/29/15
 * Used to move the robot with two gamepads
 * For use in the driver controlled section of the competition
 * Based on Control_1.java
 */
public class TwoGamepads extends Telemetry9926 {

    int MyReverse;
    double Dpad;
    boolean ChangeTopSpeed = true;
    boolean Claw = true;

    @Override
    public void start() {
        // Call the PushBotHardware (super/base class) start method.
        super.start ();
    }

    @Override
    public void loop() {
        Servo2.setPosition(.5);
        // Switch Drive direction
        if (gamepad1.a) {
            MyReverse = -1;
        }
        else if (gamepad1.b) {
            MyReverse = 1;
        }
        else {
            MyReverse = 1;
        }

        // Changes speed when Dpad is clicked
        if (gamepad1.dpad_up && Dpad < .9) {
            if (ChangeTopSpeed) {
                Dpad= Dpad + .1;
                ChangeTopSpeed = false;
            }
        }
        else if (gamepad1.dpad_down && Dpad > 0.1) {
            if (ChangeTopSpeed) {
                Dpad = Dpad - .1;
                ChangeTopSpeed = false;
            }
        }
        else {
            ChangeTopSpeed = true;
        }




        // Tells the amount to move
        float M1Power = Range.clip(gamepad1.left_stick_y * MyReverse * (float) Dpad, -1, 1);
        float M2Power = Range.clip(-gamepad1.right_stick_y * MyReverse * (float) Dpad, -1, 1);

        // write the values to the motors
        MoveRobot(M1Power, M2Power);

        // Determines the speed of arm
        double M3Power = (gamepad2.right_stick_y);
        M3Power = Range.clip(M3Power, -1, .1);
        M3Power = (float)scaleInput(M3Power);

        // Writes the values to the arm
        MoveArm(M3Power);

        // Determines speed of first gamepad
        double Servo1Gamepad = gamepad2.left_trigger;
        Servo1Gamepad = Range.clip(Servo1Gamepad, 0, 1);

        // Writes the values to the motors
        Set_Servo_position(Servo1Gamepad);

        // Determines the speed of the claw
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

        // Updates the telemetry
        UpdateTelemetry();
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Servo", "Servo/Arm:  " + String.format("%.2f", Servo1Gamepad) + "/" + String.format("%.2f", M3Power));
        telemetry.addData("Power", "Power (L/R/Max): " + String.format("%.2f", M1Power) + "/" + String.format("%.2f", M2Power)+ "/" + String.format("%.2f", Dpad));
    }
}
