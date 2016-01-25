package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import java.util.Set;

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
    Servo Servo2;
    Servo Servo3;
    double SM1_Position;
    double SM2_Position;
    double SM3_Position;
    double Get_Servo_Position;
    DcMotor Motor1;
    DcMotor Motor2;
    DcMotor Motor3;
    DcMotor Motor4;
    DcMotor Motor5;
    DcMotor Motor6;


    @Override
    public void init(){

        // Define Config Name in Driver Station
        double l_hand_position = 0;

        Servo1 = hardwareMap.servo.get("SM1");

        Servo1.setPosition(SM1_Position);


        
        
        SM1_Position = 0.5;

        Servo2 = hardwareMap.servo.get("SM2");
        Servo2.setPosition(SM2_Position);
        SM2_Position = 0.5;


        SM1_Position = 0;
        Servo2 = hardwareMap.servo.get("SM2");
        Servo2.setPosition(SM2_Position);
        SM2_Position = 0;


        SM1_Position = 1;
        Servo2 = hardwareMap.servo.get("SM2");
        Servo2.setPosition(SM2_Position);
        SM2_Position = 1;


        SM1_Position = 0;
        Servo2 = hardwareMap.servo.get("SM2");
        Servo2.setPosition(SM2_Position);
        SM2_Position = 0;


        SM1_Position = 1;
        Servo2 = hardwareMap.servo.get("SM2");
        Servo2.setPosition(SM2_Position);
        SM2_Position = 1;



        
        Servo3 = hardwareMap.servo.get("SM3");

        Motor1 = hardwareMap.dcMotor.get("M1");
        Motor2 = hardwareMap.dcMotor.get("M2");
        Motor3 = hardwareMap.dcMotor.get("M3");

        Motor4 = hardwareMap.dcMotor.get("M4");

        Motor5 = hardwareMap.dcMotor.get("M5");
        Motor6 = hardwareMap.dcMotor.get("M6");

        Define_Hardware_Config_Names();
        Motor1.setDirection(DcMotor.Direction.FORWARD);
        Motor2.setDirection(DcMotor.Direction.FORWARD);

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
      //  SM1_Position = 0.2;
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
    void Set_Servo2_position (double position2) {
        SM2_Position = Range.clip(position2,0,1);
        Servo2.setPosition(SM2_Position);
    }

<<<<<<< 5cb4a443ac0638b253866209cfe9040d04c0e1e6


    void Move (double Motor1power, double Motor2power){
    void MoveMotor (double Motor1power, double Motor2power){

        Motor1.setPower(Motor1power);
        Motor2.setPower(-1 * Motor2power);
    void MoveMotor (double Motor1power, double Motor2power)
    {
        if (Motor1 != null)
        {
            Motor1.setPower (Motor1power);
        }
        // set_drive_power
        if (Motor2 != null)
        {
            Motor2.setPower (Motor2power);
        }
    }
    void Turn (double Turn){
        {
            if (Motor1 != null)
            {
                Motor1.setPower (-1 * Turn);
            }

        } // set_drive_power
        if (Motor2 != null)
        {
            Motor2.setPower (Turn);
        }
        /* Left motor is Motor2, right one is Motor1
        *  If you put a negative input, then the robot will turn left
        *  If you put a positive input, then the robot will turn right */
    }
    void MoveArm (double ArmMotorPower, double ArmServoPower){
        
        Motor3.setPower(ArmMotorPower);
        SM1_Position = ArmServoPower;
        Set_Servo_position(SM1_Position);
    }
    void StopMotor() {
        {
            if (Motor1 != null)
            {
                Motor1.setPower (0);
            }

        } // set_drive_power
        if (Motor2 != null)
        {
            Motor2.setPower (0);
        }
        if (Motor3 != null)
        {
            Motor3.setPower(0);
        }
    }



=======
    void Set_Flap_position (double flap) {
        if (flap == 1) {
            SM1_Position = .25;
            Servo1.setPosition(SM1_Position);
        }
        else if (flap == 3) {
            SM3_Position = -.25;
            Servo3.setPosition(SM3_Position);
        }

    }

>>>>>>> Added Set_Flap_position and set it to the dpad left & right
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




    void MoveDrawer (double Engine4)


    void MoveAim (double Engine6)
    {
        {
            if (Motor6 != null)
            {
                Motor6.setPower (Engine6);
            }
        }
    }
    void MovePull (double Engine4)

    {
        {
            if (Motor4 != null)
            {
                Motor4.setPower (Engine4);
            }
        }
    }


    void MovePull (double Engine5)


    void MoveDrawer (double Engine5)

    {
        {
            if (Motor5 != null)
            {
                Motor5.setPower (Engine5);
            }
        }
    }

    void Turn (double Turn){
        {
            if (Motor1 != null)
            {
                Motor1.setPower (-1 * Turn);
            }

        } // set_drive_power
        if (Motor2 != null)
        {
            Motor2.setPower (Turn);
        }
        /* Left motor is Motor2, right one is Motor1
        *  If you put a negative input, then the robot will turn left
        *  If you put a positive input, then the robot will turn right */
    }
    void StopMotor() {
        {
            if (Motor1 != null)
            {
                Motor1.setPower (0);
            }

        } // set_drive_power
        if (Motor2 != null)
        {
            Motor2.setPower (0);
        }
        if (Motor3 != null)
        {
            Motor3.setPower(0);
        }
    }

    }
