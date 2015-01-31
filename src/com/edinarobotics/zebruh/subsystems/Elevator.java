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
	
	private double speed;
	private int targetTicks;
	
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
	}
	
	public enum ElevatorState {
		 BOTTOM(0),
		 PICKUP(4000),
		 ONE_TOTE(8000),
		 TWO_TOTES(12000),
		 THREE_TOTES(16000),
		 TOP(20000);
		 
		 public int ticks;
		 
		 ElevatorState(int ticks) {
			 this.ticks = ticks;
		 }
	}
	
	public boolean getLS1(){
		return ls1.get();
	}
	
	public boolean getLS2(){
		return ls2.get();
	}
	
	public boolean getLS3(){
		return ls3.get();
	}
	
	public boolean getLS4(){
		return ls4.get();
	}
	
	public void setElevatorState(ElevatorState state){
		targetTicks = state.ticks;
		update();
	}
	
	public void setElevatorSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setPosition(int ticks){
		talonA.setPosition(pos);
	}
	
	public void setOverride() {
		
	}
	
	@Override
	public void update() {
		talonA.set(targetTicks);
		talonB.set(talonA.getOutputVoltage());
	}
}
