package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

public class Drivetrain extends Subsystem1816 {
	private CANTalon topLeft, topRight, bottomLeft, bottomRight;
	private double verticalStrafe, horizontalStrafe, rotation;

	private HRobotDrive hRobotDrive;
	
	private boolean lowGear;
	
	private final double DOWN_SCALE_MAIN = 0.8;
	private final double SLOW_SPEED_SCALE = 0.6;
	
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
		lowGear = false;
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
	
	public void setLowGear(boolean lowGear) {
		this.lowGear = lowGear;
	}
	
	public void setDrivetrain(double verticalStrafe, double horizontalStrafe, double rotation) {
		if(lowGear) {
			verticalStrafe *= SLOW_SPEED_SCALE;
			horizontalStrafe *= SLOW_SPEED_SCALE;
			rotation *= SLOW_SPEED_SCALE;
		}
		this.verticalStrafe = verticalStrafe * DOWN_SCALE_MAIN;
		this.horizontalStrafe = horizontalStrafe;
		this.rotation = rotation * DOWN_SCALE_MAIN;
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
	}
}