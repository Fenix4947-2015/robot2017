package org.usfirst.frc.team4947.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearMiddle extends CommandGroup {

    public AutoGearMiddle() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

    	addSequential(new AutoDefault(),5);// Initialize the robot. max 5 sec. 
    	addSequential(new DriveForward(2100,0.9)); //2430 mm from wall to turret (7pi 10 po) //TODO : Validate trajectory - robot length.
    	addSequential(new CalculatePinLocation());
    	addSequential(new RobotEngageGear());
    	
    	/*addSequential(new DriveForward(-500,0.9)); 
    	addSequential(new DriveRotate(-90,0.9));  // TODO : VALIDATE TRAJECTORY
    	addSequential(new DriveForward(2000,0.9)); 
    	addSequential(new DriveRotate(90,0.9));  // TODO : VALIDATE TRAJECTORY
    	addSequential(new DriveForward(1000,0.9)); */
    	
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
