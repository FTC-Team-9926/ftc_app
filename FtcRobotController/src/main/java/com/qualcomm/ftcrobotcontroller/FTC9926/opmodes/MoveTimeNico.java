package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nicolas Bravo on 10/30/15.
 */
public class MoveTimeNico extends Telemetry9926{

    int move_state = 0;
    Servo Servo1;
    Servo Servo2;
    double SM1_Position;
    double SM2_Position;


    @Override
    public void init() {
        Define_Hardware_Config_Names();
        Servo1 = hardwareMap.servo.get("SM1");
        Servo2 = hardwareMap.servo.get("SM2");
        Servo1.setPosition(SM1_Position);
        Servo2.setPosition(SM2_Position);

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
                break;

            case 1:
                if (getRuntime() > 5){
                    move_state++;
                }
                break;

            case 2:
                SM1_Position = 0.2;
                SM2_Position = 0.2;
                Set_Servo_position(SM1_Position);
                Set_Servo_position(SM2_Position);
                if (getRuntime() > 10)
                {
                    move_state++;
                }
                break;

            case 3:
                SM1_Position = 0.3;
                SM2_Position = 0.3;
                Set_Servo_position(SM1_Position);
                Set_Servo_position(SM2_Position);
                if (getRuntime() > 15){
                    move_state++;
                }
                break;
            default:
                break;
        }

        UpdateTelemetry();
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Time: " + getRuntime());
        telemetry.addData("13","Servo 1: " + SM1_Position);
        telemetry.addData("14", "Servo 2: " + SM2_Position);
    }

    @Override
    public void stop() {

    }
}
