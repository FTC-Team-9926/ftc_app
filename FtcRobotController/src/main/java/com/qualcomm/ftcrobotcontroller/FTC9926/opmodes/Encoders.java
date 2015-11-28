package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

/**
 * Created by jackhogan on 11/27/2015.
 */
public class Encoders extends Telemetry9926{

    public void start(){
        super.start ();
        reset_encoders();
        run_using_encoders();
    }

    public void loop(){
       if(have_drive_encoders_reached(720,720)){
            MoveRobot(0,0);
        }
       else{
            MoveRobot(1,1);
        }
        UpdateTelemetry();
        telemetry.addData("Program", "Runtime:"+ getRuntime());
        telemetry.addData("Left","State:"+ left_encoder_count());
        telemetry.addData("Right","State:"+ right_encoder_count());
    }

    public void stop(){}
}
