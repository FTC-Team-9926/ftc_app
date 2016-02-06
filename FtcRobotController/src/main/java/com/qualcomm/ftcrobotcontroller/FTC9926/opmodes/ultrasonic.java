package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by ibravo on 2/6/16.
 * To test the ultrasonic sensor
 */
public class ultrasonic extends OpMode {

    // Ultrasonic implementation http://ftcforum.usfirst.org/showthread.php?4807-Has-anyone-come-up-with-a-way-to-measure-distance
    //LegacyModule UL1;
    UltrasonicSensor UL2;
    //OpticalDistanceSensor ODS1; //this one measures reflected light, not distance
    double Dist;

    @Override
    public void init(){

        // Initializing the attachments
        //UL1 = hardwareMap.legacyModule.get("UL1");
        //UL1.enable9v(5, true);
        UL2 = hardwareMap.ultrasonicSensor.get("UL2");
        //ODS1=hardwareMap.opticalDistanceSensor.get("ODS1");

    }

    @Override public void start(){
    }

    @Override public void loop(){
        Dist = UL2.getUltrasonicLevel();
        telemetry.addData("UL2","UL2: " & Dist);
    }

    @Override public void stop(){
    }

}
