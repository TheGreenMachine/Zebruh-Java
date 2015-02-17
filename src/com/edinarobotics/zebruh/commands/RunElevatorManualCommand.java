package com.edinarobotics.zebruh.commands;

import com.edinarobotics.utils.gamepad.GamepadNew;
import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class RunElevatorManualCommand extends Command {
	private Elevator elevator;
	private boolean isUp, manual;
	private GamepadNew gamepad1;
	
	public RunElevatorManualCommand(GamepadNew gamepad1) {
		super("RunElevatorManual");
		elevator = Components.getInstance().elevator;
		this.gamepad1 = gamepad1;
		requires(elevator);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		double value = gamepad1.getRightJoystick().getY();
		if(value >= -0.025 && value <= 0.025) {
			elevator.setOverride(false);
			manual = false;
		} else if(value < -0.025) {
			elevator.setOverride(true);
			manual = true;
			isUp = false;
		} else if(value > 0.025) {
			elevator.setOverride(true);
			manual = true;
			isUp = true;
		}
		
		if(manual)
			elevator.setManualTicks(value, isUp);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		
	}

}
