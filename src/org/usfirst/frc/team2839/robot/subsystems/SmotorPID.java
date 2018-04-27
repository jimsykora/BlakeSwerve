package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.Robot;
import org.usfirst.frc.team2839.robot.RobotPreferences;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class SmotorPID extends PIDSubsystem {
	double output = 0.0;
	boolean outputValid = false;
//	int targetRate = 0;  //for speed PID loop, remove later if/when PID loop gets tuned properly. its used to delay turning off PID loop while in motion
	double targetAngle = 2.5;  //for position PID loop, remove later if/when PID loop gets tuned properly. its used to delay turning off PID loop while in motion
	double tolerance = 0.0;
	
    // Initialize your subsystem here
    public SmotorPID() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("SmotorPID",0,0,0,0);  //for speed PID loop
    	//super("RRsteerPID",0,0,0);  //for position PID loop
    	this.setSetpoint(0.0);
    	getPIDController().setContinuous(true); //allows PID loop to handle abrupt change from 5 to 0 volts as encoder turns
    }

    public void enable()  {
    	//this.getPIDController().setPID(RobotPreferences.driveP(), RobotPreferences.driveI(), RobotPreferences.driveD(), RobotPreferences.driveF());  //for speed PID loop
    	this.getPIDController().setPID(RobotPreferences.steerP(), RobotPreferences.steerI(), RobotPreferences.steerD());
    	//this.getPIDController().setPID(0.05,  0.0,  0.005,  0.0);
    	double maxSpeed = RobotPreferences.steerMaxSpeed(); //set to <1.0 to limit max motor speed
    	this.setOutputRange(-maxSpeed, maxSpeed);
    	this.setInputRange(-180.0, 180.0);  //for position PID loop
    	outputValid = false;
    	super.enable();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	//return Robot.sMotor.getEncoderRPS(); //for speed PID loop
    	return Robot.sMotor.getPotAngle();  //for position PID loop 
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	this.output = output;
    	outputValid = true;
    }
    public double getOutput() {
    	if(this.getPIDController().isEnabled() == false || outputValid == false) { // == meams "is equal to", || means "or"
    		return 0.0;
    	}
    	//output = output + 0*Robot.mMotor.getEncoderRate()/RobotPreferences.steer2SpeedDivisor(); //to keep motors spinning at same rate after steer angle error is zero
    	return output + Robot.mMotor.getEncoderRate()/RobotPreferences.steer2SpeedDivisor();
    	
    	//return output+Robot.oi.joystick.getThrottle();  //simulated analog encoder input to summing junction for Blake's swerve
    	//return output+(Robot.sMotor.pot.getAverageVoltage()/2.5-1)/5;  //simulated analog encoder input to summing junction for Blake's swerve
    }
    public void setRawTolerance(double tolerance) {
    	this.tolerance = tolerance;
    }
   /* public boolean onRawTargrt() {
    	if(Math.abs(getPIDController().getSetpoint() - Robot.rrDrive.getEncoderRPS()) < tolerance) {
    		targetRate = targetRate +1;
    	}
    	else {
    		targetRate = 0;
    	}
    	return (targetRate >= RobotPreferences.driveTargetRate());
    }*/
}
