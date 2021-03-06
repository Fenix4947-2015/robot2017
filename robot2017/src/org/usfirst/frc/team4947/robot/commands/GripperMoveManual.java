package org.usfirst.frc.team4947.robot.commands;

import javax.swing.text.Position;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.OI.XBoxAxis;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GripperMoveManual extends Command {

    public GripperMoveManual() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// Laterally control the left right axis with motor.
    	double lateralDeadband = 0.5;
    	double rotateValue = Robot.oi.getJoystickDriverAxis(XBoxAxis.RightStickX, lateralDeadband);
    	Robot.gripper.DartMotorMoveSafe(rotateValue*0.5);
    	
    	// Control the extension and retraction of the gripper with the pneumatics. 
    	double extensionDeadBand = 0.8;
    	double extendValue = Robot.oi.getJoystickDriverAxis(XBoxAxis.RightStickY, extensionDeadBand);
    	if(extendValue>extensionDeadBand)
    	{
    		Robot.gripper.ExtendGripperSolenoid();
    	}
    	else if (-extensionDeadBand > extendValue)
    	{
    		Robot.gripper.RetractGripperSolenoid();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
