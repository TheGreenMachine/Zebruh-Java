package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Claw;
import edu.wpi.first.wpilibj.command.Command;

public class SetClampCommand extends Command{
	private Claw claw;
	private Claw.ClampState clampState;
	
	public SetClampCommand(Claw.ClampState clampState){
		super("SetClamp");
		claw = Components.getInstance().claw;
		this.clampState = clampState;
		requires(claw);
	}
	
	@Override
	protected void initialize() {
		claw.setClampState(clampState);
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
