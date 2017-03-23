package org.usfirst.frc.team4947.robot.commands;

import org.usfirst.frc.team4947.robot.Robot;
import org.usfirst.frc.team4947.robot.subsystems.VisionSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CalculatePinLocation extends Command {
	NetworkTable table ;
	double[] lastX; 
	double[] lastY; 
	double[] area ; 
	double DartCenterToCameraCenterOffset = 0 ; // mm. Dart 0 Position to Camera vision axis. The + - direction of value is in the Dart convention
	// TODO : Update the Dart to camera offset value. 
	
	int PictureXPxNumber = 640; // TODO Validate Value
	boolean foundAnswer = false; 
	double PixeltoMMScale = 500/620 ; //500mm for 620 px
    public CalculatePinLocation() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.visionSystem);
    	table = NetworkTable.getTable("GRIP/myContoursReport");
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lastX = new double[0]; 
		lastY = new double[0]; 
		area = new double[0]; 
		setTimeout(2);
		foundAnswer = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double[] defaultValue = new double[0];
		double BlobCenterX[] = table.getNumberArray("centerX",defaultValue);
		double BlobCenterY[] = table.getNumberArray("centerY",defaultValue);
		double Area[] = table.getNumberArray("area",defaultValue);		
		double[] Xprocessing = new double[0];
		//1280 X 720 pixels in image  ... en fait cEst 640 x 360 sur la webcam, dans sa config par defaut. 
		boolean itworks = true;
		if(itworks)
		{
			if(0==BlobCenterX.length)
			{
				itworks = false;
				Xprocessing[0] = 0.0;
				Xprocessing[1] = 0.0;	
			}
			if(1==BlobCenterX.length)
			{
				itworks = false;
				Xprocessing[0] = 0.0;
				Xprocessing[1] = 0.0;		
			}
			else if(2==BlobCenterX.length)
			{
				Xprocessing[0] = BlobCenterX[0];
				Xprocessing[1] = BlobCenterX[1];			
			}
			else if(3<=BlobCenterX.length)
			{
				itworks = false;
				int[] mostSimilarIndexes = CheckSimilarityIndex(Area);
				Xprocessing[0] = BlobCenterX[mostSimilarIndexes[0]];
				Xprocessing[1] = BlobCenterX[mostSimilarIndexes[1]];		
			}
		
			
						
			
			if(itworks) // TODO make a check and validate if vision processing worked. else. pass. 
			{
				double middle = (Xprocessing[0] + Xprocessing[1])*0.5; // this is the location of the pin relative to camera. 
				double desiredOffset = (middle - PictureXPxNumber*0.5 ) *PixeltoMMScale;  //TODO validate scaling
				desiredOffset = desiredOffset + DartCenterToCameraCenterOffset ; 
				foundAnswer = true;
				
				Robot.visionSystem.PinPositionVisionLocation = desiredOffset ;
				
			}
			else
			{
				Robot.visionSystem.PinPositionVisionLocation =  0;
			}
			Robot.visionSystem.VisionPinPositionSuccess = foundAnswer;	
			
		}
		else
		{
				
		}
				
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (isTimedOut() || foundAnswer);
    }

    // Called once after isFinished returns true
    protected void end() {
    	
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
					if(i==j)
					{
						// Same item. to nothing
					}
					else if(Math.abs(comparator[i]-comparator[j])<smallestDifference)
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
