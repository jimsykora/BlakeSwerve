package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.RobotMap;
import org.usfirst.frc.team2839.robot.RobotPreferences;
import org.usfirst.frc.team2839.robot.commands.DriveStart;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Smotor extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private WPI_TalonSRX Smotor = null;  //for Peanut base which uses CAN
	Encoder sEncoder = null;
	AnalogInput pot= null;
	
	public Smotor(){//added this constructor
		Smotor = new WPI_TalonSRX(RobotMap.S_MOTOR);    //for CAN
		//sMotor.enableBrakeMode(true);
		Smotor.setNeutralMode(NeutralMode.Brake);;
		//sMotor.setSafetyEnabled(false);//to allow a motor to run continuously without continuous repeated commands
		Smotor.setSafetyEnabled(false);    //for CAN
		sEncoder = new Encoder(RobotMap.S_ENCODER_CH_A,RobotMap.S_ENCODER_CH_B);
		pot = new AnalogInput(RobotMap.STEER_POT);
	}

	public void setSpeed(double speed){
		Smotor.set(speed);    //for CAN
	}
	public void resetEncoderCount(){
		sEncoder.reset();
	}
	public double getEncoderRate(){//this method returns something so we define it as double, if void it would not return anything
		return sEncoder.getRate();
	}
	public double getEncoderRPS(){//rps is getERate/(100 counts/rev) //getERate is counts/sec
		
		double cpr = 100.0*5.5; //cpr of shooter wheel (encoder counts/rev)*(gear reduction)
		return sEncoder.getRate()/cpr;  //returns rps (rev/sec) of shooter wheel
	}
///the following are used when PID loop used analog encoder (not digital quadrature encoder)
	public void setAngle(double angle){
		Smotor.set(angle);
	}
	public double getPotAngle(){
		return (pot.getAverageVoltage()*72-180);
	}
///
    public void initDefaultCommand() {
        double rps = 0.0;
		// Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveStart(rps));
    }
}

