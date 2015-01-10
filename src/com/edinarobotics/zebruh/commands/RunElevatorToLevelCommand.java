package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class RunElevatorToLevelCommand extends Command{
	
	private Elevator elevator;
	private int level;
	
	public RunElevatorToLevelCommand(int level) {
		super("RunElevatorToLevel");
		this.elevator = Components.getInstance().elevator;
		this.level = level;
		requires(elevator);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		elevator.setOverride(false);
		elevator.setElevatorLevel(level);
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
		// TODO Auto-generated method stub
		
	}
}