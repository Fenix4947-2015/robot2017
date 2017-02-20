package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.commands.DriveArcade;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	public CANTalon leftMotor1 = new CANTalon(11); // encoder
	public CANTalon leftMotor2 = new CANTalon(13);
	public CANTalon leftMotor3 = new CANTalon(17);
	public CANTalon rightMotor1 = new CANTalon(12); // encoder
	public CANTalon rightMotor2 = new CANTalon(14);
	public CANTalon rightMotor3 = new CANTalon(18);
		
	public Solenoid speedSolenoid = new Solenoid(7);
	public Solenoid ptoSolenoidLeft = new Solenoid(6);
	public Solenoid ptoSolenoidRight = new Solenoid(2);
	
	public AnalogGyro gyro = new AnalogGyro(1);
	
	public Counter encoderLeft = new Counter(0);
	public Counter encoderRight = new Counter(1);
	
	public RobotDrive robotDrive = new RobotDrive(leftMotor1, rightMotor1);
	public RobotDrive robotDrive2 = new RobotDrive(leftMotor2, rightMotor2);
	public RobotDrive robotDrive3 = new RobotDrive(leftMotor3, rightMotor3);

	public DriveTrain()
	{
		
		robotDrive.setSafetyEnabled(false);
		
		leftMotor1.setInverted(false);
		leftMotor2.setInverted(false);
		leftMotor3.setInverted(true);
		
		rightMotor1.setInverted(false);
		rightMotor2.setInverted(false);
		rightMotor3.setInverted(true);
		
		//leftMotor1
		//leftMotor2.setControlMode(TalonControlMode.Follower.value);
		//leftMotor2.set(leftMotor1.getDeviceID());
		//leftMotor3.setControlMode(TalonControlMode.Follower.value);
		//leftMotor3.set(leftMotor1.getDeviceID());
		
		//rightMotor2.setControlMode(TalonControlMode.Follower.value);
		//rightMotor2.set(rightMotor1.getDeviceID());
		//rightMotor3.setControlMode(TalonControlMode.Follower.value);
		//rightMotor3.set(rightMotor1.getDeviceID());
		
		encoderLeft.setDistancePerPulse(10);
		encoderRight.setDistancePerPulse(10);
		
		encoderLeft.reset();
		encoderRight.reset();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveArcade());
    }
}

