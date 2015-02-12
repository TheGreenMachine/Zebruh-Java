package com.edinarobotics.zebruh.subsystems;


import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class Drivetrain extends Subsystem1816 {
	private CANTalon topLeft, topRight, bottomLeft, bottomRight;
	private double verticalStrafe, horizontalStrafe, rotation;

	private HRobotDrive hRobotDrive;
	
	private final int RAMP_RATE = 100;

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
		this.topLeft.setVoltageRampRate(RAMP_RATE);
		this.topRight.setVoltageRampRate(RAMP_RATE);
		this.bottomLeft.setVoltageRampRate(RAMP_RATE);
		this.bottomRight.setVoltageRampRate(RAMP_RATE);
		hRobotDrive = new HRobotDrive(this.topLeft, this.bottomLeft,
				this.topRight, this.bottomRight, middleBottom, middleTop);
	}

	public void setVerticalStrafe(double verticalStrafe) {
		this.verticalStrafe = verticalStrafe;
		update();
	}

	public void setHorizontalStrafe(double horizontalStrafe) {
		this.horizontalStrafe = horizontalStrafe;
		update();
	}

	public void setRotation(double setRotation) {
		this.rotation = setRotation;
		update();
	}
	
	public void setDrivetrain(double verticalStrafe, double horizontalStrafe, double rotation) {
		this.verticalStrafe = verticalStrafe;
		this.horizontalStrafe = horizontalStrafe;
		this.rotation = rotation;
		update();
	}
	
	public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }

	@Override
	public void update() {
		hRobotDrive.hDrive(verticalStrafe, horizontalStrafe, rotation);
//		System.out.println("TopLeft: " + topLeft.get() + "      TopRight: " + topRight.get() + 
//				"     BottomRight: " + bottomRight.get() + "      BottomLeft: " + bottomLeft.get());
	}
}