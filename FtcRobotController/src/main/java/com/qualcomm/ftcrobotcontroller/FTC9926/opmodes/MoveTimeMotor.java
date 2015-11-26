package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

/**
 * Created by Nicolas Bravo on 10/30/15
 * Uses just the DC Motors
 * It is for use in the autonomous section of the FTC match
 */
public class MoveTimeMotor extends Telemetry9926{
    public MoveTimeMotor (){
    }

    double time = 0;

    int move_state = 0;

    @Override
    public void init() {
    }

    @Override
    public void start() {
        super.start ();
    }

    @Override public void loop()
    {
        switch (move_state)
        {
            case 0:
                /* might need to reset motor */
                time = getRuntime();
                move_state++;
                break;
            case 1:
                if ((getRuntime() - time) >= 5){
                    Stop();
                    time = getRuntime();
                    move_state++;
                }
                break;
            case 2:
                Turn(-1); //rotate left
                if ((getRuntime() - time) >= 5)
                {
                    Stop();
                    time = getRuntime();
                    move_state++;
                }
                break;
            case 3:
                Move(1,1); //move forwards
                if ((getRuntime() - time) >= 5){
                    Stop();
                    time = getRuntime()
                    ;move_state++;
                }
                break;
            default:
                break;
        }

        UpdateTelemetry();
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Time (Total): " + getRuntime());
        telemetry.addData("13", "Time (Task): " + (getRuntime() - time));
    }

    @Override
    public void stop() {

    }
}
