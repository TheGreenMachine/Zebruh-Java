package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Arms;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleArmsCommand extends Command{
	private Arms arms;
	boolean left;
	
	public ToggleArmsCommand(boolean left){
		super("ToggleArms");
		arms = Components.getInstance().arms;
		this.setInterruptible(true);
		this.left = left;
		requires(arms);
	}
	
	@Override
	protected void initialize() {
		if(left)
			arms.toggleLeftArm();
		else
			arms.toggleRightArm();
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
