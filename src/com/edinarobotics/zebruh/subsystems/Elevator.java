package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator extends Subsystem1816 {
	
	private CANTalon talonA, talonB;
	private DigitalInput ls1, ls2, ls3, ls4;
	
	private final double P = 1.0;
	private final double I = 0.0;
	private final double D = 0.0;
	
	private Elevator.ElevatorLevel level;
	private boolean isOverride, isUpManual, isDownAuto;
	private int currentTicks, pastTicks;
	
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
		talonB.changeControlMode(CANTalon.ControlMode.Follower);
		isOverride = false;
		ElevatorLevel.setDefault(talonA.getEncPosition());
		level = ElevatorLevel.DEFAULT;
		isDownAuto = false;
	}
	
	public enum ElevatorLevel {
		 BOTTOM(0),
		 PICKUP(-1000),
		 ONE_TOTE(-2000),
		 TWO_TOTES(-3000),
		 THREE_TOTES(-4000),
		 TOP(-5000),
		 DEFAULT(100);
		 
		 public int ticks;
		 
		 ElevatorLevel(int ticks) {
			 this.ticks = ticks;
		 }
		 
		 static public void setDefault(int ticks) {
			 DEFAULT.ticks = ticks;
		 }
	}
	
	public void printInformation(){
//		System.out.println("Encoder: " + getEncoderTicks() + "\tTalonA: " + talonA.getOutputVoltage() + "\tTalonB: " + talonB.getOutputVoltage() + "\tTalonA: " + talonA.getOutputCurrent() + "\tTalonB: " + talonB.getOutputCurrent());
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
		if(level.ticks < state.ticks)
			isDownAuto = true;
		else
			isDownAuto = false;
		level = state;
		update();
	}
	
	public Elevator.ElevatorLevel getLevel() {
		return level;
	}
	
	public boolean isDownAutoDone() {
		//Remember, we are working with negative ticks. So everything is backwards.
		return talonA.getEncPosition() > level.ticks;
	}
	
	public void setManualTicks(int ticks, boolean isUp){
		setOverride(true);
		isUpManual = isUp;
		pastTicks = talonA.getEncPosition();
		currentTicks = talonA.getEncPosition() + ticks;
		update();
	}
	
	public void setElevatorDown(int ticks) {
		setOverride(false);
		currentTicks = talonA.getEncPosition() + ticks;
		update();
	}
	
	public void setPosition(int ticks) {
		talonA.setPosition(ticks);
	}
	
	private void setOverride(boolean override) {
		isOverride = override;
	}
	
	@Override
	public void update() {
		if(isOverride){
			if(getLS1() && !isUpManual){
				talonA.set(pastTicks);
				talonB.set(4);
			} else if(getLS4() && isUpManual) {
				talonA.set(pastTicks);
				talonB.set(4);
			} else {
				talonA.set(currentTicks);
				talonB.set(4);
			}
		} else {
			if(!isDownAuto) {
				System.out.println("Going up!");
				talonA.set(level.ticks);
				talonB.set(4);
			} else {
				System.out.println("Going down!");
				talonA.set(currentTicks);
				talonB.set(4);
			}
		}
	}
}
