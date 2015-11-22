package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Nicolas Bravo on 11/18/15
 * Uses distance/time to move accurately
 * It is for use in the autonomous section of the FTC match
 */

public class MoveInch extends Telemetry9926 {

    /* Defines the actual motors
     * Defines move_state as an integer with a value of 0 */
    DcMotor Motor1;
    DcMotor Motor2;
    int move_state = 0;
    double inch = 0.08769231;
    double time = 0;

    @Override
    public void init() {
        Define_Hardware_Config_Names();
        /* Defines the motor names */
        Motor1 = hardwareMap.dcMotor.get("M1");
        Motor2 = hardwareMap.dcMotor.get("M2");
        Motor2.setDirection(DcMotor.Direction.REVERSE);
        /* the amount of seconds it takes to drive an inch */
    }

    @Override
    public void start() {
    }

    @Override public void loop()
    {

        switch (move_state)
        {
            case 0:
                Motor1.setPower(1);
                Motor2.setPower(1);
                move_state++;
                break;
            case 1:
                if (getRuntime() >= (inch * 150) + time) {
                /* If getRuntime() is equal to or greater than 150 inches/second + time already passed */
                    Motor1.setPower(0);
                    Motor2.setPower(0);
                    time = time + (inch * 150);
                    move_state++;
                }
                break;
            default:
                break;
        }

        UpdateTelemetry();
        /* TELEMETRY
         * Displays telemetry data on phone */
        telemetry.addData("M1","M1_Power: " + Motor1.getPower());
        telemetry.addData("M2","M2 Power: " + Motor2.getPower());
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Time: " + getRuntime());
    }

    @Override
    public void stop() {
    }
}
