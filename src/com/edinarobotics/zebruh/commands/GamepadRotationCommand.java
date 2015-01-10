package com.edinarobotics.zebruh.commands;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.RotationDrive;

import edu.wpi.first.wpilibj.command.Command;

public class GamepadRotationCommand extends Command {

	private RotationDrive rotationDrive;
	private Gamepad gamepad;
	
	public GamepadRotationCommand(Gamepad gamepad){
		super("GamepadRotationCommand");
		this.gamepad = gamepad;
		this.rotationDrive = Components.getInstance().rotationDrive;
		requires(rotationDrive);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double rotation = gamepad.getGamepadAxisState().getRightJoystick().getX();
		rotationDrive.setRotation(rotation);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
