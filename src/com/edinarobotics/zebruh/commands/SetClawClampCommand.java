package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class SetClawClampCommand extends Command {

	private Claw claw;
	private Claw.SolenoidState state;

	public SetClawClampCommand(Claw.SolenoidState state) {
		super("ClawClampCommand");
		claw = Components.getInstance().claw;
		this.state = state;
		requires(claw);
	}

	@Override
	protected void initialize() {
		claw.setState(state);
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
