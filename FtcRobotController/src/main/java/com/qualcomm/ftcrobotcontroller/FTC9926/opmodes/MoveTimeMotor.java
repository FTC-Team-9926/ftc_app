package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Nicolas Bravo on 11/15/15.
 * It is similar to MoveTimeCombo.java, but only using DC motors.
 * It is for use in the autonomous section of an FTC match.
 */

public class MoveTimeMotor extends Telemetry9926{

    /* Defines the actual motors
     * Defines move_state as an integer with a value of 0 */
    DcMotor Motor1;
    DcMotor Motor2;
    int move_state = 0;

    @Override
    public void init() {
        Define_Hardware_Config_Names();
        /* Defines the motor names */
        Motor1 = hardwareMap.dcMotor.get("M1");
        Motor2 = hardwareMap.dcMotor.get("M2");
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
                 * Nothing happens
                  * Waits 5 seconds */
                move_state++;
                break;
            case 1:
                /* SECOND CASE
                 * Nothing moves
                  * Resets the DC Motors */
                if (getRuntime() > 5){
                    Motor1.setPower(0);
                    Motor2.setPower(0);
                    move_state++;
                }
                break;
            case 2:
                /* THIRD CASE
                 * DC Motors move
                  * Only one of the DC Motors move
                  * Waits 5 seconds, and then resets motors */
                Motor1.setPower(1);
                Motor2.setPower(0);
                if (getRuntime() > 10)
                {
                    Motor1.setPower(0);
                    Motor2.setPower(0);
                    move_state++;
                }
                break;
            case 3:
                /* FOURTH CASE
                 * Both DC Motors Move
                  * Waits 5 seconds, and then resets motors */
                Motor1.setPower(1);
                Motor2.setPower(1);
                if (getRuntime() > 15){
                    Motor1.setPower(0);
                    Motor2.setPower(0);
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
    }

    @Override
    public void stop() {
    }
}
