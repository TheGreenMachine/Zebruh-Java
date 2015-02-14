package com.edinarobotics.zebruh.commands;

import com.edinarobotics.utils.gamepad.GamepadNew;
import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class RunElevatorManualCommand extends Command {

	private Elevator elevator;
	private int ticks;
	private boolean isUp;
	
	private GamepadNew gamepad1;
	
	public RunElevatorManualCommand(GamepadNew gamepad1) {
		super("RunElevatorManual");
		elevator = Components.getInstance().elevator;
		this.ticks = ticks;
		this.isUp = isUp;
		this.gamepad1 = gamepad1;
		requires(elevator);
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		double value = gamepad1.getRightJoystick().getY();
		elevator.setManualTicks(value);
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
		// TODO Auto-generated method stub
		
	}

}
