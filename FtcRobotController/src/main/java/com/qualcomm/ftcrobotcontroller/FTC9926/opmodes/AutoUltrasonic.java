package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 Created by Nicolas Bravo on 2/5/16.
 For use in the Autonomous Portion of the match
 Moves the robot to the beacon and uses the legacy Ultrasonic sensor as a guide
 */

public class AutoUltrasonic extends Telemetry9926 {
    public AutoUltrasonic() {
        // Copying the same logic as PushBotAuto.java
    }

    @Override
    public void start() {
        // call the PushBotHardware (super/base class) start method.
        super.start();
        // sets Motor2's direction to go backwards, since the motor is flipped
        Motor2.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override public void loop()
    {
        Ultrasonic.readAnalog(30);
    }
}
