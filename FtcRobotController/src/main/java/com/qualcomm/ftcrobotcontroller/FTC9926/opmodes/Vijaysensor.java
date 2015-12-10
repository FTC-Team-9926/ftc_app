package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by vijay on 11/29/2015.
 */
public class Vijaysensor extends Telemetry9926{

    ColorSensor Color1;
    DcMotor Motor1;
    DcMotor Motor2;

    @Override
public void init () {
        Define_Hardware_Config_Names();
        Motor1 = hardwareMap.dcMotor.get("M1");
        Motor2 = hardwareMap.dcMotor.get("M2");
        Color1 = hardwareMap.colorSensor.get("C1");

    }

public void start () {

    }

public  void loop () {
olp
}


}








