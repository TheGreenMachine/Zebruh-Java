package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;

public class Claw extends Subsystem1816 {

	private DoubleSolenoid clampSolenoid;
	private DoubleSolenoid rotateSolenoid;
	private Solenoid solenoid;
	private ClawState targetState;
	
	private boolean solenoidState;
	
	private Elevator elevator;
	
	public Claw(int clampChannel1, int clampChannel2, int rotateChannel1, int rotateChannel2, int singleChannel, int pcmNode) {
		clampSolenoid = new DoubleSolenoid(pcmNode, clampChannel1, clampChannel2);
		rotateSolenoid = new DoubleSolenoid(pcmNode, rotateChannel1, rotateChannel2);
		solenoid = new Solenoid(pcmNode, singleChannel);
		targetState = ClawState.CLAMP_UP_OPEN;
		solenoidState = false;
	}
	
	public enum ClawState {
		CLAMP_DOWN_OPEN(DoubleSolenoid.Value.kReverse, DoubleSolenoid.Value.kForward),
		CLAMP_DOWN_CLOSE(DoubleSolenoid.Value.kForward, DoubleSolenoid.Value.kForward),
		CLAMP_UP_CLOSE(DoubleSolenoid.Value.kForward, DoubleSolenoid.Value.kReverse),
		CLAMP_UP_OPEN(DoubleSolenoid.Value.kReverse, DoubleSolenoid.Value.kReverse);
		
		public DoubleSolenoid.Value clamp, rotate;
		
		private ClawState(DoubleSolenoid.Value clamp, DoubleSolenoid.Value rotate){
			this.clamp = clamp;
			this.rotate = rotate;
		}
	}
	
	public void setElevator(Elevator elevator){
		this.elevator = elevator;
	}
	
	public void setSolenoid(boolean state) {
		solenoidState = state;
		update();
	}
	
	public Elevator getElevator(){
		return elevator;
	}
	
	public void setClawState(ClawState state) {
		
		if(!(elevator.getEncoderTicks() <= Elevator.CLAW_UP_MAXIMUM_HEIGHT && getRotateState() == Value.kForward && state.rotate == Value.kReverse)) {
			targetState = state;
		}
		
		update();
	}
	
	public Value getClampState() {
		return targetState.clamp;
	}
	
	public Value getRotateState() {
		return targetState.rotate;
	}
	
	@Override
	public void update() {
		clampSolenoid.set(targetState.clamp);
		rotateSolenoid.set(targetState.rotate);
		solenoid.set(solenoidState);
//		System.out.println("Clamp Solenoid: " + clampSolenoid.get().toString() + "    Rotate Solenoid: " + rotateSolenoid.get().toString());
	}

}
