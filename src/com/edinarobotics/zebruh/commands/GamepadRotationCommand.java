package com.edinarobotics.zebruh.commands;

import com.edinarobotics.utils.gamepad.GamepadNew;
import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadRotationCommand extends Command {
	private Drivetrain drivetrain;
	private GamepadNew gamepad;

	public GamepadRotationCommand(GamepadNew gamepad0) {
		super("GamepadRotationCommand");
		this.gamepad = gamepad0;
		this.drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		double rotation = gamepad.getRightJoystick().getX();
		drivetrain.setRotation(-rotation);
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
