package org.usfirst.frc.team4947.robot.commands;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RobotLift extends CommandGroup {

    public RobotLift() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	if(Timer.getMatchTime() > 120) // Rope is down at 2 minutes count.    		
    	{
        	addSequential(new DriveStop());
        	addSequential(new Pause(0.1));
        	
        	// Todo : Valider si une position de la pince est preferable. 
        	addSequential(new DriveNeutral());
        	addSequential(new IntakeDefault());
        	addSequential(new DriveTrainPTOEngage());
        	
        	// Stop compressor operation to 
        	//Compressor c = new Compressor(0); //TODO : PCM adress is 0
        	//c.setClosedLoopControl(false);
        	
        	addSequential(new Pause(0.25));
        	addSequential(new DriveBackwardRewind());
    	}
    	

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
