package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nicolas Bravo on 10/30/15.
 */
public class MoveTimeServo extends Telemetry9926{

    int move_state = 0;
    Servo Servo1;
    double SM1_Position;
    double time = 0;


    @Override
    public void init() {
        Define_Hardware_Config_Names();
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
                /* might need to reset motor */
                move_state++;
                time = getRuntime();
                break;

            case 1:
                if ((getRuntime() - time) >= 5){
                    move_state++;
                    time = getRuntime();
                }
                break;

            case 2:
                SM1_Position = 0.2;
                Set_Servo_position(SM1_Position);
                if ((getRuntime() - time) >= 5)
                {
                    move_state++;
                    time = getRuntime();
                }
                break;

            case 3:
                SM1_Position = 0.3;
                Set_Servo_position(SM1_Position);
                if ((getRuntime() - time) >= 5){
                    move_state++;
                    time = getRuntime();
                }
                break;
            default:
                break;
        }

        UpdateTelemetry();
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Time (Total): " + getRuntime());
        telemetry.addData("13", "Time (Task): " + (getRuntime() - time));
        telemetry.addData("14", "Servo 1: " + SM1_Position);
    }

    @Override
    public void stop() {

    }
}
