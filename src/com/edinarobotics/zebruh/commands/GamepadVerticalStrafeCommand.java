package com.edinarobotics.zebruh.commands;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.VerticalStrafe;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadVerticalStrafeCommand extends Command {
	private Gamepad gamepad;
	private VerticalStrafe verticalStrafe;

	public GamepadVerticalStrafeCommand(Gamepad gamepad) {
		super("GamepadVerticalStrafe");
		this.gamepad = gamepad;
		verticalStrafe = Components.getInstance().verticalStrafe;
		requires(verticalStrafe);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		double verticalStrafe = gamepad.getLeftJoystick().getY();
		this.verticalStrafe.setVerticalStrafe(verticalStrafe);
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
