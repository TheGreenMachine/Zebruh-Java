package com.edinarobotics.zebruh.commands;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.HorizontalStrafe;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadHorizontalStrafeCommand extends Command {
	private Gamepad gamepad;
	private HorizontalStrafe horizontalStrafe;
	
	public GamepadHorizontalStrafeCommand(Gamepad gamepad){
		super("GamepadHorizontalStrafe");
		horizontalStrafe = Components.getInstance().horizontalStrafe;
		this.gamepad = gamepad;
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		double horizontalStrafe = gamepad.getGamepadAxisState().getLeftJoystick().getX();
		this.horizontalStrafe.setHorizontalStrafe(horizontalStrafe);
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
