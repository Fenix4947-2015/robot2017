package org.usfirst.frc.team4947.robot.commands;

import javax.swing.text.Position;

import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GripperMoveTo extends Command {

	private int position;
	private double speed;
	private int tolerance = 5;
    public GripperMoveTo(int Position,double Speed) {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	position = Position;
    	speed = Speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int currentPosition = Robot.gripper.motor.getPulseWidthPosition();
    	Robot.gripper.motor.set(speed);
    	if(currentPosition < position){
    		Robot.gripper.motor.set(speed);
    	}
    	else if (currentPosition > position){
    		Robot.gripper.motor.set(-speed);
    	}		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs( Robot.gripper.motor.getPulseWidthPosition ()  - position )< tolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gripper.motor.set(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
