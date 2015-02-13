package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveXTimeVerticalCommand extends Command {
	
	private double speed;
	private Drivetrain drivetrain;

	public DriveXTimeVerticalCommand(double time, double speed) {
		super("drivextimevertical");
		drivetrain = Components.getInstance().drivetrain;
		this.speed = speed;
		setTimeout(time);
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		drivetrain.setDrivetrain(speed, 0.0, 0.0);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		drivetrain.setDrivetrain(0.0, 0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		
	}

}
