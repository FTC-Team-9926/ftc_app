package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by vijay on 12/14/2015.
 */
public class Autonomous_Red_1 extends Telemetry9926 {



    public Autonomous_Red_1() {
        // Copying same logic as PushBotAuto.java
    }


    int move_state = 0;
//initial state is 0
    ;


    @Override
    public void start() {
        //
        // Call the PushBotHardware (super/base class) start method.
        //
        Motor2.setDirection(DcMotor.Direction.REVERSE);
        super.start();
    }

    @Override public void loop()
    {

        switch (move_state)
        {
            case 0:
                /* might need to reset motor */
                move_state++;
                //moves to next state
                break;

            case 1:
                if (getRuntime() > 1) {
                    MoveRobot(.5, .5);
                    //if time is greater than one second then move forwards for 3 seconds
                    move_state++;
                    break;
                }
            case 2:
                if (getRuntime() > 5) {
                    MoveRobot(0, 0);
                    //if time is greater than 4 second stop for one second
                    move_state++;
                }
            case 3:

                if (getRuntime() > 6) {
                    MoveRobot(.4, 0);
                    //if time is greater than 5 seconds point turn towards rescue zone
                    move_state++;
                    break;
                }
            case 4:
                if (getRuntime() > 9) {
                    MoveRobot(0, 0);
                    //if time is greater than nine seconds stop for one second
                    move_state++;
                    break;
                }
            case 5:
                if (getRuntime() > 10) {
                    MoveRobot(.6, .6);
                    //if time is greater than nine seconds move forwards for 5 seconds
                    move_state++;
                    break;
                }
            case 6:
                if (getRuntime() > 11) {
                    MoveRobot(0, 0);
                    //if time is greater than ten seconds stop for one second
                    move_state++;
                    break;
                }
            case 7:
                if (getRuntime() > 12) {
                    MoveRobot(.5, 0);
                    //if time is greater than ten seconds point turn for 3 seconds
                    move_state++;
                    break;
                }
            case 8:
                if (getRuntime() > 13) {
                    MoveRobot(0, 0);
                    //if time is greater than 12 seconds stop for one second
                    move_state++;
                    break;
                }
            case 9:
                if (getRuntime() > 14) {
                    MoveRobot(.4, .4);
                    //if time is greater than 13 seconds move forwards for 2 seconds
                    move_state++;
                    break;
                }
            case 10:
                if (getRuntime() > 16.5) {
                    MoveRobot(0, 0);
                    //if time is greater than 15 seconds stop
                    move_state++;
                    break;
                }
                break;

            default:
                break;
        }

        UpdateTelemetry();
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Time: " + getRuntime());
        //Telemetry data includes state number and time
    }

    @Override
    public void stop() {
//stops program
    }



}









