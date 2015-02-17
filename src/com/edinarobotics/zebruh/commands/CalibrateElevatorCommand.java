package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Elevator;
import com.edinarobotics.zebruh.subsystems.Elevator.ElevatorLevel;

import edu.wpi.first.wpilibj.command.Command;

public class CalibrateElevatorCommand extends Command {
	private Elevator elevator;
	private ElevatorLevel level;
	
	public CalibrateElevatorCommand() {
		super("CallibrateElevator");
		elevator = Components.getInstance().elevator;
		setInterruptible(false);
		requires(elevator);
	}
	
	@Override
	protected void initialize() {
	
	}

	@Override
	protected void execute() {
		
	}
	

	@Override
	protected boolean isFinished() {
		if(elevator.getLS1()) {
			level = ElevatorLevel.BOTTOM;
			return true;
		} else if (elevator.getLS4()) {
			level = ElevatorLevel.TOP;
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		elevator.setPosition(level.ticks+200);
		elevator.setElevatorState(level);
		System.out.println("calibrated" + level.ticks);
	}

	@Override
	protected void interrupted() {
		
	}

}
