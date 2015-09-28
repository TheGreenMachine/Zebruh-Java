package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;

import edu.wpi.first.wpilibj.Solenoid;

public class Arms extends Subsystem1816 {
	private Solenoid left, right;
	private boolean leftUp, rightUp;
	private ArmState targetArmState;
	
	public Arms(int leftChannel, int rightChannel, int pcmNode) {
		left = new Solenoid(pcmNode, leftChannel);
		right = new Solenoid(pcmNode, rightChannel);
		leftUp = true;
		rightUp = true;
		targetArmState = ArmState.BOTH_UP;
	}
	
	public enum ArmState {
		BOTH_UP(true, true),
		BOTH_DOWN(false, false),
		LEFT_DOWN(false, true),
		RIGHT_DOWN(true, false);
		
		public boolean leftUp;
		public boolean rightUp;
		
		private ArmState(boolean leftUp, boolean rightUp){
			this.leftUp = leftUp;
			this.rightUp = rightUp;
		}
	}
	
	public void setArmState(ArmState armState) {
		targetArmState = armState;
		update();
	}
	
	public ArmState getArmState() {
		return targetArmState;
	}
	
	public void toggleLeftArm() {
		if(targetArmState.equals(ArmState.BOTH_DOWN))
			targetArmState = ArmState.RIGHT_DOWN;
		else if(targetArmState.equals(ArmState.BOTH_UP))
			targetArmState = ArmState.LEFT_DOWN;
		else if(targetArmState.equals(ArmState.RIGHT_DOWN))
			targetArmState = ArmState.BOTH_DOWN;
		else
			targetArmState = ArmState.BOTH_UP;
		
		update();
	}
	
	public void toggleRightArm() {
		if(targetArmState.equals(ArmState.BOTH_DOWN))
			targetArmState = ArmState.LEFT_DOWN;
		else if(targetArmState.equals(ArmState.BOTH_UP))
			targetArmState = ArmState.RIGHT_DOWN;
		else if(targetArmState.equals(ArmState.LEFT_DOWN))
			targetArmState = ArmState.BOTH_DOWN;
		else
			targetArmState = ArmState.BOTH_UP;
		
		update();
	}
	
	@Override
	public void update() {
		left.set(!targetArmState.leftUp);
		right.set(!targetArmState.rightUp);
	}

}
