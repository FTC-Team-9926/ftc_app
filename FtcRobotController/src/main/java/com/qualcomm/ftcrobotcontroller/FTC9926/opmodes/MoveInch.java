package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

/**
 * Created by Nicolas Bravo on 11/18/15
 * Uses distance/time to move accurately
 * It is for use in the autonomous section of the FTC match
 */

public class MoveInch extends Telemetry9926 {

    public MoveInch (){
    }

    /* Defines the motors
     * Defines move_state as an integer with a value of 0 */

    int move_state = 0;
    /* the amount of seconds it takes to drive an inch */
    double inch = 0.08769231;
    double time = 0;

    @Override
    public void init() {
    }

    @Override
    public void start() {
        super.start();
    }

    @Override public void loop()
    {

        switch (move_state)
        {
            case 0:
                /* CASE ZERO
                The "reset" of the program
                 */
                time = getRuntime();
                move_state++;
                break;
            case 1:
                /* CASE ONE
                First moves for a foot
                Then stops and prepares for the next case
                 */
                MoveMotor(1, 1);
                if ((getRuntime() - time) >= (inch * 12)) {
                /* If getRuntime() minus time is greater than or equal to inch * amount of inches */
                    Stop();
                    time = getRuntime();
                    move_state++;
                }
                break;
            case 2:
                /* SECOND CASE
                First moves backwards for a foot
                Then stops and prepares for the next case
                 */
                MoveMotor(-1, -1);
                if ((getRuntime() - time) >= (inch * 12)) {
                    Stop();
                    time=getRuntime();
                    move_state++;
                }
                break;
            case 3:
                /* THIRD CASE
                First moves slightly to the right for a foot
                Then stops and prepares for the next case
                 */
                MoveMotor(0.7, 1);
                if((getRuntime() - time) >= (inch * 12)) {
                    Stop();
                    time = getRuntime();
                    move_state++;
                }
                break;
            case 4:
                /* FOURTH CASE
                First tank turns to the right for three seconds
                Then stops and prepares for the next case
                 */
                Turn(1);
                if ((getRuntime() - time) >= 3) {
                    Stop();
                    time = getRuntime();
                    move_state++;
                }
                break;
            case 5:
                /* FIFTH CASE
                First tank turns to the left for three seconds
                Then stops and prepares for the next case
                 */
                Turn(-1);
                if ((getRuntime() - time) >= 3) {
                    Stop();
                    time = getRuntime();
                    move_state++;
                }
                break;
            case 6:
                /* SIXTH CASE
                First moves the arm forwards for 0.3 seconds
                While moving the servo 0.5
                Then stops and prepares for the next case
                 */
                Arm(1, 0.5);
                if ((getRuntime() - time) >= 0.3){
                    Stop();
                    time = getRuntime();
                    move_state++;
                }
                break;
            default:
                break;
        }

        UpdateTelemetry();
        /* TELEMETRY
         * Displays telemetry data on phone */
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Time (Total): " + getRuntime());
    }

    @Override
    public void stop() {
    }
}