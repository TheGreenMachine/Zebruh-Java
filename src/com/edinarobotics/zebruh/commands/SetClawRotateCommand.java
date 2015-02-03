//package com.edinarobotics.zebruh.commands;
//
//import com.edinarobotics.zebruh.Components;
//import com.edinarobotics.zebruh.subsystems.Claw;
//import com.edinarobotics.zebruh.subsystems.Claw.SolenoidState;
//
//import edu.wpi.first.wpilibj.command.Command;
//
//public class SetClawRotateCommand extends Command{
//	
//	private Claw claw;
//	private SolenoidState rotateState;
//	
//	public SetClawRotateCommand(SolenoidState rotateState){
//		super("ClawRotateCommand");
//		this.claw = Components.getInstance().claw;
//		this.rotateState = rotateState;
//		requires(claw);
//	}
// 
//	@Override
//	protected void initialize() {
//		claw.setState(rotateState);
//	}
//
//	@Override
//	protected void execute() {
//		
//	}
//
//	@Override
//	protected boolean isFinished() {
//		return true;
//	}
//
//	@Override
//	protected void end() {
//		
//	}
//
//	@Override
//	protected void interrupted() {
//		
//	}
//
//}
