package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by vijay on 1/12/2016.
 */
public class AUTORED1CLIMBERS extends Telemetry9926 {


    public AUTORED1CLIMBERS() {
        // Copying same logic as PushBotAuto.java
    }

    int move_state = 0;
    double Variance = 0;
    double TimeNow = 0;
    double SM1_Position;
    double time = 0;
    DcMotor Motor3;
//initial state is 0


    @Override
    public void start() {
        //
        // Call the PushBotHardware (super/base class) start method.
        //
        Define_Hardware_Config_Names();
        Servo1 = hardwareMap.servo.get("SM1");
        Servo1.setPosition(SM1_Position);
        Motor2.setDirection(DcMotor.Direction.REVERSE);
        Set_Servo2_position(0.5);
        Motor3 = hardwareMap.dcMotor.get("M3");
        super.start();
    }

    @Override
    public void loop() {
        switch (move_state) {
            case 0:
                /* might need to reset motor */
                move_state++;
                Variance = getRuntime();
                TimeNow = 0;
                //moves to next state
                break;

            case 1:
                MoveRobot(.5, .5);
                if (TimeNow > 4) {
                    //if time is greater than one second then move forwards for 3 seconds
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;
            case 2:
                MoveRobot(0, 0);
                if (TimeNow > .5) {
                    //if time is greater than 4 second stop for one second
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;

            case 3:
                MoveRobot(.6, 0);
                if (TimeNow > 1.5) {
                    //if time is greater than 5 seconds point turn towards rescue zone
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;
            case 4:
                MoveRobot(0, 0);
                if (TimeNow > .5) {
                    //if time is greater than nine seconds stop for one second
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;

            case 5:
                MoveRobot(.6, .6);
                if (TimeNow > 1.1) {
                    //if time is greater than nine seconds move forwards for 5 seconds
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;
            case 6:
                MoveRobot(0, 0);
                if (TimeNow > 2) {
                    //if time is greater than nine seconds move forwards for 5 seconds
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;
            case 7:
                MoveArm(.3);
                if (TimeNow > 3)
                    move_state++;
                Variance = getRuntime();
                TimeNow = 0;
                break;
            case 8:
                move_state++;
                Variance = getRuntime();
                TimeNow = 0;
            case 9:
                SM1_Position = 0.2;
                Set_Servo_position(SM1_Position);
                if ((getRuntime() - time) >= 20)
                {
                    move_state++;
                    time = getRuntime();
                }
                break;
            case 10:
                MoveRobot(0, 0);
                if (TimeNow > 5) {
                    //if time is greater than nine seconds move forwards for 5 seconds
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;





            default:
                break;
        }

        TimeNow = getRuntime() - Variance;

        UpdateTelemetry();
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Clock: " + getRuntime());
        telemetry.addData("13", "Var: " + Variance);
        telemetry.addData("14", "TimeNow: " + TimeNow);
        //Telemetry data includes state number and time
    }

    @Override
    public void stop() {
//stops program
    }


}