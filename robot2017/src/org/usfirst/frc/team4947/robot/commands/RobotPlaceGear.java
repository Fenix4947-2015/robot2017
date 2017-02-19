package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class RobotPlaceGear extends Command {
	NetworkTable table ;
	double[] lastX; 
	double[] lastY; 
	double[] area ; 
    public RobotPlaceGear() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gripper);
    	table = NetworkTable.getTable("GRIP/myContoursReport");
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lastX = new double[0]; 
		lastY = new double[0]; 
		area = new double[0]; 
	setTimeout(30);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double[] defaultValue = new double[0];
		double BlobCenterX[] = table.getNumberArray("centerX",defaultValue);
		double BlobCenterY[] = table.getNumberArray("centerY",defaultValue);
		double Area[] = table.getNumberArray("area",defaultValue);		
		double[] Xprocessing = new double[0];
		//1280 X 720 pixels in image // en fait, c'Est 640 x 360 sur la webcam, dans sa config par défaut. 
		boolean itworks = true;
		if(itworks)
			if(1==BlobCenterX.length)
			{
				Xprocessing[0] = BlobCenterX[0];
				Xprocessing[1] = BlobCenterX[0];		
			}
			if(2==BlobCenterX.length)
			{
				Xprocessing[0] = BlobCenterX[0];
				Xprocessing[1] = BlobCenterX[1];			
			}
			if(3<=BlobCenterX.length)
			{
				int[] mostSimilarIndexes = CheckSimilarityIndex(Area);
				Xprocessing[0] = mostSimilarIndexes[0];
				Xprocessing[1] = mostSimilarIndexes[1];		
			}
			double middle = (Xprocessing[0] + Xprocessing[1])*0.5; // this is the location of the pin relative to camera. 
			
			
			if (middle<310)
			{
				Robot.gripper.motor.set(0.3);
			}
			else if (310<=middle)
			{
				Robot.gripper.motor.set(-0.3);
			}
		else
		{	
			Robot.gripper.motor.set(0.0);
		}
		//		Robot.exampleSubsystem.PistonBench.set(true);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gripper.motor.set(0);
	}
    

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	public int[] CheckSimilarityIndex(double[] comparator)
	{
		int[] mostsimilarindexes = new int[2];
		double smallestDifference = 1000000000000000000000.0;
		
		for(int i = 0; i<comparator.length;i++)
		{
			for(int j = 0; j<comparator.length;j++)
			{
				if(i==j)
				{
				}
				else
				{
					if(Math.abs(comparator[i]-comparator[j])<smallestDifference)
					{
						smallestDifference = Math.abs(comparator[i]-comparator[j]);
						mostsimilarindexes[0]=i;
						mostsimilarindexes[1]=j;
					}
				}
			}
		}		
		return mostsimilarindexes;
	}

}
