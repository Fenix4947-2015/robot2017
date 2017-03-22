package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.commands.IntakeDefault;
import org.usfirst.frc.team4947.robot.commands.IntakeOut;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    public CANTalon intakeMotor = new CANTalon(16);
    public DigitalInput intakeGearInput = new DigitalInput(3);

    public void initDefaultCommand() {        
    	setDefaultCommand(new IntakeDefault());
    }
}

