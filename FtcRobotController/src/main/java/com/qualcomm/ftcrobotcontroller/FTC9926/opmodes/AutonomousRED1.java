package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by vijay on 2/3/2016.
 */
public class AutonomousRED1 extends Telemetry9926 {


    public AutonomousRED1() {
        // Copying same logic as PushBotAuto.java
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

    @Override
    public void loop() {
        switch (move_state) {
            case 0:
                // adds one to the case
                move_state++;
                // gives a new value to Variance
                Variance = getRuntime();
                // resets the value of TimeNow
                TimeNow = 0;
                // moves to next state
                break;
            case 1:
                // moves the robot at 50% power
                MoveRobot(.6, .6);
                // if the robot moved for at least .75 seconds
                if (TimeNow > 2.7) {
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
                // if the robot stopped for at least .5 seconds
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
                // turns the robot with 60% power on Motor 1
                MoveRobot(.6, 0);
                // if the robot moved for at least 1.5 seconds
                if (TimeNow > 2) {
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
                if (TimeNow > .5) {
                    // adds one to the counter
                    move_state++;
                    // gives a new value to Variance
                    Variance = getRuntime();
                    // resets TimeNow
                    TimeNow = 0;
                }
                case 5:
                        // moves the robot at 50% power
                        MoveRobot(.6, .6);
                        // if the robot moved for at least .75 seconds
                        if (TimeNow > 1) {
                            // adds one to the case
                            move_state++;
                            // gives a new value to Variance
                            Variance = getRuntime();
                            // resets TimeNow
                            TimeNow = 0;
                        }
                        // moves to the next case
                        break;
                    case 6:
                        // stops the robot
                        MoveRobot(.6, 0);
                        // if the robot stopped for at least .5 seconds
                        if (TimeNow > 2) {
                            // adds one to the case
                            move_state++;
                            // gives a new value to Variance
                            Variance = getRuntime();
                            // resets TimeNow
                            TimeNow = 0;
                                }
                                // moves to the next case
                                break;

                            case 7:
                                // turns the robot with 60% power on Motor 1
                                MoveRobot(.6, .6);
                                // if the robot moved for at least 1.5 seconds
                                if (TimeNow > 3) {
                                    // adds one to move_state
                                    move_state++;
                                    // gives a new value to Variance
                                    Variance = getRuntime();
                                    // resets the value of TimeNow
                                    TimeNow = 0;
                                }
                                // moves on to the next case
                                break;

        }
    }
}