<<<<<<< 77b2f7b42eb340e150c0c7deeba4a0601ea7a9d0
/* Copyright (c) 2014, 2015 Technologies Inc
<<<<<<< 133e5af2fee5b460f04e950b411384bc2d4fd568

=======
>>>>>>> Removed duplicated with Git.
=======
/* Copyright (c) 2014, 2015 Qualcomm Technologies Inc

>>>>>>> Edits
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.
<<<<<<< 77b2f7b42eb340e150c0c7deeba4a0601ea7a9d0
<<<<<<< 133e5af2fee5b460f04e950b411384bc2d4fd568

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
=======
Neither the name of Technologies Inc nor the names of its contributors
>>>>>>> Removed duplicated with Git.
=======

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
>>>>>>> Edits
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.FTC9926.opmodes.*;
<<<<<<< 77b2f7b42eb340e150c0c7deeba4a0601ea7a9d0
import com.qualcomm.ftcrobotcontroller.FTC9926.opmodes.HardwareMap9926;
import com.qualcomm.ftcrobotcontroller.FTC9926.opmodes.MoveForward;
import com.qualcomm.ftcrobotcontroller.FTC9926.opmodes.MoveInch;
//import com.qualcomm.ftcrobotcontroller.FTC9926.opmodes.MoveServo;
//import com.qualcomm.ftcrobotcontroller.FTC9926.opmodes.MoveStick;
import com.qualcomm.ftcrobotcontroller.FTC9926.opmodes.MoveTank;
import com.qualcomm.ftcrobotcontroller.FTC9926.opmodes.MoveTimeCombo;
import com.qualcomm.ftcrobotcontroller.FTC9926.opmodes.MoveTimeMotor;
import com.qualcomm.ftcrobotcontroller.FTC9926.opmodes.MoveTimeServo;
import com.qualcomm.ftcrobotcontroller.FTC9926.opmodes.TouchOp;
=======
>>>>>>> Edits
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

/**
 * Register Op Modes
 */
public class FtcOpModeRegister implements OpModeRegister {

  /**
   * The Op Mode Manager will call this method when it wants a list of all
   * available op modes. Add your op mode to the list to enable it.
   *
   * @param manager op mode manager
   */
  public void register(OpModeManager manager) {

    /*
     * register your op modes here.
     * The first parameter is the name of the op mode
     * The second parameter is the op mode class property 
     *
     * If two or more op modes are registered with the same name, the app will display an error.
<<<<<<< 77b2f7b42eb340e150c0c7deeba4a0601ea7a9d0
<<<<<<< 133e5af2fee5b460f04e950b411384bc2d4fd568
=======

>>>>>>> Removed duplicated with Git.
    */
    manager.register("MoveForward", MoveForward.class);

    manager.register("NullOp", NullOp.class);

    //manager.register("MatrixK9TeleOp", MatrixK9TeleOp.class);
//    manager.register("K9TeleOp", K9TeleOp.class);
//    manager.register("K9Line", K9Line.class);
//    manager.register ("PushBotAuto", PushBotAuto.class);
//    manager.register ("PushBotManual", PushBotManual.class);

//    manager.register("MoveForward", MoveForward.class);
    manager.register("Inch", MoveInch.class);
//    manager.register("Tank", MoveTank.class);
//    manager.register("Combo", MoveTimeCombo.class);
//    manager.register("Motor", MoveTimeMotor.class);
//    manager.register("TimeServo", MoveTimeServo.class);
//    manager.register("Touch", TouchOp.class);

//    manager.register("NullOp", NullOp.class);
    */
    manager.register("MoveForward", MoveForward.class);
//    manager.register("Servo", MoveServo.class);
//    manager.register("Stick", MoveStick.class);
    manager.register("Tank", MoveTank.class);
    manager.register("Touch", TouchOp.class);
    manager.register("Servo", MoveTimeServo.class);
    manager.register("Motor", MoveTimeMotor.class);
    manager.register("Combo", MoveTimeCombo.class);
    manager.register("NullOp", NullOp.class);

    //manager.register("MatrixK9TeleOp", MatrixK9TeleOp.class);
    manager.register("K9TeleOp", K9TeleOp.class);
    manager.register("K9Line", K9Line.class);
//    manager.register ("PushBotAuto", PushBotAuto.class);
    manager.register ("PushBotManual", PushBotManual.class);

    /*
     * Uncomment any of the following lines if you want to register an op mode.
     */

    //manager.register("AdafruitRGBExample", AdafruitRGBExample.class);
    //manager.register("ColorSensorDriver", ColorSensorDriver.class);

    //manager.register("IrSeekerOp", IrSeekerOp.class);
    //manager.register("CompassCalibration", CompassCalibration.class);
    //manager.register("I2cAddressChangeExample", LinearI2cAddressChange.class);


    //manager.register("NxtTeleOp", NxtTeleOp.class);

    //manager.register("LinearK9TeleOp", LinearK9TeleOp.class);
    //manager.register("LinearIrExample", LinearIrExample.class);


//    manager.register("K9TeleOp", K9TeleOp.class);
//    manager.register("K9IrSeeker", K9IrSeeker.class);
//    manager.register("K9Line", K9Line.class);


    //manager.register ("PushBotManual1", PushBotManual1.class);
    //manager.register ("PushBotAutoSensors", PushBotAutoSensors.class);
    //manager.register ("PushBotIrEvent", PushBotIrEvent.class);

<<<<<<< 77b2f7b42eb340e150c0c7deeba4a0601ea7a9d0
     /* The following example op modes are designed to work with a pushbot-style robot.
     *  - PushBotManual is a driver controled (tank drive) op mode.
     *  - PushBotAuto uses the event driven (non linear) OpMode class for autonomous operation.
     *  - PushBotDriveTouch uses the LinearOpMode class and shows how to autonomously drive if a button is not pressed.
     *  - PushBotIrSeek uses the LinearOpMode class and shows how to track an IR beacon.
     *  - PushBotSquare uses the LinearOpMOde class and shows how to drive in a square pattern autonomously.
     */
=======
>>>>>>> Edits

    //manager.register("PushBotManual", PushBotManual.class);
    //manager.register("PushBotAuto", PushBotAuto.class);

// Linear Programs
//    manager.register("PushBotDriveTouch", PushBotDriveTouch.class);
//    manager.register("PushBotIrSeek", PushBotIrSeek.class);
//    manager.register("PushBotSquare", PushBotSquare.class);
    //manager.register ("PushBotManualSensors", PushBotManualSensors.class);
    //manager.register ("PushBotOdsDetectEvent", PushBotOdsDetectEvent.class);
    //manager.register ("PushBotOdsFollowEvent", PushBotOdsFollowEvent.class);
    //manager.register ("PushBotTouchEvent", PushBotTouchEvent.class);


    //manager.register("PushBotDriveTouch", PushBotDriveTouch.java);
    //manager.register("PushBotIrSeek", PushBotIrSeek.java);
    //manager.register("PushBotSquare", PushBotSquare.java);
  }
}
