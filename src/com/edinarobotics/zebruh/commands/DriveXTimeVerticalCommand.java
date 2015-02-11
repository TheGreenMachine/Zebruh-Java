package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.subsystems.RotationDrive;

import edu.wpi.first.wpilibj.command.Command;

public class DriveXTimeCommand extends Command {
	
	private double time, speed;
	private RotationDrive rotate;

	public DriveXTimeCommand(double time, double speed) {
		setTimeout(time);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}

}
