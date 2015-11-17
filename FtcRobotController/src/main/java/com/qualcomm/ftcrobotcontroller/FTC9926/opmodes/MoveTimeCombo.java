package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by nicolasbravo on 11/16/15.
 */
public class MoveTimeCombo extends Telemetry9926{

    DcMotor Motor1;
    DcMotor Motor2;
    Servo Servo1;
    int move_state = 0;

    @Override
    public void init() {
        Define_Hardware_Config_Names();
        Motor1 = hardwareMap.dcMotor.get("M1");
        Motor2 = hardwareMap.dcMotor.get("M2");
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
                SM1_Position = 0.2;
                Set_Servo_position(SM1_Position);
                move_state++;
                break;

            case 1:
                if (getRuntime() > 5){
                    Motor1.setPower(0);
                    Motor2.setPower(0);
                    move_state++;
                }
                break;

            case 2:
                SM1_Position = 0.3;
                Set_Servo_position(SM1_Position);
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
                Motor1.setPower(1);
                Motor2.setPower(1);
                if (getRuntime() > 15){
                    Motor1.setPower(0);
                    Motor2.setPower(0);
                    move_state++;
                }
                break;
            default:
                break;
        }

        UpdateTelemetry();
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Time: " + getRuntime());
        telemetry.addData("13", "Servo 1: " + SM1_Position);
    }

    @Override
    public void stop() {

    }
}
