package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.util.Range;

/**
 * Created by Nicolas Bravo on 11/29/15
 * Used to move the robot with two gamepads
 * For use in the driver controlled section of the competition
 * Based on Control_1.java
 */
 
public class TwoGamepads extends Telemetry9926 {

    double Dpad = 0.5;
    boolean ChangeTopSpeed = true;
    boolean Claw = true;
    boolean Forwards = true;
    @Override
    public void start() {
        // Call the PushBotHardware (super/base class) start method.
        super.start ();
    }

    @Override
    public void loop() {
        // If Gamepad 1's A button is pressed
        if (gamepad1.b) {
            Forwards = true;
        }
        // If Gamepad 1's B button is pressed
        else if (gamepad1.a) {
            Forwards = false;
        }
        // If neither of those are pressed
        else {
            // If "Forwards" is true
            if (Forwards) {
                // Calculates values to go forwards at a certain speed
                float M1Power = Range.clip(-gamepad1.right_stick_y * (float) Dpad, -1, 1);
                float M2Power = Range.clip(gamepad1.left_stick_y * (float) Dpad, -1, 1);
                // Writes values to motors
                MoveRobot(M1Power, M2Power);
            }
            // If "Forwards" is false
            if (!Forwards) {
                // Calculates values to go backwards at a certain speed
                float M1Power = Range.clip(gamepad1.left_stick_y * (float) Dpad, -1, 1);
                float M2Power = Range.clip(-gamepad1.right_stick_y * (float) Dpad, -1, 1);
                // Writes values to motors
                MoveRobot(M1Power, M2Power);
            }
        }

        // If Gamepad 1's Dpad is pressed up and "Dpad" is less than 0.9
        if (gamepad1.dpad_up && Dpad < .9) {
            // If "ChangeTopSpeed" is true
            if (ChangeTopSpeed) {
                // Adds 0.1 to "Dpad"
                Dpad= Dpad + .1;
                // Makes "ChangeTopSpeed" false
                ChangeTopSpeed = false;
            }
        }
        // If Gamepad 1's Dpad is pressed down and "Dpad" is greater than 0.1
        else if (gamepad1.dpad_down && Dpad > 0.1) {
            // If "ChangeTopSpeed" is true
            if (ChangeTopSpeed) {
                // Subtracts 0.1 from "Dpad"
                Dpad = Dpad - .1;
                // Makes "ChangeTopSpeed" false
                ChangeTopSpeed = false;
            }
        }
        // If neither of those are true
        else {
            // Makes "ChangeTopSpeed" true
            ChangeTopSpeed = true;
        }

        // Makes "M3Power" equal Gamepad 2's right stick
        double M3Power = (-gamepad2.right_stick_y);
        // Adds boundaries to not exceed certain values
        M3Power = Range.clip(M3Power, -1, 1);
        // Adds "scaleInput" to make easier to control
        M3Power = (float)scaleInput(M3Power);
        // Writes the values to the arm
        MoveArm(M3Power * 0.2);

        // Makes "Servo2Gamepad" equal Gamepad 2's left trigger
        double Servo2Gamepad = (1 - gamepad2.left_trigger);
        // Makes boundaries to not exceed certain values
        Servo2Gamepad = Range.clip(Servo2Gamepad, 0, 1);
        // Writes the values to the motors
        Set_Servo_position(Servo2Gamepad);

        // Updates the telemetry
        UpdateTelemetry();
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Servo", "Servo/Arm:  " + String.format("%.2f", Servo2Gamepad) + "/" + String.format("%.2f", M3Power));
        telemetry.addData("Power", "Power: " + String.format("%.2f", Dpad));
    }
}