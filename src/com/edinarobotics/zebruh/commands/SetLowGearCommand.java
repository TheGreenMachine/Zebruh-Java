package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class SetLowGearCommand extends Command {
	private Drivetrain drivetrain;
	
	private boolean lowGear;
	
	
	public SetLowGearCommand(boolean lowGear) {
		super("setlowgear");
		drivetrain = Components.getInstance().drivetrain;
		this.lowGear = lowGear;
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		drivetrain.setLowGear(lowGear);
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
