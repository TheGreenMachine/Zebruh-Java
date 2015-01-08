package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.command.Command;

public class VerticalStrafe extends Subsystem1816 {

	private Drivetrain drivetrain;
	private double verticalStrafe;

	public VerticalStrafe(Drivetrain drivetrain) {
		this.drivetrain = drivetrain;

	}

	public double getVerticalStrafe() {
		return verticalStrafe;
	}
	
	public void setVerticalStrafe(double verticalStrafe) {
		this.verticalStrafe = verticalStrafe;
		update();
	}

	@Override
	public void update() {
		drivetrain.setVerticalStrafe(verticalStrafe);

	}
	
	public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }

}
