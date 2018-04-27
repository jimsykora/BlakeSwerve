package org.usfirst.frc.team2839.robot.subsystems;

import org.usfirst.frc.team2839.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Telemetry extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void update() {

		SmartDashboard.putNumber("Mmotor rate", Robot.mMotor.getEncoderRate());
		SmartDashboard.putNumber("Smotor rate", Robot.sMotor.getEncoderRate());

		SmartDashboard.putNumber("Rate delta", (Robot.mMotor.getEncoderRate()+Robot.sMotor.getEncoderRate())/100);
		SmartDashboard.putNumber("Twist", Robot.oi.joystick.getTwist()*180);
		SmartDashboard.putNumber("Throttle", Robot.oi.joystick.getThrottle()*180);
		SmartDashboard.putNumber("Direction", Robot.oi.joystick.getDirectionDegrees());
		SmartDashboard.putNumber("Magnitude", Robot.oi.joystick.getMagnitude());
		SmartDashboard.putNumber("Raw pot angle", Robot.sMotor.getPotAngle());
		SmartDashboard.putNumber("Angle delta", Robot.oi.joystick.getDirectionDegrees()-Robot.sMotor.getPotAngle());
		//SmartDashboard.putNumber("ArcTangent", Math.atan(RobotPreferences.wheelbase()/RobotPreferences.treadwidth())*57.296);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

