package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.command.Command;

public class HorizontalStrafe extends Subsystem1816 {
	private Drivetrain drivetrain;
	
	private double horizontalStrafe;
	
	public HorizontalStrafe(Drivetrain drivetrain) {
		this.drivetrain = drivetrain;
	}

	public void setHorizontalStrafe(double horizontalStrafe) {
		this.horizontalStrafe = horizontalStrafe;
		update();
	}
	
	public double getHoritzonalStrafe() {
		return horizontalStrafe;
	}
	
	@Override
	public void update() {
		drivetrain.setHorizontalStrafe(horizontalStrafe);
	}
	
	public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }

}
