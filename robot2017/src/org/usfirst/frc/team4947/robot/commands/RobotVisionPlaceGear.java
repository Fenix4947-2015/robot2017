package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RobotVisionPlaceGear extends CommandGroup {

    public RobotVisionPlaceGear() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

    	addSequential(new DoorOpen());
    	addSequential(new CalculatePinLocation());
    	addSequential(new GripperMoveTo(Robot.visionSystem.PinPositionVisionLocation,0.8));
    	addSequential(new GripperExtend()); 
    	addSequential(new GripperOpen());
    	addSequential(new RobotMoveGripperDown());
    	
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
