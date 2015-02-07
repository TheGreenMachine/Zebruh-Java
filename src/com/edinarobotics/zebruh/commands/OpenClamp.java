package com.edinarobotics.zebruh.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class OpenClamp extends Command {

	private DoubleSolenoid clamp;
	
	public OpenClamp() {
		clamp = new DoubleSolenoid(0, 1);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		clamp.set(Value.kForward);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
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
