package org.usfirst.frc.team4947.robot.subsystems;

import org.usfirst.frc.team4947.robot.commands.WinchOpen;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem {

    public Solenoid WinchOpenSolenoid = new Solenoid(3);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new WinchOpen());
    }
}

