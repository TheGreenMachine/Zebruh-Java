package com.edinarobotics.zebruh.commands;

import com.edinarobotics.utils.gamepad.GamepadNew;
import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadVerticalStrafeCommand extends Command {
	private GamepadNew gamepad;
	private Drivetrain drivetrain;

	public GamepadVerticalStrafeCommand(GamepadNew gamepad0) {
		super("GamepadVerticalStrafe");
		this.gamepad = gamepad0;
		drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		double verticalStrafe = gamepad.getLeftJoystick().getY();
		this.drivetrain.setVerticalStrafe(verticalStrafe);
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
