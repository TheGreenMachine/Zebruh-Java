package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Claw extends Subsystem1816 {

	private DoubleSolenoid clampSolenoid, rotateSolenoid;
	private DoubleSolenoid.Value clampValue, rotateValue;
	
	public Claw(int clampChannel1, int clampChannel2, int rotateChannel1, int rotateChannel2) {
		this.clampSolenoid = new DoubleSolenoid(clampChannel1,clampChannel2);
		this.rotateSolenoid = new DoubleSolenoid(rotateChannel1,rotateChannel2);
		
	}
	
	public enum SolenoidState {
		CLAMP_OPEN, CLAMP_CLOSE, ROTATE_UP, ROTATE_DOWN;
	}
	
	public void setState(SolenoidState state){
		switch(state) {
			case CLAMP_OPEN:
				if(getRotateState() == DoubleSolenoid.Value.kReverse) 
					clampValue = DoubleSolenoid.Value.kForward;
				break;
			case CLAMP_CLOSE:
				clampValue = DoubleSolenoid.Value.kReverse;
				break;
			case ROTATE_UP:
				rotateValue = DoubleSolenoid.Value.kForward;
				break;
			case ROTATE_DOWN:
				rotateValue = DoubleSolenoid.Value.kReverse;
				break;
		}
	}
	
	public Value getClampState() {
		return clampSolenoid.get();
	}
	
	public Value getRotateState() {
		return clampSolenoid.get();
	}
	
	@Override
	public void update() {
		clampSolenoid.set(clampValue);		
		rotateSolenoid.set(rotateValue);
	}

	
}
