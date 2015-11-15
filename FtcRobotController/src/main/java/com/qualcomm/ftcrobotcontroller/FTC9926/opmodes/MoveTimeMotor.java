package com.qualcomm.ftcrobotcontroller.FTC9926.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Nicolas Bravo on 10/30/15.
 */
public class MoveTimeMotor extends Telemetry9926{

    DcMotor Motor1;
    DcMotor Motor2;

    int move_state = 0;

    @Override
    public void init() {
        Define_Hardware_Config_Names();

    }

    @Override
    public void start() {

    }

    @Override public void loop()
    {


        switch (move_state)
        {
            case 0:
                /* might need to reset motor */
                Define_Hardware_Config_Names();
                move_state++;
                break;

            case 1:
                //m_hand_position(0.2);

                if (getRuntime() > 5){
                    Motor1.setPower(0);
                    Motor2.setPower(0);
                    move_state++;
                }
                break;

            case 2:
                Motor1.setPower(1);
                Motor2.setPower(0);
                if (getRuntime() > 10)
                {
                    Motor1.setPower(0);
                    Motor2.setPower(0);
                    move_state++;
                }
                break;

            case 3:
 //               m_hand_position(.8);
                Motor1.setPower(1);
                Motor2.setPower(1);
                if (getRuntime() > 15){
                    Motor1.setPower(0);
                    Motor2.setPower(0);
                    move_state++;
                }
                break;
            default:
                break;


    /*        case 4:
                Motor1.setPower(-1);
                Motor2.setPower(-1);
                if (getRuntime() > 5){
                    Motor1.setPower(0);
                    Motor2.setPower(0);
                    move_state++;
                }
                break;

            case 5:
                Motor1.setPower(-1);
                Motor2.setPower(0);
                if (getRuntime() > 1.5)
                {
                    Motor1.setPower(0);
                    Motor2.setPower(0);
                    move_state++;
                }
                break;

            case 6:
                Motor1.setPower(-1);
                Motor2.setPower(-1);
                if (getRuntime() > 5){
                    Motor1.setPower(0);
                    Motor2.setPower(0);
                    move_state++;
                }*/

                //break;
        }

        UpdateTelemetry();
        telemetry.addData("11", "State: " + move_state);
        telemetry.addData("12", "Time: " + getRuntime());
    }

    @Override
    public void stop() {

    }
}
