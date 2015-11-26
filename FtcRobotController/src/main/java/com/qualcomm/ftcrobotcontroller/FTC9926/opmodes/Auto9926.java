package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

/**
 * Created by ibravo on 11/25/15.
 * Based on PushBotAuto class
 */
public class Auto9926 extends Telemetry9926 {

    public Auto9926 (){
        // Copying same logic as PushBotAuto.java
    }


    int move_state = 0;


    @Override
    public void start() {
        //
        // Call the PushBotHardware (super/base class) start method.
        //
        super.start ();

    }
    @Override public void loop()
    {
        switch (move_state)
        {
            case 0:
                /* might need to reset motor */
                move_state++;
                break;

            case 1:
                if (getRuntime() > 5){
                    MoveRobot(.01, .01);
                    move_state++;
                    break;
                }
            case 2:
                if (getRuntime() > 10){
                    MoveRobot(-.01, -.01);
                    move_state++;
                    break;
                }
            case 3:
                if (getRuntime() > 15){
                    MoveRobot(0, 0);
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
    }

    @Override
    public void stop() {

    }



}
