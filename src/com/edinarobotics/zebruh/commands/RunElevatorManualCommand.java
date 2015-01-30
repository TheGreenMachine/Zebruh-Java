package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class RunElevatorManualCommand extends Command {

	private Elevator elevator;
	private double speed;

	public RunElevatorManualCommand(double speed) {
		super("RunElevatorManual");
		elevator = Components.getInstance().elevator;
		this.speed = speed;
		requires(elevator);
	}

	@Override
	protected void initialize() {
		elevator.setElevatorSpeed(speed);
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
