package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

/**
 * Created by jackhogan on 11/27/2015.
 */
public class Encoders extends Telemetry9926{
    boolean loop_exit = false;
    @Override
    public void init(){}

    @Override
    public void start(){}

    public void loop(){
        if(Motor1.getCurrentPosition() <= 720){
            Motor1.setPower(1);
            Motor2.setPower(1);
        }
        else{
            Motor1.setPower(0);
            Motor2.setPower(0);
        }
    }
}
