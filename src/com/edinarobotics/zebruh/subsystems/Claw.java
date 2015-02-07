package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;
import com.edinarobotics.zebruh.Components;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Claw extends Subsystem1816 {

	private DoubleSolenoid clampSolenoid;
	private DoubleSolenoid rotateSolenoid;
	private ClawState targetState;
	
	private Elevator elevator;
	
	public Claw(int clampChannel1, int clampChannel2, int rotateChannel1, int rotateChannel2) {
		this.clampSolenoid = new DoubleSolenoid(clampChannel1, clampChannel2);
		this.rotateSolenoid = new DoubleSolenoid(rotateChannel1, rotateChannel2);
		targetState = ClawState.CLAMP_UP_CLOSE;
		elevator = Components.getInstance().elevator;
	}
	
	public enum ClawState {
		CLAMP_DOWN_OPEN(DoubleSolenoid.Value.kForward, DoubleSolenoid.Value.kForward),
		CLAMP_DOWN_CLOSE(DoubleSolenoid.Value.kReverse, DoubleSolenoid.Value.kForward),
		CLAMP_UP_CLOSE(DoubleSolenoid.Value.kReverse, DoubleSolenoid.Value.kReverse);
		
		public DoubleSolenoid.Value clamp, rotate;
		
		private ClawState(DoubleSolenoid.Value clamp, DoubleSolenoid.Value rotate){
			this.clamp = clamp;
			this.rotate = rotate;
		}
	}
	
	public void setClawState(ClawState state) {
		targetState = state;
		update();
	}
	
	public Value getClampState() {
		return clampSolenoid.get();
	}
	
	public Value getRotateState() {
		return rotateSolenoid.get();
	}
	
	@Override
	public void update() {
		clampSolenoid.set(targetState.clamp);
		if (elevator.getEncoderTicks() <= Elevator.CLAW_UP_MAXIMUM_HEIGHT){			
			rotateSolenoid.set(targetState.rotate);
		} else {
			rotateSolenoid.set(DoubleSolenoid.Value.kForward);
		}
	}

}
