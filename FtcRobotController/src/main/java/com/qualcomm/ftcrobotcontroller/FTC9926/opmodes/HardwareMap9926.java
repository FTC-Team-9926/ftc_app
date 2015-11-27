package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by ibravo on 10/30/15.
 * To place all the hardware components
 *
 */
public class HardwareMap9926 extends OpMode {

     //Define what hardware to use:
    // Motor Controller
//    private DcMotorController v_dc_motor_controller_drive;

    // Motor Left and encoder
//    private DcMotor v_motor_left_drive;
//    final int v_channel_left_drive = 1;

    // Servo ARM
    Servo Servo1;
    double SM1_Position;
    double Get_Servo_Position;
    DcMotor Motor1;
    DcMotor Motor2;
    DcMotor Motor3;



    @Override
    public void init(){

        // Define Config Name in Driver Station
        double l_hand_position = 0.5;

        Servo1 = hardwareMap.servo.get("SM1");
        SM1_Position = 0.5;
        Motor1 = hardwareMap.dcMotor.get("M1");
        Motor2 = hardwareMap.dcMotor.get("M2");
        Motor3 = hardwareMap.dcMotor.get("M3");

    }

    @Override public void start(){
    }

    @Override public void loop(){
    }

    @Override public void stop(){
    }

    //--------------------------------------------------------------------------
    // Access the hand position.
    //--------

    public void Define_Hardware_Config_Names() {
        Servo1 = hardwareMap.servo.get("SM1");
    }

    public double Get_Servo_Position()
    {
//        SM1_Position = 0.2;
        Servo1 = hardwareMap.servo.get("SM1");
        return Servo1.getPosition();


//        return Servo1.getPosition();

    } // PushBotManual::a_hand_position

    //--------------------------------------------------------------------------
    // Set the hand position.
    //--------

    void Set_Servo_position (double p_position)
    {
        // Ensure the specifiec value is legal.
//        double l_position = Range.clip(p_position, Servo.MIN_POSITION,Servo.MAX_POSITION);

        SM1_Position = Range.clip(p_position,0,1);

        Servo1.setPosition(SM1_Position);

        // Set the value.
//        v_servo_left_hand.setDirection(1);
//        v_servo_left_hand.setPosition(.2);

    } // PushBotManual::m_hand_position

    /*
 * This method scales the joystick input so for low joystick values, the
 * scaled value is less than linear.  This is to make it easier to drive
 * the robot more precisely at slower speeds.
 */
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

    void MoveRobot (double Engine1, double Engine2)
    {
        {
            if (Motor1 != null)
            {
                Motor1.setPower (Engine1);
            }

        } // set_drive_power
            if (Motor2 != null)
            {
                Motor2.setPower (Engine2);
            }
    }
    void MoveArm (double Engine3)
    {
        {
            if (Motor3 != null)
            {
                Motor3.setPower (Engine3);
            }

        } // set_arm_power
    }
    void reset_right_drive_encoder(){
        if(Motor1 != null){
            Motor1.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }
    void reset_left_drive_encoder(){
        if(Motor2 != null){
            Motor2.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }
    void reset_encoders(){
        reset_left_drive_encoder();
        reset_right_drive_encoder();
    }
    void run_using_right_drive_encoder (){
        if (Motor1 != null){
            Motor1.setMode( DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    }
    void run_using_left_drive_encoder (){
        if (Motor2 != null){
            Motor2.setMode( DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
    }
    void run_using_encoders(){
        run_using_left_drive_encoder();
        run_using_right_drive_encoder();
    }
}
