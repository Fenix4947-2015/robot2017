package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GripperMoveHome extends Command {
	private double DesiredPosition;
	private double speed = 0.75;
	private int tolerance = 2;
	private double lastPotAngle=0;
	
    public GripperMoveHome() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lastPotAngle = Robot.gripper.DartPot.get();
    	DesiredPosition = 0.0; 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	lastPotAngle = Robot.gripper.RecursiveFilter(Robot.gripper.DartPot.get(), lastPotAngle);
    	double currentPosition = lastPotAngle;    	
    	
    	SmartDashboard.putNumber("Dart Is At Position", currentPosition);
    	SmartDashboard.putBoolean("Dart Is Centered", Robot.gripper.IsDartCentered());
    	
    	if(currentPosition < DesiredPosition){
    		Robot.gripper.DartMotorMoveSafe(speed);
    	}
    	else if (currentPosition > DesiredPosition){
    		Robot.gripper.DartMotorMoveSafe(-speed);
    	}		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.gripper.IsDartCentered();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gripper.DartMotorStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
