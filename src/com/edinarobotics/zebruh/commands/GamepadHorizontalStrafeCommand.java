package com.edinarobotics.zebruh.commands;

import com.edinarobotics.utils.gamepad.GamepadNew;
import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadHorizontalStrafeCommand extends Command {
	private GamepadNew gamepad;
	private Drivetrain drivetrain;

	public GamepadHorizontalStrafeCommand(GamepadNew gamepad0) {
		super("GamepadHorizontalStrafe");
		drivetrain = Components.getInstance().drivetrain;
		this.gamepad = gamepad0;
		requires(drivetrain);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		double horizontalStrafe = gamepad.getLeftJoystick().getX();
		drivetrain.setHorizontalStrafe(horizontalStrafe);
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
