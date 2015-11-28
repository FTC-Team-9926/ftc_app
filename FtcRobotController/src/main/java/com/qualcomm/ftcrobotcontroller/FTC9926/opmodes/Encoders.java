package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

/**
 * Created by jackhogan on 11/27/2015.
 */
public class Encoders extends Telemetry9926{
    @Override
    public void init(){}

    @Override
    public void start(){
        reset_encoders();
    }

    public void loop(){
       if(have_drive_encoders_reached(720,720)){
            MoveRobot(1,1);
        }
       else{
            MoveRobot(0,0);
        }
    }

    public void stop(){}
}
