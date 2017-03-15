package org.usfirst.frc.team4947.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RobotMoveGripperDown extends CommandGroup {

    public RobotMoveGripperDown() {
    	addSequential(new GripperRetract());
    	addSequential(new DoorOpen());
    	
    	addSequential(new GripperClose());
    	addSequential(new GripperMoveTo(0, 0.6)); // Go center
    	
    	addSequential(new GripperDown());
    	
    	addSequential(new DoorClose());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
