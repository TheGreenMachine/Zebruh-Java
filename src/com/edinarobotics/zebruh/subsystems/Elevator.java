package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class Elevator extends Subsystem1816 {

	private Talon elevatorTalon;
	private Encoder elevatorEncoder;
	private double elevatorManualSpeed;
	private double elevatorAutoSpeed = 1;
	private int targetLevel, lastLevel, currentEncoderCount,
			targetEncoderCount;
	private boolean isOverride, isDown, isDone;

	public Elevator(int elevatorTalon, int elevatorEncoderChannelA,
			int elevatorEncoderChannelB) {
		this.elevatorTalon = new Talon(elevatorTalon);
		this.elevatorEncoder = new Encoder(elevatorEncoderChannelA,
				elevatorEncoderChannelB);
	}

	public void setElevatorLevel(int level) {
		isOverride = false;
		isDone = false;
		if (lastLevel > level) {
			isDown = true;
			targetLevel = lastLevel - level;
		} else {
			isDown = false;
			targetLevel = level - lastLevel;
		}
		lastLevel = level;
		switch (targetLevel) {
		//TODO: set target encoder ticks
		case 0:
			update();
		case 1:
			update();
		case 2:
			update();
		case 3:
			update();
		case 4:
			update();
		case 5:
			update();
		}
	}

	public void setElevatorSpeed(double elevatorSpeed, boolean isOverride) {
		this.elevatorManualSpeed = elevatorSpeed;
		this.isOverride = isOverride;
		isDone = false;
		elevatorTalon.set(elevatorManualSpeed);
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

	public void resetEncoder() {
		elevatorEncoder.reset();
	}

	@Override
	public void update() {
		if (isOverride && !isDone) {
			elevatorTalon.set(elevatorManualSpeed);
		} else if (!isDone) {
			if (isDown) {
				while (getEncoderCount() > targetEncoderCount && !isDone) {
					elevatorTalon.set(-elevatorAutoSpeed);
				}
				isDone = true;
			} else {
				while (getEncoderCount() < targetEncoderCount && !isDone) {
					elevatorTalon.set(elevatorAutoSpeed);
				}
				isDone = true;
			}
		}
		isDone = true;
	}

}
