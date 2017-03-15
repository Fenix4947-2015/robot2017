package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives the robot over a given distance with simple proportional
 * control This command will drive a given distance limiting to a maximum speed.
 */
//LE CODE EST POUR PILOTER SEULEMENT !!!!!!!!
public class DriveForward extends Command {
	private double speed;
	private double distance;
	private double error;
	private final double TOLERANCE = 100; //mm
	private final double KP = -1.0;
	private double initPos = 0;
	
	public DriveForward(double dist) {
		this(dist, 0.5);
	}
// tout en mm
	public DriveForward(double distance, double speed) {
		requires(Robot.driveTrain);
		
		this.distance = distance;
		this.speed = speed;
	}

	protected void initialize() {
		initPos = Robot.driveTrain.encoderLeft.getDistance();
	}

	protected void execute() {
		error = (distance - (Robot.driveTrain.encoderLeft.getDistance()-initPos) );
		// error is positive if need to go forward, 
		if (error >= 0) {
			Robot.driveTrain.DriveArcadeSafe(speed, 0);
		} 
		else if (0 >= error) {
			Robot.driveTrain.DriveArcadeSafe(-speed, 0);
		} 		
	}

	protected boolean isFinished() {
		return (Math.abs(error) <= TOLERANCE);
	}

	protected void end() {
		//Robot.driveTrain.tankDrive(0, 0);
	}

	protected void interrupted() {
		end();
	}
}
