package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.common.Updatable;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

public class Drivetrain implements Updatable {

	private CANTalon topLeft, topRight, bottomLeft, bottomRight;
	private double verticalStrafe, horizontalStrafe, rotation;

	private HRobotDrive hRobotDrive;

	public Drivetrain(int topLeft, int topRight, int bottomLeft,
			int bottomRight, int middleTop, int middleBottom) {
		this.topLeft = new CANTalon(topLeft);
		this.topRight = new CANTalon(topRight);
		this.bottomLeft = new CANTalon(bottomLeft);
		this.bottomRight = new CANTalon(bottomRight);
		this.topLeft.changeControlMode(ControlMode.PercentVbus);
		this.topRight.changeControlMode(ControlMode.PercentVbus);
		this.bottomLeft.changeControlMode(ControlMode.PercentVbus);
		this.bottomRight.changeControlMode(ControlMode.PercentVbus);
		this.topLeft.enableControl();
		this.topRight.enableControl();
		this.bottomLeft.enableControl();
		this.bottomRight.enableControl();
		hRobotDrive = new HRobotDrive(this.topLeft, this.bottomLeft, this.topRight,
				this.bottomRight, middleBottom, middleTop);
	}

	public void setVerticalStrafe(double verticalStrafe) {
		this.verticalStrafe = verticalStrafe;
		update();
	}

	public void setHorizontalStrafe(double horizontalStrafe) {
		this.horizontalStrafe = horizontalStrafe;
		update();
	}
	
	public void setRotation (double setRotation) {
		this.rotation = setRotation;
		update();
	}

	@Override
	public void update() {
		hRobotDrive.hDrive(verticalStrafe, horizontalStrafe, rotation);
	}

}