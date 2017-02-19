package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.commands.GripperDefault;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gripper extends Subsystem {

    public CANTalon motor = new CANTalon(8); // encoder
    
    public Solenoid openSolenoid = new Solenoid(3);
    public Solenoid upSolenoid = new Solenoid(4);
    public Solenoid extendSolenoid = new Solenoid(5);
    
    public DigitalInput gearInput = new DigitalInput(2);

    public void initDefaultCommand() {
        setDefaultCommand(new GripperDefault());
    }
}

