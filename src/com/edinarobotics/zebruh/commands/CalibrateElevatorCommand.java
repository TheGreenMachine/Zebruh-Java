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
		requires(elevator);
	}
	
	@Override
	protected void initialize() {
		elevator.setElevatorSpeed(-1.0);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		if(elevator.getLS1()) {
			level = ElevatorLevel.A;
			return true;
		} else if(elevator.getLS2()) {
			level = ElevatorLevel.B;
			return true;
		} else if(elevator.getLS3()) {
			level = ElevatorLevel.C;
			return true;
		} else if (elevator.getLS4()) {
			level = ElevatorLevel.D;
			return true;
		}
		return false;
	}
	
	public String getHey(){
		return "hey";
	}

	@Override
	protected void end() {
		elevator.setElevatorSpeed(0.0);
		elevator.setPosition(level.getEncoderTicks());
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
