package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.commands.GripperDefault;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Gripper extends Subsystem {

    private CANTalon motor = new CANTalon(15); // encoder
    
    public Solenoid openGripperSolenoid = new Solenoid(0);
    public Solenoid upGripperSolenoid = new Solenoid(1);
    private Solenoid extendGripperSolenoid = new Solenoid(4);
    public Solenoid openDoorSolenoid = new Solenoid(5);
    
    private double DartCenteringTolerance = 5 ; //mm. + or -
    private double DartCenterPosOffset = 0 ; 
    public AnalogPotentiometer DartPot = new AnalogPotentiometer(1,350,-230); // This constructor creates an instance of the DART potentiometer that returns values in mm, with 0 approximately centered. the range allowed is 140 mm, thus -70 to 70 mm. Positive to the right, facing same direction as the robot.    
    
    public DigitalInput DartCenter = new DigitalInput(2); // This constructor creates an instance of the DART potentiometer that returns values in mm, with 0 approximately centered. the range allowed is 140 mm, thus -70 to 70 mm. Positive to the right, facing same direction as the robot.    
    
    //public DigitalInput GearPresence = new DigitalInput(2);
    
    // public PowerDistributionPanel power = new PowerDistributionPanel(0);

    
    public Gripper(){
    	// Initialize State
    	//openGripperSolenoid.set(true);
    	//openDoorSolenoid.set(true);
    	
    	motor.setInverted(true);
    }
    
    public void initDefaultCommand() {
        //setDefaultCommand(new GripperDefault());
    }
    
    public double RecursiveFilter(double NewValue, double LastValue)
	{
		double alpha = 0.025 ; // Filter aggressivity. Must be between (0-1) -- 0.001 and 0.999. Highest value filters less. Careful. Aggressivity induces lag in the response.  
		return (LastValue * (1-alpha) + alpha * NewValue);
	}
    
    public boolean IsDartCentered()
    {    	
    	//return DartCenter.get(); // TODO : Valider le fonctionnement 
    	double absDistToCenter = Math.abs(DartCenterPosOffset - DartPot.get());
    	return (absDistToCenter < DartCenteringTolerance);    
    }
    
    public void DartMotorMoveSafe(double speed)
    {
    	// if down, cannot move
    	if( upGripperSolenoid.get() == true)
    	{
    		motor.set(speed);
    	}    	
    }
    public void DartMotorStop()
    {
    	motor.set(0.0);
    }
    
    public void RetractGripperSolenoid()
    {
    	extendGripperSolenoid.set(false); // TODO Validate states. 
    }
    
    public void ExtendGripperSolenoid()
    {
    	if(upGripperSolenoid.get()==false)
    	{ // if down, cannot extend. 
    		extendGripperSolenoid.set(true);
    	}
    }
    
    public void log()
    {
    	SmartDashboard.putNumber("Dart Is At Position", DartPot.get());
    	SmartDashboard.putBoolean("Dart Home Switch", DartCenter.get());
    }
    
}

