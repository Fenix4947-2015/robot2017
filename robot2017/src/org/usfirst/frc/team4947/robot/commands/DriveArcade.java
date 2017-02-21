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
    protected void initialize() {
    	Robot.driveTrain.ptoSolenoid.set(true); // true Disengages PTO.
    	Robot.driveTrain.speedSolenoid.set(true); // true makes it slow. TODO VALIDATE
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double moveValue = Robot.oi.getJoystickDriverAxis(XBoxAxis.LeftTrigger) - Robot.oi.getJoystickDriverAxis(XBoxAxis.RightTrigger);
    	double rotateValue = Robot.oi.getJoystickDriverAxis(XBoxAxis.LeftStickX, 0.1);
    	
    	Robot.driveTrain.robotDrive.arcadeDrive(moveValue, rotateValue);
    	double distLeft = Robot.driveTrain.encoderLeft.get();//.getDistance();
    	double distRight = Robot.driveTrain.encoderRight.get();//.getDistance();
    	
    	Robot.driveTrain.robotDrive2.arcadeDrive(moveValue, rotateValue);
    	Robot.driveTrain.robotDrive3.arcadeDrive(moveValue, rotateValue);
    	
    	SmartDashboard.putNumber("encoderLeft", distLeft);
    	SmartDashboard.putNumber("encoderRight", distRight);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.robotDrive.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
