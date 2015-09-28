package com.edinarobotics.zebruh.commands;

import com.edinarobotics.utils.gamepad.GamepadNew;
import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadDriveCommand extends Command {
	private Drivetrain drivetrain;
	private GamepadNew gamepad;
	
	public GamepadDriveCommand(GamepadNew gamepad) {
		super("gamepaddrivecommand");
		this.gamepad = gamepad;
		drivetrain = Components.getInstance().drivetrain;
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		double rotation = gamepad.getRightJoystick().getX();
		double horizontalStrafe = gamepad.getLeftJoystick().getX();
		double verticalStrafe = gamepad.getLeftJoystick().getY();
		drivetrain.setDrivetrain(verticalStrafe, horizontalStrafe, rotation);
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
