package org.usfirst.frc.team4947.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionSystem extends Subsystem {
	
	public double PinPositionVisionLocation = 0;
    public boolean VisionPinPositionSuccess = false; 
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public VisionSystem()
    {
    	
    }
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void log()
    {
    	SmartDashboard.putNumber("Vision Pin Position", PinPositionVisionLocation);
    	SmartDashboard.putBoolean("Vision Worked", VisionPinPositionSuccess)  ;
    }
}

