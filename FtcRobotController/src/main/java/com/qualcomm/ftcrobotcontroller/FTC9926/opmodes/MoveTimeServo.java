package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nicolas Bravo on 10/30/15.
 * It is similar to MoveTimeCombo.java, but only using servo motors.
 * It is for use in the autonomous section of an FTC match.
 */

public class MoveTimeServo extends Telemetry9926{
    /* Defines the actual motors
     * Defines move_state as an integer with a value of 0 */
    int move_state = 0;
    Servo Servo1;
    double SM1_Position;

    @Override
    public void init() {
        Define_Hardware_Config_Names();
        /* Defines the motor names */
        Servo1 = hardwareMap.servo.get("SM1");
        Servo1.setPosition(SM1_Position);
    }

    @Override
    public void start() {
    }

    @Override public void loop()
    {
        switch (move_state)
        {
            case 0:
                /* FIRST CASE
                 * Nothing moves
                 * Waits 5 seconds */
                move_state++;
                break;
            case 1:
                /* SECOND CASE
                 * Nothing moves */
                if (getRuntime() > 5){
                    move_state++;
                }
                break;
            case 2:
                /* THIRD CASE
                 * Servo Motors move
                 * Waits 5 seconds, and then resets motors */
                SM1_Position = 0.2;
                Set_Servo_position(SM1_Position);
                if (getRuntime() > 10)
                {
                    move_state++;
                }
                break;
            case 3:
                /* FOURTH CASE
                 * Servo Motors Move
                 * Waits 5 seconds, and then resets motors */
                SM1_Position = 0.3;
                Set_Servo_position(SM1_Position);
                if (getRuntime() > 15){
                    move_state++;
                }
                break;
            default:
                /* DEFAULT CASE
                 * States the default
                 * For use if move_state is greater than four */
                break;
        }

        UpdateTelemetry();
        /* TELEMETRY
         * Displays telemetry data on phone */
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Time: " + getRuntime());
        telemetry.addData("13", "Servo: " + SM1_Position);
    }

    @Override
    public void stop() {
    }
}
