package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Claw;
import edu.wpi.first.wpilibj.command.Command;

public class SetClawCommand extends Command{
	private Claw claw;
	private Claw.ClawState state;
	
	public SetClawCommand(Claw.ClawState state){
		super("SetClaw");
		claw = Components.getInstance().claw;
		this.state = state;
		requires(claw);
	}
	
	@Override
	protected void initialize() {
		claw.setClawState(state);
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
