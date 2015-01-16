package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class Elevator extends Subsystem1816 {
	private CANTalon elevatorCANTalon1, elevatorCANTalon2;
	private Encoder elevatorEncoder;
	private DigitalInput upperLimit, lowerLimit;
	private double elevatorManualSpeed = 1.0;
	private double elevatorAutoSpeed = 1.0;
	private int lastLevel = 0;
	private int targetLevel, currentEncoderCount,
			targetEncoderCount;
	private boolean isOverride;

	public Elevator(int elevatorCANTalon1, int elevatorCANTalon2, int elevatorEncoderChannelA,
			int elevatorEncoderChannelB, int upperLimit, int lowerLimit) {
		this.elevatorCANTalon1 = new CANTalon(elevatorCANTalon1);
		this.elevatorCANTalon2 = new CANTalon(elevatorCANTalon2);
		this.elevatorEncoder = new Encoder(elevatorEncoderChannelA,
				elevatorEncoderChannelB);
		this.upperLimit = new DigitalInput(upperLimit);
		this.lowerLimit = new DigitalInput(lowerLimit);
		isOverride = false;
	}

	public void setElevatorLevel(int targetLevel) {
		isOverride = false;
		if (lastLevel > targetLevel) {
			this.targetLevel = lastLevel - targetLevel;
			if(getEncoderCount() > targetEncoderCount)
				elevatorAutoSpeed *= -1;
		} else {
			this.targetLevel = targetLevel - lastLevel;
			if(getEncoderCount() < targetEncoderCount)
			elevatorAutoSpeed *= -1;
		}
		lastLevel = targetLevel;
		switch (this.targetLevel) {
		//TODO: set target encoder ticks
		case 0:
			update();
			break;
		case 1:
			update();
			break;
		case 2:
			update();
			break;
		case 3:
			update();
			break;
		case 4:
			update();
		case 5:
			update();
			break;
		}
	}

	public void setElevatorSpeed(boolean isOverride) {
		this.isOverride = isOverride;
		elevatorCANTalon1.set(elevatorManualSpeed);
		elevatorCANTalon2.set(elevatorManualSpeed);
		update();
	}

	public void setOverride(boolean isOverride) {
		this.isOverride = isOverride;
	}

	public boolean getOverride() {
		return isOverride;
	}

	public int getEncoderCount() {
		currentEncoderCount = elevatorEncoder.get();
		return currentEncoderCount;
	}
	
	public boolean getUpperLimit() {
		return upperLimit.get();
	}

	public boolean getLowerLimit() {
		return lowerLimit.get();
	}

	@Override
	public void update() {
		if (isOverride) {
			elevatorCANTalon1.set(elevatorManualSpeed);
			elevatorCANTalon2.set(elevatorManualSpeed);
		} else {
			if(getEncoderCount() > targetEncoderCount && !getLowerLimit()) {
				elevatorCANTalon1.set(elevatorAutoSpeed);
				elevatorCANTalon2.set(elevatorAutoSpeed);
			}
			else if(getEncoderCount() < targetEncoderCount && !getUpperLimit()) {
				elevatorCANTalon1.set(elevatorAutoSpeed);
				elevatorCANTalon2.set(elevatorAutoSpeed);
			}
			else {
				elevatorCANTalon1.set(0.0);
				elevatorCANTalon2.set(0.0);
			}
		}
	}
}
