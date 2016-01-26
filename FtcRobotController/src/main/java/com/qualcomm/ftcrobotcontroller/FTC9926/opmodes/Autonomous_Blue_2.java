package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

/**
 * Created by vijay on 12/15/2015.
 */
public class Autonomous_Blue_2 extends Telemetry9926{



    public Autonomous_Blue_2() {
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
                    //if time is greater than one second then move forwards
                    move_state++;
                    break;
                }
            case 2:
                if (getRuntime() > 3) {
                    MoveRobot(0, 0);
                    //if time is greater than 3 second stop for one second
                    move_state++;
                }
            case 3:
                if (getRuntime() > 4) {
                    MoveRobot(.5, 0);
                    //if time is greater than 4 seconds point turn towards rescue zone
                    move_state++;
                    break;
                }
            case 4:
                if (getRuntime() > 9) {
                    MoveRobot(0, 0);
                    //if time is greater than nine seconds stop for one second
                    move_state++;
                }
            case 5:
                if (getRuntime() > 10) {
                    MoveRobot(.7, .7);
                    //if time is greater than 10 seconds move forwards for 5 seconds
                    move_state++;
                    break;
                }
            case 6:
                if (getRuntime() > 15) {
                    MoveRobot(0, 0);
                    //if time is greater then 15 seconds stop for one second
                    move_state++;
                    break;
                }
            case 7:
                if (getRuntime() > 16) {
                    MoveRobot(.5, 0);
                    //if time is greater than 16 seconds point turn for 3 seconds
                    move_state++;
                    break;
                }
            case 8:
                if (getRuntime() > 19) {
                    MoveRobot(.5, .5);
                    //if time is greater than 19 seconds go forwards for 3 seconds
                    move_state++;
                    break;
                }
            case 9:
                if (getRuntime() > 22) {
                    MoveRobot(0, 0);
                    //if time is greater than 22 seconds stop the autonomous program
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







