package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;

import javax.swing.plaf.SliderUI;

import org.usfirst.frc.team4947.robot.OI.XBoxAxis;
import org.usfirst.frc.team4947.robot.OI.XBoxButton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveBackwardRewind extends Command {

	public double RewindSpeedParameter = 0.6;
	
	// TODO : Valider le threshold
	double powerLiftThreshold = 130/2.2 * 9.8 * 0.5 * RewindSpeedParameter * 1.5 * 8.2 * 0.0254 * 5; // (lbs * 2.2 (to kg) * 9.8 (to N) * liftSpeed(pi/s) * rotate speed * 1.5 tour/s a 100% * 8po/tour * 0.0254 intom * (to m/s) * FS sur la puissance nominale 
	
    public DriveBackwardRewind() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double nowPower = Robot.driveTrain.powerDistPanel.getTotalPower();
    	SmartDashboard.putDouble("Lift Now Power", nowPower);
    	if( Robot.oi.getJoystickDriverButton(XBoxButton.RB))
    	{
    		if(nowPower < powerLiftThreshold)
    		{
    			Robot.driveTrain.DriveArcadeSafe(- RewindSpeedParameter , 0);
    		}
    		else
    		{
			  	// Wait before pilot can retry. 
				Robot.driveTrain.DriveArcadeSafe(0 , 0);
    			Timer.delay(2);
    		}
    			
    	}
    	else
    	{
    		Robot.driveTrain.DriveArcadeSafe(0, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
