package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class SetElevatorPositionCommand extends Command {
	private Elevator elevator;
	private int encoderTicks;
	
	public SetElevatorPositionCommand(int encoderTicks) {
		super("SetElevaorPosition");
		this.encoderTicks = encoderTicks;
		elevator = Components.getInstance().elevator;
		requires(elevator);
	}
	
	@Override
	protected void initialize() {
		elevator.setPosition(encoderTicks);
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
