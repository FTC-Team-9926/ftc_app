package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.util.Range;

/**
 * Created by Nicolas Bravo on 11/29/15
 * Used to move the robot with two gamepads
 * For use in the driver controlled section of the competition
 * Based on Control_1.java
 */
 
public class TwoGamepads extends Telemetry9926 {

<<<<<<< 332652c3face884c81ab6870881e894c7230a24f
<<<<<<< 11cb17f163b7531f54ffb64a244e0045ac3bee83



    double Dpad = 0.6;
    boolean ChangeTopSpeed = true;
    boolean Claw = true;

=======
=======
    // Sets the default speed when the program starts
>>>>>>> Added comments
    double Dpad = 0.5;
    double Drawers = 0.5;

    // Adding some booleans for when modifying the speed with the dpad
    boolean ChangeTopSpeed = true;
    boolean ChangeDrawerSpeed = true;
<<<<<<< 332652c3face884c81ab6870881e894c7230a24f
>>>>>>> Added a way to change the speed of the drawer slides
=======

    // Sets the direction of the robot
>>>>>>> Added comments
    boolean Forwards = true;

    @Override
    public void start() {
        // Call the PushBotHardware (super/base class) start method.
        super.start ();

        // Sets default position of the servo
        Set_Servo2_position(1);
    }

    @Override
    public void loop() {
        // If Gamepad 1's A button is pressed
        if (gamepad1.b) {
            // The robot goes forwards
            Forwards = true;
        }
        // If Gamepad 1's B button is pressed
        else if (gamepad1.a) {
            // The robot goes backwards
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


        // If Gamepad 1's or Gamepad 2's Dpad is pressed up and "Dpad" is less than 0.7

        if (gamepad1.dpad_up && Dpad < .7 || gamepad2.dpad_up && Dpad < .7) {

        if (gamepad1.dpad_up && Dpad < .6 | gamepad2.dpad_up && Dpad < .6) {

            // If "ChangeTopSpeed" is true
            if (ChangeTopSpeed) {
                // Adds 0.1 to "Dpad"
                Dpad = Dpad + .1;
                // Makes "ChangeTopSpeed" false
                ChangeTopSpeed = false;
            }
        }
        // If Gamepad 1's Dpad is pressed down and "Dpad" is greater than 0.1


        // If Gamepad 1's or Gamepad 2's Dpad is pressed down and "Dpad" is greater than 0.2

        else if (gamepad1.dpad_down && Dpad > .2 || gamepad2.dpad_down && Dpad > .2) {

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

        /*
        =====================================

        * BEGIN DRAWER SLIDE SPEED *

        =====================================
         */
        if (gamepad2.dpad_right && Drawers < 1) {
            if (ChangeDrawerSpeed) {
                Drawers = Drawers + 0.05;
                ChangeDrawerSpeed = false;
            }
        }
        else if (gamepad2.dpad_left && Drawers > 0.05) {
            if (ChangeDrawerSpeed) {
                Drawers = Drawers - 0.05;
                ChangeDrawerSpeed = false;
            }
        }
        else {
            ChangeDrawerSpeed = true;
        }

        /*
        =====================================

        * END DRAWER SLIDE SPEED *

        =====================================
        */

        // If Gamepad 2's A button is being pressed and the B button is not
        if (gamepad2.b && !gamepad2.a) {
            // Extend the drawer slides
            MoveDrawer(Drawers);
        }
        // If Gamepad 2's B button is being pressed and the A button is not
        if (gamepad2.a && !gamepad2.b) {
            // Bring back the drawer slides
            MoveDrawer(-0.05);
        }
        // If neither are being pressed
        if (!gamepad2.b && !gamepad2.a) {
            // Don't move the drawer slides
            MoveDrawer(0);
        }

        // If Gamepad 2's right bumper OR left bumper are being pressed
        if (gamepad2.right_bumper || gamepad2.left_bumper) {
            // If Gamepad 2's right bumper is being pressed
            if (gamepad2.right_bumper) {
                // Extend the hook
                MovePull(-1);
            }
            // If Gamepad 2's left bumper is being pressed
            else if (gamepad2.left_bumper) {
                // Retract the hook
                MovePull(1);
            }
        }
        // If Gamepad 2's right bumper and left bumper are BOTH being pressed
        else if (gamepad2.right_bumper && gamepad2.left_bumper) {
            // Stop the hook
            MovePull(0);
        }
        // If Gamepad 2's right bumper and left bumper are NOT being pressed
        else if (!gamepad2.right_bumper && !gamepad2.left_bumper) {
            // Stop the hook
            MovePull(0);
        }

        // Makes "M3Power" equal Gamepad 2's right stick
        double M3Power = (-gamepad2.right_stick_y);
        // Adds boundaries to not exceed certain values
        M3Power = Range.clip(M3Power, -1, 1);
        // Adds "scaleInput" to make easier to control
        M3Power = (float)scaleInput(M3Power);
        // Writes the values to the arm
        MoveArm(M3Power * 0.2);


        // Makes "M6Power" equal Gamepad 2's left stick
        double M6Power = (gamepad2.left_stick_y);
        // Adds boundaries to not exceed certain values
        M6Power = Range.clip(M6Power, -1, 1);
        // Adds "scaleInput" to make easier to control
        M6Power = (float)scaleInput(M6Power);
        // If Gamepad 2's left stick (y) is less than 0
        if (gamepad2.left_stick_y < 0) {
            // Writes the values to the motor
            MoveAim(M6Power * 0.2);
        }
        // If Gamepad 2's left stick (y) is greater than
        else if (gamepad2.left_stick_y > 0) {
            // Writes the values to the motor
            MoveAim(M6Power * 0.4);
        }
        // If neither apply
        else {
            // Don't move the motor
            Motor6.setPower(0);
        }


        double Servo2Gamepad = (1 - gamepad2.left_trigger);
        // Makes boundaries to not exceed certain values
        Servo2Gamepad = Range.clip(Servo2Gamepad, 0, 1);
        // Writes the values to the motor
        Set_Servo_position(Servo2Gamepad);


        double LeftFlapper = (1-gamepad1.left_trigger);




        // Makes "LeftFlapper" equal 1 - Gamepad 1's left trigger
        double LeftFlapper = (1-gamepad1.left_trigger);
        // Makes boundaries to not exceed certain values

        LeftFlapper = Range.clip(LeftFlapper, 0, 1);
        // Writes the values to the motor
        Set_Servo2_position(LeftFlapper);

        // Makes "RightFlapper" equal Gamepad 1's right trigger
        double RightFlapper = gamepad1.right_trigger;
        // Makes boundaries to not exceed certain values
        RightFlapper = Range.clip(RightFlapper, 0, 1);
        // Writes the values to the motor
        Set_Servo3_position(RightFlapper);

        // Updates the telemetry
        UpdateTelemetry();
        // Displays a title
        telemetry.addData("Text", "*** Robot Data***");
        // Displays the values of the motors
        telemetry.addData("Servo", "Servo/Arm:  " + String.format("%.2f", Servo2Gamepad) + "/" + String.format("%.2f", M3Power));
        // Displays the power of the treads
        telemetry.addData("Power", "Power: " + String.format("%.2f", Dpad));
        // Displays the power of the drawer slides
        telemetry.addData("Drawer Slides", "Drawer Slides: " + String.format("%.2f", Drawers));
    }
}
