package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class RunElevatorManualCommand extends Command {

	private Elevator elevator;
	private int ticks;
	private boolean isUp;
	
	public RunElevatorManualCommand(int ticks, boolean isUp) {
		super("RunElevatorManual");
		elevator = Components.getInstance().elevator;
		this.ticks = ticks;
		this.isUp = isUp;
		requires(elevator);
	}

	@Override
	protected void initialize() {
		elevator.setManualTicks(ticks, isUp);
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
		// TODO Auto-generated method stub
		
	}

}
