package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.commands.IntakeDefault;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    public CANTalon motor = new CANTalon(16);

    public void initDefaultCommand() {
        setDefaultCommand(new IntakeDefault());
    }
}

