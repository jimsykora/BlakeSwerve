package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.RobotMap;
import org.usfirst.frc.team2839.robot.commands.DriveStart;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Mmotor extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon Mmotor = null;  //for Peanut base which uses CAN
	Encoder mEncoder = null;
	
	public Mmotor(){//added this constructor
		//mMotor = new VictorSP(RobotMap.M_MOTOR);//must create a constant in RobotMap
		Mmotor = new CANTalon(RobotMap.M_MOTOR);    //for CAN
		//mMotor.enableBrakeMode(true);
		Mmotor.enableBrakeMode(true);    //for CAN
		//mMotor.setSafetyEnabled(false);//to allow a motor to run continuously without continuous repeated commands
		Mmotor.setSafetyEnabled(false);//to allow a motor to run continuously without continuous repeated commands
		mEncoder = new Encoder(RobotMap.M_ENCODER_CH_A,RobotMap.M_ENCODER_CH_B);
	}

	public void setSpeed(double speed){
		Mmotor.set(speed);    //for CAN
	}
	public void resetEncoderCount(){
		mEncoder.reset();
	}
	public double getEncoderRate(){//this method returns something so we define it as double, if void it would not return anything
		return mEncoder.getRate();
	}
	public double getEncoderRPS(){//rps is getERate/(100 counts/rev) //getERate is counts/sec
		
		double cpr = 100.0*5.5; //cpr of shooter wheel (encoder counts/rev)*(gear reduction)
		return mEncoder.getRate()/cpr;  //returns rps (rev/sec) of shooter wheel
	}
    public void initDefaultCommand() {
        double rps = 0.0;
		// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveStart(rps));
    }
}

