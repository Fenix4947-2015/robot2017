package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.commands.DriveArcade;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	public CANTalon leftMotor1 = new CANTalon(11); // encoder
	public CANTalon leftMotor2 = new CANTalon(13);
	public CANTalon leftMotor3 = new CANTalon(17);
	public CANTalon rightMotor1 = new CANTalon(12); // encoder
	public CANTalon rightMotor2 = new CANTalon(14);
	public CANTalon rightMotor3 = new CANTalon(18);
		
	public Solenoid speedSolenoid = new Solenoid(7);
	public Solenoid ptoSolenoid = new Solenoid(6);
	//public Solenoid ptoSolenoidRight = new Solenoid(2);
	
	private static final double kVoltsPerDegreePerSecond = 0.0128/2;
	public AnalogGyro gyro = new AnalogGyro(0);
	
	public Counter encoderLeft;// = new Counter();//(0);//0);
	public Counter encoderRight;// = new Counter();//(1);//1);
	//public Encoder encLeft = new Encoder(0, 0);
	//public Encoder encRight = new Encoder(1, 1);
	
	private RobotDrive robotDrive = new RobotDrive(leftMotor1, rightMotor1);
	private RobotDrive robotDrive2 = new RobotDrive(leftMotor2, rightMotor2);
	private RobotDrive robotDrive3 = new RobotDrive(leftMotor3, rightMotor3);

	public PowerDistributionPanel powerDistPanel = new PowerDistributionPanel(1);
	
	public DriveTrain()
	{
		gyro.setSensitivity(kVoltsPerDegreePerSecond);
		
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
		
		encoderLeft= new Counter();
		encoderLeft.setUpSource(0); // Mapper sur les DIO
		encoderLeft.setUpDownCounterMode();
		encoderLeft.setMaxPeriod(0.1);
		encoderLeft.setUpdateWhenEmpty(true);
		encoderLeft.setReverseDirection(false);
		encoderLeft.setSamplesToAverage(3);
		encoderLeft.setDistancePerPulse(59); // mm with 8 pulse encoder. 
		//encoderLeft.start();
		//encoderLeft.startLiveWindowMode();// ???
		
		
	//	encLeft.setDistancePerPulse(12);
		encoderRight= new Counter();
		encoderRight.setUpSource(1);
		encoderRight.setUpDownCounterMode();
		encoderRight.setMaxPeriod(0.1);
		encoderRight.setUpdateWhenEmpty(true);
		encoderRight.setReverseDirection(false);
		encoderRight.setSamplesToAverage(2);
		encoderRight.setDistancePerPulse(235.62); // mm with 2 pulse encoder. 
		encoderRight.startLiveWindowMode();// to add ???
		
		//encoderLeft.reset();
		//encoderRight.reset();
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new DriveArcade());
    }
    

    public void DriveArcadeSafe(double Speed, double Rotation) {
    	//ptoSolenoid.set(true);
    	if(ptoSolenoid.get()==true)//pto disengaged
    	{
	    	robotDrive.arcadeDrive(Speed, Rotation);   	
	    	robotDrive2.arcadeDrive(Speed, Rotation);
	    	robotDrive3.arcadeDrive(Speed, Rotation);
    	}
    	else // limitation : cannot move forward or rotate right.
    	{
    		if (Speed>0)
    		{
    			Speed = 0;    				
    		}
    		if (Rotation>0)
    		{
    			Rotation = 0;    				
    		}
    		robotDrive.arcadeDrive(Speed, Rotation);   	
	    	robotDrive2.arcadeDrive(Speed, Rotation);
	    	robotDrive3.arcadeDrive(Speed, Rotation);
    	}
    }
}

