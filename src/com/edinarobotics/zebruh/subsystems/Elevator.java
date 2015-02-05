package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;
import com.edinarobotics.zebruh.Components;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Elevator extends Subsystem1816 {
	
	private CANTalon talonA, talonB;
	private DigitalInput ls1, ls2, ls3, ls4;
	
	private final double P_UP = 0.4;
	private final double I_UP = 0.00001;
	private final double D_UP = 0.0;
	
	private final double P_DOWN = 1;
	private final double I_DOWN = 0.0;
	private final double D_DOWN = 0.0;
	
	private Elevator.ElevatorLevel level;
	private boolean override, downAuto;
	private int currentTicks;
	
	private AnalogInput analog1;
	
	private Claw claw;
	
	public Elevator(int talonAChannel, int talonBChannel, int ls1Channel, int ls2Channel, 
			int ls3Channel, int ls4Channel, int analogChannel1) {
		talonA = new CANTalon(talonAChannel);
		talonB = new CANTalon(talonBChannel);
		analog1 = new AnalogInput(analogChannel1);
		ls1 = new DigitalInput(ls1Channel);
		ls2 = new DigitalInput(ls2Channel);
		ls3 = new DigitalInput(ls3Channel);
		ls4 = new DigitalInput(ls4Channel);
		talonA.changeControlMode(CANTalon.ControlMode.Position);
		talonA.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		talonA.setPID(P_UP, I_UP, D_UP);
		talonB.changeControlMode(CANTalon.ControlMode.Follower);
		override = false;
		ElevatorLevel.setDefault(talonA.getEncPosition());
		level = ElevatorLevel.DEFAULT;
		downAuto = false;
		claw = Components.getInstance().claw;
	}
	
	public enum ElevatorLevel {
		 BOTTOM(0),
		 PICKUP(-1000),
		 ONE_TOTE(-2000),
		 TWO_TOTES(-3000),
		 THREE_TOTES(-4000),
		 TOP(-6500),
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
		return !ls4.get();
	}
	
	public double getAnalog1() {
		return analog1.getVoltage();
	}
	
	public void setElevatorState(ElevatorLevel state) {
		setOverride(false);
		if(level.ticks < state.ticks) {
			downAuto = true;
			talonA.setPID(P_DOWN, I_DOWN, D_DOWN);
		}
		else {
			downAuto = false;
			talonA.setPID(P_UP, I_UP, D_UP);
		}
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
		talonA.setPID(P_DOWN, I_DOWN, D_DOWN);
		
		if((!getLS1() && !getLS4()) || ((getLS1() && isUp) || (getLS4() && !isUp))) {
			currentTicks = talonA.getEncPosition() + ticks;
			update();
		}
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
		this.override = override;
	}
	
	@Override
	public void update() {
		System.out.println("Target: " + level.ticks + "      Current: " + getEncoderTicks());
		
		if(override) {
			talonA.set(currentTicks);
			talonB.set(talonA.getDeviceID());
		} else {
			if(!downAuto) {
				System.out.println("Going up!");
				if(claw.getRotateState() == DoubleSolenoid.Value.kReverse && level == ElevatorLevel.TOP) {
					talonA.set(level.ticks);
					talonB.set(talonA.getDeviceID());
				}
				talonA.set(level.ticks);
				talonB.set(talonA.getDeviceID());
			} else {
				System.out.println("Going down!");
				talonA.set(currentTicks);
				talonB.set(talonA.getDeviceID());
			}
		}
	}
}
