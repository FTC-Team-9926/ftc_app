package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.util.Range;

/**
 * Created by Nicolas Bravo on 11/29/15
 * Used to move the robot with two gamepads
 * For use in the driver controlled section of the competition
 * Based on Control_1.java
 */
 
public class TwoGamepads extends Telemetry9926 {

    // Sets the default speed when the program starts
    double Dpad = 0.5;

    // Adding some booleans for when modifying the speed with the dpad
    boolean ChangeTopSpeed = true;

    // Sets the direction of the robot
    boolean Forwards = true;

    // Sets Aim Increments & Location
    int Aim_Increment = 40;
    int Aim_New_Location = 0;
    int Aim_Curr_Location =0;
    double Aim_Error = 20;

    @Override
    public void start() {
        // Call the PushBotHardware (super/base class) start method.
        super.start();

        // Sets default position of the servo
        Set_Servo2_position(1);
        reset_drive_encoders();
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

        // If Gamepad 1's or Gamepad 2's Dpad is pressed up and "Dpad" is less than 1
        if (gamepad1.dpad_up && Dpad < 1 || gamepad2.dpad_up && Dpad < 1) {
            // If "ChangeTopSpeed" is true
            if (ChangeTopSpeed) {
                // Adds 0.1 to "Dpad"
                Dpad= Dpad + .1;
                // Makes "ChangeTopSpeed" false
                ChangeTopSpeed = false;
            }
        }
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

        // If Gamepad 2's A button is being pressed and the B button is not
        if (gamepad2.b && !gamepad2.a) {
            // Extend the drawer slides
            MoveDrawer(.95);
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

        /**
         * Define Running with Encoders for ARM
         * RUN_USING_ENCODERS means: use the encoders to maintain a constant speed (specified by the setPower() method).
         * RUN_TO_POSITION means: go to the encoder target position specified by the setTargetPosition() method, and move at a speed UP-TO the one set by setPower()

         * Both commands use the encoders, but RUN_TO_POSITION uses them for both speed AND position control.

         * Note: If you have encoders installed you can read them at any time, regardless of the current motor mode.
         * One possible reason to switch between RUN_TO_POSITION and RUN_USING_ENCODERS is if you want to turn off the "positioning" feature while you change actions or target positions.
         * Source: http://ftcforum.usfirst.org/showthread.php?6369-Need-Help-With-Run_To_Position
         *
         */
        // Makes "M6Power" equal Gamepad 2's left stick
        double M6Power = (gamepad2.left_stick_y);
        // Adds boundaries to not exceed certain values
        M6Power = Range.clip(M6Power, -1, 1);

        // Common section
        //run_using_encoders();
        run_to_position();

        //Defines New Location of the Aim based on Power of Joystick
        Aim_New_Location = (int) (M6Power * Aim_Increment) + Aim_New_Location;
        Aim_New_Location = (int) Range.clip(Aim_New_Location, 0, 720);

//        Aim_Curr_Location = get_encoder_position();

//      Move the motor at full power
        MoveAim(1);
        Motor6.setTargetPosition(Aim_New_Location);


        // Move motor to reach the encoder value
        if (Aim_New_Location > (Aim_Curr_Location + Aim_Error)){
            //Move AIM in one direction at full power
//            MoveAim(Dpad);
        }
        else if (Aim_New_Location < (Aim_Curr_Location - Aim_Error)) {
//            MoveAim(-Dpad);
        }
        else {
//            MoveAim(0);
        }

        // Makes "Servo2Gamepad" equal 1 - Gamepad 2's left trigger
        double Servo2Gamepad = (1 - gamepad2.left_trigger);
        // Makes boundaries to not exceed certain values
        Servo2Gamepad = Range.clip(Servo2Gamepad, 0, 1);
        // Writes the values to the motor
        Set_Servo_position(Servo2Gamepad);

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
        telemetry.addData("AIM", "AIM Cur/Set: " + String.valueOf(Aim_Curr_Location) + "/" + String.valueOf(Aim_New_Location));
    }
}
