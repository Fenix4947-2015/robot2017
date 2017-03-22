package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.commands.IntakeDefault;
import org.usfirst.frc.team4947.robot.commands.IntakeOut;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {

    public CANTalon intakeMotor = new CANTalon(16);
    public DigitalInput intakeGearInput = new DigitalInput(3);

    public void initDefaultCommand() {        
    	setDefaultCommand(new IntakeDefault());
    }
    
    public void log(){
    	SmartDashboard.putBoolean("Gear Presence Sensor", intakeGearInput.get());
    	SmartDashboard.putNumber("Intake Motor Speed", ((CANTalon) intakeMotor).getSpeed()); // TODO Validate if this shows correctly
    }
}

