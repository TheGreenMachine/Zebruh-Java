package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class SetSingleSolenoidStateCommand extends Command {

	private boolean state;
	private Claw claw;
	
	public SetSingleSolenoidStateCommand(boolean state){
		super("SetSingleSolenoidStateCommand");
		this.state = state;
		claw = Components.getInstance().claw;
		requires(claw);
	}
	
	@Override
	protected void initialize() {
		claw.setSolenoid(state);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
	

}
