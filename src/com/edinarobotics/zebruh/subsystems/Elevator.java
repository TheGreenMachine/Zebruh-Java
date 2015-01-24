package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class Elevator extends Subsystem1816 {

	public CANTalon leftTalon, rightTalon;
	public DigitalInput upperLimit, lowerLimit;
	public Encoder elevatorEncoder; 
	public ElevatorLevel targetLevel;
	
	private double teleOpElevatorSpeed = 1.0;
	private double elevatorSpeed = 1.0;
	private boolean isOverride = false;
	private boolean hasBeenOverridden = false;
	private boolean isCalibrated = false;
	
	public final int ELEVATOR_DEADZONE_TICKS = 666;
			
	public Elevator(int leftTalon, int rightTalon, int upperLimit, int lowerLimit, 
			int channelA, int channelB) {
		this.leftTalon = new CANTalon(leftTalon);
		this.rightTalon = new CANTalon(rightTalon);
		this.leftTalon.changeControlMode(CANTalon.ControlMode.Position);
		this.upperLimit = new DigitalInput(upperLimit);
		this.lowerLimit = new DigitalInput(lowerLimit);
	}
	
	public void setElevatorLevel(ElevatorLevel level) {
		hasBeenOverridden = false;
		isOverride = false;
		targetLevel = level;
		leftTalon.setPosition(targetLevel.encoderTicks);
		rightTalon.setPosition(targetLevel.encoderTicks);
		update();
	}
	
	public void setOverride(boolean isOverride) {
		this.isOverride = isOverride;
		update();
	}
	
	public void setTeleOpElevatorSpeed(double speed){
		teleOpElevatorSpeed = speed;
		update();
	}
	
	public enum ApproxPosition {
		PUSH_BIN(0),
		PUSH_TOTE(1),
		PUSH_BIN_TOTE(2),
		LANDFILL_AUTO(3);
		
		private int positionTicks;
		
		private ApproxPosition(int positionTicks) {
			this.positionTicks = positionTicks;
		}
	}
	
	public enum ElevatorLevel {
		A(0),
		B(1),
		C(2),
		D(3),
		E(4),
		F(5);
		
		private int encoderTicks;
		
		private ElevatorLevel(int encoderTicks) {
			this.encoderTicks = encoderTicks;
		}
	}
	
	public void update() {
		if(!isOverride && !hasBeenOverridden && isCalibrated){
			if(elevatorEncoder.get() < targetLevel.encoderTicks) {
				leftTalon.set(elevatorSpeed);
				rightTalon.set(elevatorSpeed);
			} else if (elevatorEncoder.get() > targetLevel.encoderTicks + ELEVATOR_DEADZONE_TICKS){
				leftTalon.set(-elevatorSpeed);
				rightTalon.set(-elevatorSpeed);
			} else {
				leftTalon.set(0.0);
				rightTalon.set(0.0);
			}
		} else if(isOverride) {
			hasBeenOverridden = true;
			leftTalon.set(teleOpElevatorSpeed);
			rightTalon.set(teleOpElevatorSpeed);
		} else if (hasBeenOverridden){
			leftTalon.set(0);
			rightTalon.set(0);
		} else {
			leftTalon.set(0);
			rightTalon.set(0);
		}
	}
}
