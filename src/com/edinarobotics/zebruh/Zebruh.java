package com.edinarobotics.zebruh;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zebruh.commands.GamepadHorizontalStrafeCommand;
import com.edinarobotics.zebruh.commands.GamepadRotationCommand;
import com.edinarobotics.zebruh.commands.GamepadVerticalStrafeCommand;
import com.edinarobotics.zebruh.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Zebruh extends IterativeRobot {
	
	private Drivetrain drivetrain;
	
    public void robotInit() {
    	Controls.getInstance();
    	Components.getInstance();
    	drivetrain = Components.getInstance().drivetrain;
    }

    public void autonomousPeriodic() {

    }
    
    @Override
    public void teleopInit() {
    	Gamepad gamepad1 = Controls.getInstance().gamepad;
    	Components.getInstance().rotationDrive.setDefaultCommand(new GamepadRotationCommand(gamepad1));
    	Components.getInstance().verticalStrafe.setDefaultCommand(new GamepadVerticalStrafeCommand(gamepad1));
    	Components.getInstance().horizontalStrafe.setDefaultCommand(new GamepadHorizontalStrafeCommand(gamepad1));    	
    }

    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
        
    }
    
    public void testPeriodic() {
    
    }
    
}
