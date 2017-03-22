package org.usfirst.frc.team4947.robot.commands;

import javax.swing.text.Position;

import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GripperMoveTo extends Command {

	private double position;
	private double speed;
	private int tolerance = 3;
	private double lastPotAngle=0;
    public GripperMoveTo(double Position,double Speed) {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	position = Position; // value in mm
    	speed = Speed; // value in percent. dart stall speed around 20% 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lastPotAngle = Robot.gripper.DartPot.get();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	lastPotAngle = Robot.gripper.RecursiveFilter(Robot.gripper.DartPot.get(), lastPotAngle);
    	double currentPosition = lastPotAngle;
    	
    	if(currentPosition < position){
    		Robot.gripper.DartMotorMoveSafe(speed);
    	}
    	else if (currentPosition > position){
    		Robot.gripper.DartMotorMoveSafe(-speed);
    	}		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs( lastPotAngle - position )< tolerance;
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
