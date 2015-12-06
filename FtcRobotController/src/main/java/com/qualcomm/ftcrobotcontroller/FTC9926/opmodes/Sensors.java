package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import android.graphics.Color;

import com.qualcomm.robotcore.robocol.Telemetry;


/**
 * Created by jackhogan on 12/6/2015.
 */
public class Sensors extends Telemetry9926{

    @Override
    public void init(){}

    public void start(){
        super.start();
    }

    public void loop() {
        //color sensor
        turn_color_sensor_light_on(true);
        while(is_button_pressed() != true){
        telemetry.addData("Clear: ", Color1.alpha());
        telemetry.addData("Red: ", Color1.red());
        telemetry.addData("Green: ", Color1.green());
        telemetry.addData("Blue: ", Color1.blue());
        //hue will not be used
        }
        turn_color_sensor_light_on(false);
        //touch sensor
        int a = 0;
        while(a <= 5){
            if(is_button_pressed() == true){
                a++;
                telemetry.addData("Touch Sensor State: ", Touch1.isPressed());
            }
        }
        //light sensor
    }

    @Override
    public void stop(){}
}
