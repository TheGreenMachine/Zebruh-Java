package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.CANTalon;

public class Drivetrain extends Subsystem1816 {
	
	private CANTalon topLeft, topRight, bottomLeft, bottomRight, middleTop, middleBottom;
	
	public Drivetrain(int topLeft, int topRight, int bottomLeft,
			int bottomRight, int middleTop, int middleBottom) {
		this.topLeft = new CANTalon(topLeft);
		this.topRight = new CANTalon(topRight);
		this.bottomLeft = new CANTalon(bottomLeft);
		this.bottomRight = new CANTalon(bottomRight);
		this.middleTop = new CANTalon(middleTop);
		this.middleBottom = new CANTalon(middleBottom);
	}

	@Override
	public void update() {

	}

}