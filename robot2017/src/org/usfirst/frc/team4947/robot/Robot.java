
package org.usfirst.frc.team4947.robot;

import org.usfirst.frc.team4947.robot.commands.AutoDefault;
import org.usfirst.frc.team4947.robot.commands.AutoForward;
import org.usfirst.frc.team4947.robot.commands.AutoGearLeft;
import org.usfirst.frc.team4947.robot.commands.AutoGearMiddle;
import org.usfirst.frc.team4947.robot.commands.AutoGearRight;
import org.usfirst.frc.team4947.robot.commands.DriveTrainPTODisengage;
//import org.usfirst.frc.team4947.robot.subsystems.Camera;
//import org.usfirst.frc.team4947.robot.subsystems.Camera;
import org.usfirst.frc.team4947.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4947.robot.subsystems.Gripper;
import org.usfirst.frc.team4947.robot.subsystems.Intake;
import org.usfirst.frc.team4947.robot.subsystems.VisionSystem;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static DriveTrain driveTrain;
	public static Gripper gripper;
	public static Intake intake;
	public static VisionSystem visionSystem;

    //public static Camera camera1;
   // public static Camera camera2;
   // public static CameraServer camera1;
	
	public static OI oi;

	Command autonomousCommand;
	 SendableChooser autonomousChooser = new SendableChooser();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		
		driveTrain = new DriveTrain();
		gripper = new Gripper();
		intake = new Intake();

		CameraServer.getInstance().startAutomaticCapture();
		CameraServer.getInstance().startAutomaticCapture();
		oi = new OI();
		// chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		// SmartDashboard.putData("Auto mode", chooser);
		
		autonomousChooser.addDefault("1 - Auto Default", new AutoDefault());
		autonomousChooser.addDefault("2 - Auto Forward", new AutoForward());
		autonomousChooser.addDefault("3 - Auto Gear Left", new AutoGearLeft());
		autonomousChooser.addDefault("4 - Auto Gear Middle", new AutoGearMiddle());
		autonomousChooser.addDefault("5 - Auto Gear Right", new AutoGearRight());
		//autonomousCommand = new AutoDefault();
		SmartDashboard.putData("AutoMode", autonomousChooser);
	
        SmartDashboard.putData(driveTrain);
        SmartDashboard.putData(intake);
        SmartDashboard.putData(gripper);
        SmartDashboard.putData(visionSystem);       
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = (Command)autonomousChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
		{
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		log();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		log();
	}
	

	private void log() 
	{
		driveTrain.log();
		intake.log();
		gripper.log();		
		visionSystem.log();
	}

}
