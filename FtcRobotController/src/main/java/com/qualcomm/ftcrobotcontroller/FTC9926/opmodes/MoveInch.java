package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Nicolas Bravo on 11/18/15
 * Uses distance/time to move accurately
 * It is for use in the autonomous section of the FTC match
 */

public class MoveInch extends Telemetry9926 {

    /* Defines the motors
     * Defines move_state as an integer with a value of 0 */

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
                goForward();
                time = getRuntime();
                move_state++;
                break;
            case 1:
                if ((getRuntime() - time) >= (inch * 150)) {
                /* If getRuntime() minus time is greater than or equal to inch * amount of inches */
                    stopMotor();
                    time = getRuntime();
                    move_state++;
                }
                else {
                    time = getRuntime();
                }
                break;
            case 2:
                goReverse();
                time = getRuntime();
                move_state++;
                break;
            case 3:
                if ((getRuntime() - time) >= (inch * 75)) {
                    stopMotor();
                    time=getRuntime();
                    move_state++;
                }
                else {
                    time = getRuntime();
                }
                break;
            case 4:
                goForward();
                time = getRuntime();
                move_state++;
                break;
            case 5:
                if((getRuntime() - time) >= (inch * 25)) {
                    stopMotor();
                    time = getRuntime();
                    move_state++;
                }
                else {
                    time = getRuntime();
                }
                break;
            case 6:
                goReverse();
                time = getRuntime();
                move_state++;
                break;
            case 7:
                if ((getRuntime() - time) >= (inch * 100)) {
                    stopMotor();
                    time = getRuntime();
                    move_state++;
                }
                else {
                    time = getRuntime();
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
