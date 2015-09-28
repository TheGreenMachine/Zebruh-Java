package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class Claw extends Subsystem1816 {
	private DoubleSolenoid clampSolenoid;
	private DoubleSolenoid rotateSolenoid;
	private Solenoid carrySolenoid;
	private ClawState targetClawState;
	private ClampState targetClampState;
	
	private Elevator elevator;
	
	public Claw(int clampChannel1, int clampChannel2, int rotateChannel1, int rotateChannel2, int singleChannel, int pcmNode) {
		clampSolenoid = new DoubleSolenoid(pcmNode, clampChannel1, clampChannel2);
		rotateSolenoid = new DoubleSolenoid(pcmNode, rotateChannel1, rotateChannel2);
		carrySolenoid = new Solenoid(pcmNode, singleChannel);
		targetClawState = ClawState.CLAW_VERTICAL;
		targetClampState = ClampState.CLAMP_CLOSED;
	}
	
	public enum ClawState {
		CLAW_DOWN(DoubleSolenoid.Value.kForward, true),
		CLAW_CARRY(DoubleSolenoid.Value.kReverse, true),
		CLAW_VERTICAL(DoubleSolenoid.Value.kReverse, false);
		
		public DoubleSolenoid.Value rotate;
		public boolean bumper;
		
		private ClawState(DoubleSolenoid.Value rotate, boolean solenoidState){
			this.rotate = rotate;
			this.bumper = solenoidState;
		}
	}
	
	public enum ClampState {
		CLAMP_OPEN(DoubleSolenoid.Value.kReverse),
		CLAMP_CLOSED(DoubleSolenoid.Value.kForward);
		
		public DoubleSolenoid.Value clamp;
		
		private ClampState(DoubleSolenoid.Value clamp)
		{
			this.clamp = clamp;
		}
	}
	
	public void setElevator(Elevator elevator){
		this.elevator = elevator;
	}
	
	public Elevator getElevator(){
		return elevator;
	}
	
	public void setClawState(ClawState clawState) {
		if(!(elevator.getEncoderTicks() > Elevator.CLAW_UP_MAX_HEIGHT) || !targetClawState.equals(ClawState.CLAW_DOWN)) {
			targetClawState = clawState;
		}
		
		update();
	}
	public void setClampState(ClampState clampState) {
		targetClampState = clampState;
		
		update();
	}
	
	public ClampState getClampState() {
		return targetClampState;
	}
	
	public ClawState getClawState() {
		return targetClawState;
	}
	
	public boolean pickUpPosition() {
		if(targetClawState.equals(ClawState.CLAW_VERTICAL) && targetClampState.equals(ClampState.CLAMP_CLOSED))
			return true;
		else
			return false;
	}
	
	@Override
	public void update() {
		clampSolenoid.set(targetClampState.clamp);
		rotateSolenoid.set(targetClawState.rotate);
		carrySolenoid.set(targetClawState.bumper);
	}

}
