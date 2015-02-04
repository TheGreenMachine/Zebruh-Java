package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Elevator;
import com.edinarobotics.zebruh.subsystems.Elevator.ElevatorLevel;

import edu.wpi.first.wpilibj.command.Command;

public class RunElevatorToLevelCommand extends Command {
	
	private Elevator elevator;
	private ElevatorLevel level;
	private boolean isAutoDown;
	
	public RunElevatorToLevelCommand(ElevatorLevel level) {
		super("RunElevatorToLevel");
		elevator = Components.getInstance().elevator;
		this.level = level;
		isAutoDown = false;
		requires(elevator);
	}

	@Override
	protected void initialize() {
		//Remember, we are working with negative ticks. So everything is backwards.
		elevator.setElevatorState(level);
		if(elevator.getLevel().ticks < level.ticks) {
			isAutoDown = false;
		}
		else
			isAutoDown = true;
	}

	@Override
	protected void execute() {
		//This is positive because we are working with negative ticks
		if(isAutoDown) {
			System.out.println(elevator.getEncoderTicks());
			elevator.setElevatorDown(100); 
		}
	}

	@Override
	protected boolean isFinished() {
		if(!isAutoDown)
			return true;
		else
			return elevator.isDownAutoDone();
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
