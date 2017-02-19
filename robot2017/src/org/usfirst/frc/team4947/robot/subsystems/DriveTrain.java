package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.commands.DriveArcade;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	public CANTalon leftMotor1 = new CANTalon(1); // encoder
	public CANTalon leftMotor2 = new CANTalon(2);
	public CANTalon leftMotor3 = new CANTalon(3);
	public CANTalon rightMotor1 = new CANTalon(4); // encoder
	public CANTalon rightMotor2 = new CANTalon(5);
	public CANTalon rightMotor3 = new CANTalon(6);
		
	public Solenoid speedSolenoid = new Solenoid(1);
	public Solenoid ptoSolenoid = new Solenoid(2);
	
	public AnalogGyro gyro = new AnalogGyro(1);
	
	public RobotDrive robotDrive = new RobotDrive(leftMotor1, rightMotor1);

	public DriveTrain()
	{
		leftMotor2.setControlMode(TalonControlMode.Follower.value);
		leftMotor2.set(leftMotor1.getDeviceID());
		leftMotor3.setControlMode(TalonControlMode.Follower.value);
		leftMotor3.set(leftMotor1.getDeviceID());
		
		rightMotor2.setControlMode(TalonControlMode.Follower.value);
		rightMotor2.set(rightMotor1.getDeviceID());
		rightMotor3.setControlMode(TalonControlMode.Follower.value);
		rightMotor3.set(rightMotor1.getDeviceID());
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveArcade());
    }
}

