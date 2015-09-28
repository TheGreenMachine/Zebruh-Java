package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;
import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.Controls;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem1816 {
	private CANTalon talonA, talonB;
	private DigitalInput lsBottom, lsTop;

	private Claw claw;

	private int talonAChannel;

	public static final int CLAW_UP_MAX_HEIGHT = 7750;//6700;
	public static final int MAX_HEIGHT = 7820;
	public static final int MIN_HEIGHT = -50;
	private static final int MANUAL_TICKS_UP = 700;
	private static final int MANUAL_TICKS_DOWN = 600;
	private static final int AUTO_TICKS_DOWN = 600;
	
	private double target;
	public boolean autoDownDone;


	public Elevator(int talonAChannel, int talonBChannel, int lsBottomChannel, int lsTopChannel) {
		talonA = new CANTalon(talonAChannel);
		talonB = new CANTalon(talonBChannel);
		
		lsBottom = new DigitalInput(lsBottomChannel);
		lsTop = new DigitalInput(lsTopChannel);
		
		this.talonAChannel = talonAChannel;
		
		talonA.changeControlMode(CANTalon.ControlMode.Position);
		talonA.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		talonB.changeControlMode(CANTalon.ControlMode.Follower);
		
		target = 0;
	}

	public enum PIDValues {
		UP_AUTO(3.6, 0.0, 2.5),
		UP_MANUAL(1.5, 0.0, 0.0),
		DOWN_AUTO(1.5, 0.0, 10),
		DOWN_MANUAL(1.0, 0.0, 0.0);
		
		public double p, i, d;
		
		private PIDValues(double p, double i, double d) {
			this.p = p;
			this.i = i;
			this.d = d;
		}
	}
	
	public enum ElevatorLevel {
		BOTTOM(MIN_HEIGHT), 
		TWO_TOTES(2400), 
		THREE_TOTES(3965), 
		FOUR_TOTES(5224),
		BIN_FOUR(6641), 
		BIN_FIVE(MAX_HEIGHT),
		BIN_PICKUP_AUTO(750);
		
		public int ticks;

		private ElevatorLevel(int ticks) {
			this.ticks = ticks;
		}
	}

	public double getEncoderTicks() {
		return -talonA.getEncPosition();
	}
	
	public double getTarget() {
		return target;
	}
	
	public CANTalon getTalonA() {
		return talonA;
	}

	public boolean getLSBottom() {
		return !lsBottom.get();
	}

	public boolean getLSTop() {
		return !lsTop.get();
	}
	
	public void setElevatorTarget(double joystickValue) {   //MANUAL CONTROL!!!
		double current = getEncoderTicks();
		target = current;
		
		if(joystickValue > 0) {   //If going up.
			talonA.setPID(PIDValues.UP_MANUAL.p, PIDValues.UP_MANUAL.i, PIDValues.UP_MANUAL.d);
			
			if(!claw.getClawState().equals(Claw.ClawState.CLAW_DOWN)) {  //If claw is up.
				if(current < CLAW_UP_MAX_HEIGHT) {
					target = current + (joystickValue * MANUAL_TICKS_UP);  //Set the change.
					
					if(target > CLAW_UP_MAX_HEIGHT) {  	//Coerce to the target.
						target = CLAW_UP_MAX_HEIGHT;
					}
				}
				else {
					target = CLAW_UP_MAX_HEIGHT;  //If it somehow got above, bring it down.
				}
				
			}
			else {   //If claw is down.
				if(current < MAX_HEIGHT) {
					target = current + (joystickValue * MANUAL_TICKS_UP);  //Set the change.
					
					if(target > MAX_HEIGHT) {  	//Coerce to the target.
						target = MAX_HEIGHT;
					}
				}
				else {
					target = MAX_HEIGHT;  //If it somehow got above, bring it down.
				}
				
			}	
		}
		else if(joystickValue < 0) {  //If going down.
			talonA.setPID(PIDValues.DOWN_MANUAL.p, PIDValues.DOWN_MANUAL.i, PIDValues.DOWN_MANUAL.d);
			
			if(current > MIN_HEIGHT) {
				target = current + (joystickValue * MANUAL_TICKS_DOWN);  //Set the change.
				
				if(target < MIN_HEIGHT) {  	//Coerce to the target.
					target = MIN_HEIGHT;
				}
			}
			else {
				target = MIN_HEIGHT;  //If it somehow got below, bring it up.
			}
			/*
			if(target < MIN_HEIGHT + 250)    						//Once it gets below 250, slow to 1/4 speed.
				target = ((target - current) * 0.5) + current;
			
			else if(target < MIN_HEIGHT + 500)						//Once it gets below 500, slow to 1/2 speed.
				target = ((target - current) * 0.75) + current;
			*/
		}
		
		update();
	}
	
	public void setElevatorTarget(ElevatorLevel targetLevel) {	 //AUTO LEVEL CONTROL!!!
		double current = getEncoderTicks();	
		
		
		if(targetLevel.ticks > current) {																		//Automatic going up.
			talonA.setPID(PIDValues.UP_AUTO.p, PIDValues.UP_AUTO.i, PIDValues.UP_AUTO.d);
			
			target = targetLevel.ticks;
			
			if(target > CLAW_UP_MAX_HEIGHT && !(claw.getClawState() == Claw.ClawState.CLAW_DOWN)) { //Don't let claw up go above max height.
				target = CLAW_UP_MAX_HEIGHT;
			}
		}
		else {																						//Automatic going down.
			talonA.setPID(PIDValues.DOWN_AUTO.p, PIDValues.DOWN_AUTO.i, PIDValues.DOWN_AUTO.d);
			
			
			target = current - AUTO_TICKS_DOWN;
			
			if(target < targetLevel.ticks) {
				target = targetLevel.ticks;
			}
		}
		
		update();
	}
	
	public void setClaw(Claw claw) {
		this.claw = claw;
	}

	public Claw getClaw() {
		return claw;
	}

	private void setTalons(double ticks) {
		talonA.set(-ticks);
		talonB.set(talonAChannel);
	}
	
	public void setPosition(int ticks) {
		talonA.setPosition(ticks+5);
	}
	
	public void setDefaultCommand(Command command) {
		if (getDefaultCommand() != null) {
			super.getDefaultCommand().cancel();
		}
		super.setDefaultCommand(command);
	}

	public boolean pickUpPosition() {
		if(getEncoderTicks() < 50)
			return true;
		else
			return false;
	}
	
	@Override
	public void update() {
		setTalons(target);
	}
	
}
