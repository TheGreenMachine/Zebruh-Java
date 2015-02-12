package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class SetDrivetrainCommand extends Command {
	private Drivetrain drivetrain;
	private double verticalStrafe, horizontalStrafe, rotation;
	
	public SetDrivetrainCommand(double verticalStrafe, double horizontalStrafe, double rotation) {
		super("setdrivetraincommand");
		drivetrain = Components.getInstance().drivetrain;
		this.verticalStrafe = verticalStrafe;
		this.horizontalStrafe = horizontalStrafe;
		this.rotation = rotation;
		requires(drivetrain);
	}
	
	@Override
	protected void initialize() {
		drivetrain.setDrivetrain(verticalStrafe, horizontalStrafe, rotation);
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}

}
