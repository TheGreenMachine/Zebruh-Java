package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator extends Subsystem1816 {
	
	private CANTalon talonA, talonB;
	private DigitalInput ls1, ls2, ls3, ls4;
	
	private final double P = .1;
	private final double I = 0.0;
	private final double D = 3.0;
	
	private double speed;
	private Elevator.ElevatorLevel level;
	private boolean isOverride;
	private int currentTicks;
	
	public Elevator(int talonAChannel, int talonBChannel, int ls1Channel, int ls2Channel, 
			int ls3Channel, int ls4Channel) {
		talonA = new CANTalon(talonAChannel);
		talonB = new CANTalon(talonBChannel);
		ls1 = new DigitalInput(ls1Channel);
		ls2 = new DigitalInput(ls2Channel);
		ls3 = new DigitalInput(ls3Channel);
		ls4 = new DigitalInput(ls4Channel);
		talonA.changeControlMode(CANTalon.ControlMode.Position);
		talonA.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		talonA.setPID(P, I, D);
		isOverride = false;
		ElevatorLevel.setDefault(1000);
		level = ElevatorLevel.DEFAULT;
	}
	
	public enum ElevatorLevel {
		 BOTTOM(0),
		 PICKUP(4000),
		 ONE_TOTE(8000),
		 TWO_TOTES(12000),
		 THREE_TOTES(16000),
		 TOP(20000),
		 DEFAULT(100);
		 
		 public int ticks;
		 
		 ElevatorLevel(int ticks) {
			 this.ticks = ticks;
		 }
		 
		 static public void setDefault(int ticks) {
			 DEFAULT.ticks = ticks;
		 }
	}
	
	public int getEncoderTicks() {
		return talonA.getEncPosition();
	}
	
	public boolean getLS1() {
		return ls1.get();
	}
	
	public boolean getLS2() {
		return ls2.get();
	}
	
	public boolean getLS3() {
		return ls3.get();
	}
	
	public boolean getLS4() {
		return ls4.get();
	}
	
	public void setElevatorState(ElevatorLevel state) {
		setOverride(false);
		level = state;
		update();
	}
	
	public void setElevatorSpeed(double speed) {
		setOverride(true);
		this.speed = speed;
		update();
	}
	
	public void setElevatorSpeed(double speed, boolean wasUp) {
		setOverride(true);
		this.speed = speed;
		talonA.changeControlMode(CANTalon.ControlMode.Position);
		if(wasUp){
			currentTicks = talonA.getEncPosition() + 150;
		} else {
			currentTicks = talonA.getEncPosition() - 150;
		}
		update();
	}
	
	public void setPosition(int ticks) {
		talonA.setPosition(ticks);
	}
	
	private void setOverride(boolean override) {
		talonA.changeControlMode(override ? CANTalon.ControlMode.PercentVbus : CANTalon.ControlMode.Position);
		isOverride = override;
	}
	
	@Override
	public void update() {
		if(isOverride){
			if(getLS1() && speed > 0.0){
				talonA.set(0.0);
				talonB.set(0.0);
			} else if(getLS4() && speed < 0.0){
				talonA.set(0.0);
				talonB.set(0.0);
			} else{
				if(speed == 0.0){
					talonA.set(currentTicks);
					talonB.set(talonA.getOutputVoltage());
				} else {
					talonA.set(speed);
					talonB.set(speed);
				}
			}
		} else {
			talonA.set(level.ticks);
			talonB.set(talonA.getOutputVoltage());
		}
	}
}
