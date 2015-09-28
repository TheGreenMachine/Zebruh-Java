package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.Controls;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class RunElevatorToLevelCommand extends Command {
	private Elevator elevator;
	private Elevator.ElevatorLevel level;
	
	public RunElevatorToLevelCommand(Elevator.ElevatorLevel level) {
		super("RunElevatorToLevel");
		elevator = Components.getInstance().elevator;
		setInterruptible(true);
		this.level = level;
		requires(elevator);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		elevator.setElevatorTarget(level);
		if(Math.abs(Controls.getInstance().gamepad1.getRightY()) > 0.025)
			cancel();
	}

	@Override
	protected boolean isFinished() {
		if(elevator.getTarget() == level.ticks)
			return true;
		else
			return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}

}
