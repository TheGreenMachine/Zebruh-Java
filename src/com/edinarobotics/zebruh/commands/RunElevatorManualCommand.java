package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class RunElevatorManualCommand extends Command{
	private Elevator elevator;
	private boolean isOverride;
	
	public RunElevatorManualCommand(Elevator elevator, boolean isOverride) {
		super("RunElevatorManual");
		this.elevator = Components.getInstance().elevator;
		this.elevator = elevator;
		this.isOverride = isOverride;
		requires(elevator);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		//elevator.setElevatorSpeed(isOverride);
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
