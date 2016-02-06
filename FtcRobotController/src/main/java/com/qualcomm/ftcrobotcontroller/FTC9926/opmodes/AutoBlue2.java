package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

/**
 Created by Nicolas Bravo on 12/17/15.
 For use in the Autonomous Portion of the match
 Moves the robot when on the Blue Team
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor;

public class AutoBlue2 extends Telemetry9926 {



    public AutoBlue2() {
        // Copying same logic as PushBotAuto.java
    }

    int move_state = 0;
    double Variance = 0;
    double TimeNow = 0;
//initial state is 0


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
                Variance = getRuntime();
                TimeNow = 0;
                //moves to next state
                break;

            case 1:
                MoveRobot(.5, .5);
                if (TimeNow > .75) {
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
                MoveRobot(0, .5);
                if ( TimeNow > 1.5) {
                    //if time is greater than 5 seconds point turn towards rescue zone
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;
            case 4:
                MoveRobot(0, 0);
                if ( TimeNow > .5) {
                    //if time is greater than nine seconds stop for one second
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;

            case 5:
                MoveRobot(.6, .6);
                if ( TimeNow > 3.5) {
                    //if time is greater than nine seconds move forwards for 5 seconds
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;

            case 6:
                MoveRobot(0, 0);
                if ( TimeNow > 1) {
                    //if time is greater than ten seconds stop for one second
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;
            case 7:
                MoveRobot(-.5, 0);
                if ( TimeNow > 1.1) {
                    //if time is greater than ten seconds stop for one second
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;
            case 8:
                MoveRobot(.5, .5);
                if ( TimeNow > 1.5) {
                    //if time is greater than ten seconds stop for one second
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;
            case 9:
                MoveRobot(0, 0);
                if ( TimeNow > 5) {
                    //if time is greater than ten seconds stop for one second
                    move_state++;
                    Variance = getRuntime();
                    TimeNow = 0;
                }
                break;

/*            case 7:
                if (getRuntime() > 13) {
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
*/
            default:
                break;
        }

        TimeNow = getRuntime() - Variance;

        UpdateTelemetry();
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Clock: " + getRuntime());
        telemetry.addData("13", "Var: "+ Variance);
        telemetry.addData("14", "TimeNow: "+ TimeNow );
        //Telemetry data includes state number and time
    }

    @Override
    public void stop() {
//stops program
    }



}
