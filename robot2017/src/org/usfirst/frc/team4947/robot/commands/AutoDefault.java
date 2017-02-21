package org.usfirst.frc.team4947.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDefault extends CommandGroup {

    public AutoDefault() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	//Robot.driveTrain.ptoSolenoid.set(true); // true Disengages PTO.
    	//Robot.driveTrain.speedSolenoid.set(true); // true makes it slow. TODO VALIDATE
 
    	addSequential(new DriveTrainPTODisengage());
    	addSequential(new DriveSlow());
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
