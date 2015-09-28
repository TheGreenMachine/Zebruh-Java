package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class SetMediumSpeedCommand extends Command {
	private Drivetrain drivetrain;
	private boolean mediumSpeed;
	
	public SetMediumSpeedCommand(boolean mediumSpeed) {
		super("SetMediumSpeed");
		drivetrain = Components.getInstance().drivetrain;
		this.mediumSpeed = mediumSpeed;
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		drivetrain.setMediumSpeed(mediumSpeed);
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}

}
