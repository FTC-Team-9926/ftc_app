package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.ftcrobotcontroller.FTC9926.opmodes.HardwareMap9926;
import com.qualcomm.ftcrobotcontroller.FTC9926.opmodes.Telemetry9926;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 Created by Nicolas Bravo on 12/17/15.
 For use in the Autonomous Portion of the match
 Moves the robot when on the Blue Team
 */
public class AutoBlue1 extends Telemetry9926{



    public AutoBlue1() {

    }

    // used for keeping track of the state
    int move_state = 0;
    // used to determine TimeNow
    double Variance = 0;
    // The amount of time we want the robot to move
    double TimeNow = 0;

    @Override
    public void start() {
        // call the PushBotHardware (super/base class) start method.
        super.start();
        // sets Motor2's direction to go backwards, since the motor is flipped
        Motor2.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override public void loop()
    {
        switch (move_state)
        {
            case 0:
                // adds one to the case
                move_state++;
                // gives a new value to Variance
                Variance = getRuntime();
                // resets the value of TimeNow
                TimeNow = 0;
                //moves to next state
                break;
            case 1:
                // moves the robot at 50% power
                MoveRobot(.5, .5);
                // if the robot moved for at least .75 seconds
                if (TimeNow > .75) {
                    // adds one to the case
                    move_state++;
                    // gives a new value to Variance
                    Variance = getRuntime();
                    // resets TimeNow
                    TimeNow = 0;
                }
                // moves to the next case
                break;
            case 2:
                // stops the robot
                MoveRobot(0, 0);
                // if the robot is stopped for at least .5 seconds
                if (TimeNow > .5) {
                    // adds one to the case
                    move_state++;
                    // gives a new value to Variance
                    Variance = getRuntime();
                    // resets TimeNow
                    TimeNow = 0;
                }
                // moves to the next case
                break;
            case 3:
                // turns the robot with 60% power on Motor 2
                MoveRobot(0, .6);
                // if the robot moved for at least 1.5 seconds
                if ( TimeNow > 2) {
                    // adds one to move_state
                    move_state++;
                    // gives a new value to Variance
                    Variance = getRuntime();
                    // resets the value of TimeNow
                    TimeNow = 0;
                }
                // moves on to the next case
                break;
            case 4:
                // stops the robot
                MoveRobot(0, 0);
                // if the robot stopped for at least .5 seconds
                if ( TimeNow > .5) {
                    // adds one to the counter
                    move_state++;
                    // gives a new value to Variance
                    Variance = getRuntime();
                    // resets TimeNow
                    TimeNow = 0;
                }
                // moves on to the next case
                break;
            case 5:
                // moves the robot forwards at 60% power
                MoveRobot(.6, .6);
                // if the robot moves for at least 2.2 seconds
                if ( TimeNow > 3) {
                    // increase the value of move_state
                    move_state++;
                    // give a new value to Variance
                    Variance = getRuntime();
                    // resets the value of TimeNow
                    TimeNow = 0;
                }
                // move on to the next case
                break;
            case 6:
                // stops the robot
                MoveRobot(0, 0);
                // if the robot stopped for at least 1 second
                if ( TimeNow > 1) {
                    // add one to move_state
                    move_state++;
                    // give a new value to Variance
                    Variance = getRuntime();
                    // reset the value of TimeNow
                    TimeNow = 0;
                }
                // moves on to the next case
                break;
            case 7:
                // turns the robot in reverse at 60% power on Motor 1
                MoveRobot(-.6, 0);
                // if the robot turned for at least 1 second
                if ( TimeNow > 1.3) {
                    // add one to the counter
                    move_state++;
                    // give a new value to Variance
                    Variance = getRuntime();
                    // reset the value of TimeNow
                    TimeNow = 0;
                }
                // move on to the next case
                break;
            case 8:
                // move the robot forwards at 50% power
                MoveRobot(.6, .6);
                // if the robot moved for at least 1.75 seconds
                if ( TimeNow > 2) {
                    // add one to move_state
                    move_state++;
                    // give a new value to Variance
                    Variance = getRuntime();
                    // reset the value of TimeNow
                    TimeNow = 0;
                }
                // move on to the next case
                break;
            case 9:
                // stops the robot
                MoveRobot(0, 0);
                // if the robot stopped for at least 50% power
                if ( TimeNow > 5) {
                    // increase the value of move_state
                    move_state++;
                    // give a new value to Variance
                    Variance = getRuntime();
                    // reset the value of TimeNow
                    TimeNow = 0;
                }
                // move on to the next case
                break;

            // if none of the cases match up with move_state
            default:
                // end the movement of the robot
                break;
        }

        // calculates the value of TimeNow by subtracting the value of getRuntime() with the amount of time that passed since the last time the value of getRuntime was saved in Variance
        TimeNow = getRuntime() - Variance;

        // telemetry data
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