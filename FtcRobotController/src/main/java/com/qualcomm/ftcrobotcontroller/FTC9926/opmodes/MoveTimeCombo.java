package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

/**
 * Created by Nicolas Bravo on 11/16/15
 * Uses both the DC Motors and the Servo Motors
 * It is for use in the autonomous section of the FTC match
 */
public class MoveTimeCombo extends Telemetry9926{
    public MoveTimeCombo (){
    }

    int move_state = 0;
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
                /* might need to reset motor */
                SM1_Position = 0.2;
                Set_Servo_position(SM1_Position);
                move_state++;
                time = getRuntime();
                break;
            case 1:
                if ((getRuntime() - time) >= 5){
                    StopMotor();
                    move_state++;
                    time = getRuntime();
                }
                break;
            case 2:
                SM1_Position = 0.3;
                Set_Servo_position(SM1_Position);
                MoveMotor(1,1); //move forwards
                if ((getRuntime() - time) >= 5)
                {
                    StopMotor();
                    move_state++;
                    time = getRuntime();
                }
                break;
            case 3:
                Turn(-1); //tilt left
                if ((getRuntime() - time) > 5){
                    StopMotor();
                    move_state++;
                    time = getRuntime();
                }
                break;
            default:
                break;
        }

        UpdateTelemetry();
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Time (Total): " + getRuntime());
        telemetry.addData("13", "Time (Task): " + (getRuntime() - time));
        telemetry.addData("14", "Servo 1: " + SM1_Position);
    }

    @Override
    public void stop() {

    }
}
