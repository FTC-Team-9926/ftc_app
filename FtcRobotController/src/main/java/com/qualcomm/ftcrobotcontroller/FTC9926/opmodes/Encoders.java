package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

/**
 * Created by jackhogan on 11/27/2015.
 */
public class Encoders extends Telemetry9926{

    @Override public void init(){
        run_using_encoders();
        reset_encoders();
    }

    @Override public void start(){
        super.start();
    }

    @Override public void loop(){
       while(!have_drive_encoders_reached(720,720)){
           MoveRobot(1,1);
       }
            MoveRobot(0, 0);

        UpdateTelemetry();
        telemetry.addData("Program", "Runtime:"+ getRuntime());
        telemetry.addData("Left","State:"+ left_encoder_count());
        telemetry.addData("Right","State:"+ right_encoder_count());
    }

    @Override public void stop(){}
}
