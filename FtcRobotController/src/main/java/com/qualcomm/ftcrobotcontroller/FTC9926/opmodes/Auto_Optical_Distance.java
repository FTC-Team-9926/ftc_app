package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.robocol.Telemetry;

/**
 * Created by vijay on 11/20/2015.
 */
public class Auto_Optical_Distance extends Telemetry9926 {

    DcMotor Motor1;
    DcMotor Motor2;
    OpticalDistanceSensor Distance1;

    @Override
    public void init() {
        // Link with Phone Configuration Names
        Motor1 = hardwareMap.dcMotor.get("M1");
        Motor2 = hardwareMap.dcMotor.get("M2");
        Distance1 = hardwareMap.opticalDistanceSensor.get("D1");

    }

    @Override
    public void loop() {


    }

}