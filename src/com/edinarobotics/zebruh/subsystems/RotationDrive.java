package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.command.Command;

public class RotationDrive extends Subsystem1816 {
	private Drivetrain drivetrain;
	private double rotation;

	public RotationDrive(Drivetrain drivetrain) {
		this.drivetrain = drivetrain;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
		update();
	}

	@Override
	public void update() {
		drivetrain.setRotation(rotation);
	}
	
	public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }
	
}
