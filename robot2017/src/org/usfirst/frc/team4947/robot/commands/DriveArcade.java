package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.OI.XBoxAxis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveArcade extends Command {

    public DriveArcade() {
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double moveValue = -Robot.oi.getJoystickDriverAxis(XBoxAxis.LeftTrigger) + Robot.oi.getJoystickDriverAxis(XBoxAxis.RightTrigger);
    	//double rotateValue = -Robot.oi.getJoystickDriverAxis(XBoxAxis.LeftStickX, 0.1)*0.65-moveValue*0.86;
    	double rotateValue = - Robot.oi.getJoystickDriverAxis(XBoxAxis.LeftStickX, 0.1)*0.65;
    	
    	//Robot.driveTrain.DriveArcadeSafe(SmoothRamp(moveValue,0.1), rotateValue);
    	Robot.driveTrain.DriveArcadeSafe(moveValue, rotateValue); // TODO Test smooth Ramp
    	
    	SmartDashboard.putNumber("ForwardSpeed", moveValue);
    	SmartDashboard.putNumber("RotationSpeed", rotateValue);
    	
    	int distLeft = Robot.driveTrain.encoderLeft.get();//.getDistance();
    	int distRight = Robot.driveTrain.encoderRight.get();//.getDistance();    
    	//int encl = Robot.driveTrain.encLeft.get();
    	SmartDashboard.putNumber("encoderLeft", distLeft);
    	SmartDashboard.putNumber("encoderRight", distRight);
    	SmartDashboard.putNumber("GyroArcade", Robot.driveTrain.gyro.getAngle());
    	//SmartDashboard.putNumber("encl", encl);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.DriveArcadeSafe(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    public double SmoothRamp(double input, double deadband)
    {
    	int order = 2;
    	double motorStartVoltage = 0.1;
    	double a = (1-motorStartVoltage)/Math.pow(1-deadband, 2);
    	if(input>deadband)
    	{
    		return Math.signum(input) * (a * Math.pow(input - deadband, 2) + motorStartVoltage);
    	}
    	else if (-deadband>input)
    	{
        	return Math.signum(input) * (a * Math.pow(Math.abs(input) - deadband, 2) + motorStartVoltage);
    	}
    	else
    	{
        	return 0.0; // deadband
    	}
    }
}
