package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Arms;

import edu.wpi.first.wpilibj.command.Command;

public class SetArmsCommand extends Command{
	private Arms arms;
	private Arms.ArmState armState;
	
	public SetArmsCommand(Arms.ArmState armState){
		super("SetArms");
		arms = Components.getInstance().arms;
		this.setInterruptible(true);
		this.armState = armState;
		requires(arms);
	}
	
	@Override
	protected void initialize() {
		arms.setArmState(armState);
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
