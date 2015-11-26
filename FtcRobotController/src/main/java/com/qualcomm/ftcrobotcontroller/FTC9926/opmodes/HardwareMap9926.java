package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import java.lang.String;

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
    DcMotor Motor1;
    DcMotor Motor2;
    DcMotor Motor3;
    double SM1_Position;
    double Get_Servo_Position;

    @Override
    public void init(){

        // Define Config Name in Driver Station
        double l_hand_position = 0.5;

        Servo1 = hardwareMap.servo.get("SM1");
        Servo1.setPosition(SM1_Position);
        SM1_Position = 0.5;
        Define_Hardware_Config_Names();
        Motor1 = hardwareMap.dcMotor.get("M1");
        Motor2 = hardwareMap.dcMotor.get("M2");
        Motor3 = hardwareMap.dcMotor.get("M3");
        Motor3.setDirection(DcMotor.Direction.REVERSE);
        Motor2.setDirection(DcMotor.Direction.REVERSE);

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
    void Move (double Motor1power, double Motor2power){
        if (Motor1 != null)
        {
            Motor1.setPower (Motor1power);
        }
        if (Motor2 != null)
        {
            Motor2.setPower (Motor2power);
        }
    }
    void Turn (double Turn){
        /* Left motor is Motor2, right one is Motor1
        *  If you put a negative input, then the robot will turn left
        *  If you put a positive input, then the robot will turn right */
        if (Motor1 != null)
        {
            Motor1.setPower (-1 * Turn);
        }
        if (Motor2 != null)
        {
            Motor2.setPower (Turn);
        }
    }
    void Arm (double ArmMotorPower, double ArmServoPower){
        if (Motor3 != null)
        {
            Motor3.setPower (ArmMotorPower);
        }
        if (Servo1 != null)
        {
            SM1_Position = ArmServoPower;
            Set_Servo_position(SM1_Position);
        }
    }
    void Stop() {
        Motor1.setPower(0);
        Motor2.setPower(0);
        Motor3.setPower(0);
    }
}
