package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Command;

public class Elevator extends Subsystem1816 {

	private CANTalon talonA, talonB;
	private DigitalInput ls1, ls2, ls3, ls4;
	private ElevatorLevel targetLevel;
	
	private double teleOpElevatorSpeed = 1.0;
	
	private boolean override;
		
	private double p, i, d;
			
	public Elevator(int leftTalon, int rightTalon, double p, double i, double d, 
			int ls1, int ls2, int ls3, int ls4) {
		talonA = new CANTalon(leftTalon);
		talonB = new CANTalon(rightTalon);
		talonA.enableControl();
		talonB.enableControl();
		talonA.changeControlMode(CANTalon.ControlMode.Position);
		talonA.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talonA.setPID(p, i, d);
		this.p = p;
		this.i = i;
		this.d = d;
		this.ls1 = new DigitalInput(ls1);
		this.ls2 = new DigitalInput(ls2);
		this.ls3 = new DigitalInput(ls3);
		this.ls4 = new DigitalInput(ls4);
		targetLevel = Elevator.ElevatorLevel.A;
		override = false;
		setPosition(0);
	}
	
	public void setElevatorLevel(ElevatorLevel level) {
		setOverride(false);
		targetLevel = level;
		update();
	}
	
	public int getEncoderTicks() {
		return talonA.getEncPosition();
	}
	
	public void setElevatorSpeed(double speed) {
		setOverride(true);
		teleOpElevatorSpeed = speed;
		update();
	}
	
	public Elevator.ElevatorLevel getTargetLevel() {
		return targetLevel;
	}
	
	public void setOverride(boolean override) {
		talonA.changeControlMode(override ? ControlMode.PercentVbus : ControlMode.Position);
		if(!override) {
			talonA.setFeedbackDevice(FeedbackDevice.QuadEncoder);
			talonA.setPID(p, i, d);
		}
		this.override = override;
	}
	
	public void setPosition(int position) {
		talonA.setPosition(position);
	}
	
	public enum ElevatorLevel {
		A(4000),
		B(8000),
		C(12000),
		D(16000),
		E(20000),
		F(24000),
		G(0);
		
		private int encoderTicks;
		
		public int getEncoderTicks() {
			return encoderTicks;
		}
		
		private ElevatorLevel(int encoderTicks) {
			this.encoderTicks = encoderTicks;
		}
	}
	
	public CANTalon getTalon() {
		return talonA;
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
	
	public void update() {
		if(override) { //manual control mode
			talonA.set(teleOpElevatorSpeed);
			talonB.set(teleOpElevatorSpeed);
		} else { //auto control mode
			talonA.set(targetLevel.encoderTicks);
			talonB.set(talonA.getOutputVoltage());
		}
	}
}
