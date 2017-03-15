package org.usfirst.frc.team4947.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;

import org.usfirst.frc.team4947.robot.commands.DoorClose;
import org.usfirst.frc.team4947.robot.commands.DoorOpen;
import org.usfirst.frc.team4947.robot.commands.DriveArcade;
import org.usfirst.frc.team4947.robot.commands.DriveBackwardRewind;
import org.usfirst.frc.team4947.robot.commands.DriveForward;
import org.usfirst.frc.team4947.robot.commands.DriveNeutral;
import org.usfirst.frc.team4947.robot.commands.DriveRotate;
import org.usfirst.frc.team4947.robot.commands.DriveSlow;
import org.usfirst.frc.team4947.robot.commands.DriveStop;
import org.usfirst.frc.team4947.robot.commands.DriveTrainPTODisengage;
import org.usfirst.frc.team4947.robot.commands.DriveTrainPTOEngage;
import org.usfirst.frc.team4947.robot.commands.GripperClose;
import org.usfirst.frc.team4947.robot.commands.GripperDefault;
import org.usfirst.frc.team4947.robot.commands.GripperDown;
import org.usfirst.frc.team4947.robot.commands.GripperExtend;
import org.usfirst.frc.team4947.robot.commands.GripperMoveManual;
import org.usfirst.frc.team4947.robot.commands.GripperMoveTo;
import org.usfirst.frc.team4947.robot.commands.GripperOpen;
import org.usfirst.frc.team4947.robot.commands.GripperOpenManualToggle;
import org.usfirst.frc.team4947.robot.commands.GripperRetract;
import org.usfirst.frc.team4947.robot.commands.GripperSmartClose;
import org.usfirst.frc.team4947.robot.commands.GripperUp;
import org.usfirst.frc.team4947.robot.commands.GripperUpManualToggle;
import org.usfirst.frc.team4947.robot.commands.IntakeDefault;
import org.usfirst.frc.team4947.robot.commands.IntakeIn;
import org.usfirst.frc.team4947.robot.commands.IntakeOut;
import org.usfirst.frc.team4947.robot.commands.RobotEngageGear;
import org.usfirst.frc.team4947.robot.commands.RobotLift;
import org.usfirst.frc.team4947.robot.commands.RobotMoveGripperDown;
import org.usfirst.frc.team4947.robot.commands.RobotMoveGripperUp;
import org.usfirst.frc.team4947.robot.commands.RobotPickGear;
import org.usfirst.frc.team4947.robot.commands.RobotVisionPlaceDart;
import org.usfirst.frc.team4947.robot.commands.StopAll;
import org.usfirst.frc.team4947.robot.commands.RobotLift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public enum XBoxAxis{
		LeftStickX(0),
		LeftStickY(1),
		LeftTrigger(2),
		RightTrigger(3),
		RightStickX(4),
		RightStickY(5);		

		private int value;
		XBoxAxis(int value){
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum XBoxButton{
		A(1),
		B(2),
		X(3),
		Y(4),
		LB(5),
		RB(6),
		Back(7),
		Start(8),
		LeftStick(9),
		RightStick(10);

		private int value;
		XBoxButton(int value){
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}	
	
    private Joystick joystickDriver = new Joystick(0);
    private Joystick joystickHelper = new Joystick(1);

    @SuppressWarnings("unused")
	public OI() {
        // Create all required button in case we need them
        JoystickButton driverA = new JoystickButton(joystickDriver, XBoxButton.A.getValue());
        JoystickButton driverB = new JoystickButton(joystickDriver, XBoxButton.B.getValue());
        JoystickButton driverX = new JoystickButton(joystickDriver, XBoxButton.X.getValue());
        JoystickButton driverY = new JoystickButton(joystickDriver, XBoxButton.Y.getValue());
        JoystickButton driverLB = new JoystickButton(joystickDriver, XBoxButton.LB.getValue());
        JoystickButton driverRB = new JoystickButton(joystickDriver, XBoxButton.RB.getValue());
        JoystickButton driverBack = new JoystickButton(joystickDriver, XBoxButton.Back.getValue());
        JoystickButton driverStart = new JoystickButton(joystickDriver, XBoxButton.Start.getValue());
        JoystickButton driverLeftStick = new JoystickButton(joystickDriver, XBoxButton.LeftStick.getValue());
        JoystickButton driverRightStick = new JoystickButton(joystickDriver, XBoxButton.RightStick.getValue());
        
        JoystickButton helperA = new JoystickButton(joystickHelper, XBoxButton.A.getValue());
        JoystickButton helperB = new JoystickButton(joystickHelper, XBoxButton.B.getValue());
        JoystickButton helperX = new JoystickButton(joystickHelper, XBoxButton.X.getValue());
        JoystickButton helperY = new JoystickButton(joystickHelper, XBoxButton.Y.getValue());
        JoystickButton helperLB = new JoystickButton(joystickHelper, XBoxButton.LB.getValue());
        JoystickButton helperRB = new JoystickButton(joystickHelper, XBoxButton.RB.getValue());
        JoystickButton helperBack = new JoystickButton(joystickHelper, XBoxButton.Back.getValue());
        JoystickButton helperStart = new JoystickButton(joystickHelper, XBoxButton.Start.getValue());
        JoystickButton helperLeftStick = new JoystickButton(joystickHelper, XBoxButton.LeftStick.getValue());
        JoystickButton helperRightStick = new JoystickButton(joystickHelper, XBoxButton.RightStick.getValue());

        // TODO Link button state to execute commands
     // TODO Link button state to execute commands

        driverA.whileHeld(new IntakeIn());
        driverA.whenPressed(new RobotPickGear());
        driverX.whenPressed(new GripperOpenManualToggle()); // toggle on/off
        
        
        driverLB.whileHeld(new GripperMoveManual());
        driverY.whenPressed(new RobotVisionPlaceDart());
        driverB.whenPressed(new GripperUpManualToggle()); //FAIRE UN TOGGLE
        driverStart.whenPressed(new StopAll());
        driverBack.whenPressed(new RobotLift()); //RB will perform the rewind as pressed. 
        

        SmartDashboard.putData("DoorOpen", new DoorOpen());
        SmartDashboard.putData("DoorClose", new DoorClose());
        
        SmartDashboard.putData("Monte sur Corde", new DriveBackwardRewind());
        
        SmartDashboard.putData("DriveArcade", new DriveArcade());
        SmartDashboard.putData("DriveForward 0.5m", new DriveForward(500,0.8));
        SmartDashboard.putData("DriveRotate +90deg", new DriveRotate(90,0.5));
        SmartDashboard.putData("DriveStop", new DriveStop());
        SmartDashboard.putData("DriveNeutral", new DriveNeutral());
        SmartDashboard.putData("Drive Gearbox Engaged", new DriveSlow());
        SmartDashboard.putData("DriveTrainPTODisengage", new DriveTrainPTODisengage());
        SmartDashboard.putData("DriveTrainPTOEngage", new DriveTrainPTOEngage());
        
        SmartDashboard.putData("GripperClose", new GripperClose());
        SmartDashboard.putData("GripperDefault", new GripperDefault());
        SmartDashboard.putData("GripperDown", new GripperDown());
        SmartDashboard.putData("GripperExtend", new GripperExtend());
        SmartDashboard.putData("GripperMoveManual", new GripperMoveManual());
        SmartDashboard.putData("GripperMoveTo LEFT", new GripperMoveTo(-65,0.5)); 
        SmartDashboard.putData("GripperMoveTo CENTER", new GripperMoveTo(0,0.5));  
        SmartDashboard.putData("GripperMoveTo RIGHT", new GripperMoveTo(65,0.5)); 
        SmartDashboard.putData("GripperOpen", new GripperOpen());
        SmartDashboard.putData("Gripper Open/Close Toggle", new GripperOpenManualToggle());
        SmartDashboard.putData("GripperRetract", new GripperRetract());
        SmartDashboard.putData("Gripper Smart Close ", new GripperSmartClose());
        SmartDashboard.putData("GripperUp", new GripperUp());
        SmartDashboard.putData("Gripper Up/Down Toggle", new GripperUpManualToggle());
        
        SmartDashboard.putData("IntakeDefault", new IntakeDefault());
        SmartDashboard.putData("IntakeIn", new IntakeIn());
        SmartDashboard.putData("IntakeOut", new IntakeOut());        

        SmartDashboard.putData("Engage Gear on Peg Macro", new RobotEngageGear());
        SmartDashboard.putData("RobotLift", new RobotLift());
        SmartDashboard.putData("Gripper Down Macro", new RobotMoveGripperDown());
        SmartDashboard.putData("Gripper Down Macro", new RobotMoveGripperUp());
        SmartDashboard.putData("Pick gear Macro", new RobotPickGear());
        SmartDashboard.putData("Robot Vision position Gripper", new RobotVisionPlaceDart());        
    }
    
    public double getJoystickDriverAxis(XBoxAxis axis) {
        return joystickDriver.getRawAxis(axis.getValue());
    }

    public double getJoystickDriverAxis(XBoxAxis axis, double deadBand) {
    	double axisValue = joystickDriver.getRawAxis(axis.getValue());
    	
    	if(Math.abs(axisValue) <= deadBand){
    		axisValue = 0;
    	}
    	
    	return axisValue;
    }
    
    public void setJoystickDriverRumble(RumbleType rumbleType, float value){
    	joystickDriver.setRumble(rumbleType, value);
    }
    
    public boolean getJoystickDriverButton(XBoxButton button) {
        return joystickDriver.getRawButton(button.getValue());
    }
    
    public double getJoystickHelperAxis(XBoxAxis axis) {
        return joystickHelper.getRawAxis(axis.getValue());
    }

    public double getJoystickHelperAxis(XBoxAxis axis, double deadBand) {
    	double axisValue = joystickHelper.getRawAxis(axis.getValue());
    	
    	if(Math.abs(axisValue) <= deadBand){
    		axisValue = 0;
    	}
    	
    	return axisValue;
    }
    
    public boolean getJoystickHelperButton(XBoxButton button) {
        return joystickHelper.getRawButton(button.getValue());
    }
}

