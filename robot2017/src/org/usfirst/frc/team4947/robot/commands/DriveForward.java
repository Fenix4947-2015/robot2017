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
	private final double TOLERANCE = .1;
	private final double KP = -1.0 / 5.0;

	public DriveForward(double dist) {
		this(dist, 0.5);
	}

	public DriveForward(double distance, double speed) {
		requires(Robot.driveTrain);
		
		this.distance = distance;
		this.speed = speed;
	}

	protected void initialize() {
		//Robot.driveTrain.setPosition(0);
	}

	protected void execute() {
	//	error = (distance - Robot.mo());
		
		if (speed * KP * error >= speed) {
			//Robot.driveTrain.tankDrive(speed, speed);
		} 
		else {
			//Robot.driveTrain.tankDrive(speed * KP * error, speed * KP * error);
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
