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
        // Sets SM2's position to 0.5
        Servo2.setPosition(.5);
        // If Gamepad 1's A button is pressed
        if (gamepad1.b) {
            Forwards = true;
        }
        // If Gamepad 1's B button is pressed
        else if (gamepad1.a) {
            Forwards = false;
        }
        else {
            if (Forwards) {
                // Go forwards
                float M1Power = Range.clip(-gamepad1.right_stick_y * (float) Dpad, -1, 1);
                float M2Power = Range.clip(gamepad1.left_stick_y * (float) Dpad, -1, 1);
                MoveRobot(M1Power, M2Power);
            }
            if (!Forwards) {
                // Go backwards
                float M1Power = Range.clip(gamepad1.left_stick_y * (float) Dpad, -1, 1);
                float M2Power = Range.clip(-gamepad1.right_stick_y * (float) Dpad, -1, 1);
                MoveRobot(M1Power, M2Power);
            }
        }
        // If neither of those are true

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
            // Makes "ChangeTopSpeed" false
            ChangeTopSpeed = true;
        }

        // Makes "M3Power" equal Gamepad 2's right stick
        double M3Power = (gamepad2.right_stick_y);
        // Adds boundaries to not exceed certain values
        M3Power = Range.clip(M3Power, -1, 1);
        // Adds "scaleInput" to make easier to control
        M3Power = (float)scaleInput(M3Power);
        // Writes the values to the arm
        MoveArm(M3Power * 0.2);

        // Makes "Servo1Gamepad" equal Gamepad 2's left trigger
        double Servo1Gamepad = gamepad2.left_trigger;
        // Makes boundaries to not exceed certain values
        Servo1Gamepad = Range.clip(Servo1Gamepad, 0, 1);
        // Writes the values to the motors
        Set_Servo_position(Servo1Gamepad);

        // If Gamepad 2's right bumper is pressed
        if (gamepad2.right_bumper) {
            // If "Claw" is true
            if (Claw) {
                // If SM2's position is 0.5
                if (Servo2.getPosition() == .5) {
                    // Set SM2's position to 0.1
                    Set_Servo2_position(.1);
                }
                // If SM2's position is 0.1
                else if (Servo2.getPosition() == .1) {
                    // Set SM2's position to 0.5
                    Set_Servo2_position(.5);
                }
                // If none of those are true
                else {
                    // Set SM2's position to 0.5
                    Set_Servo2_position(.5);
                }
            }
        }

        // Updates the telemetry
        UpdateTelemetry();
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Servo", "Servo/Arm:  " + String.format("%.2f", Servo1Gamepad) + "/" + String.format("%.2f", M3Power));
        telemetry.addData("Power", "Power: " + String.format("%.2f", Dpad));
    }
}
