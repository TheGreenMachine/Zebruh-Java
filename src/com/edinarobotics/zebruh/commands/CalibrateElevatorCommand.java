package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.Components;
import com.edinarobotics.zebruh.subsystems.Elevator;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CalibrateElevatorCommand extends Command {
	private Elevator elevator;
	
	public CalibrateElevatorCommand() {
		super("CallibrateElevator");
		elevator = Components.getInstance().elevator;
		setInterruptible(false);
		requires(elevator);
	}
	
	@Override
	protected void initialize() {
		elevator.getTalonA().changeControlMode(ControlMode.PercentVbus);
		elevator.getTalonA().set(0.2);
		SmartDashboard.putString("Elevator Calibration", "Not Calibrated");
	}

	@Override
	protected void execute() {
		
	}
	

	@Override
	protected boolean isFinished() {
		if(elevator.getLSBottom()) {
			elevator.getTalonA().set(0.0);
			elevator.getTalonA().changeControlMode(ControlMode.Position);
			return true;
		} 
		return false;
	}

	@Override
	protected void end() {
		elevator.setPosition(0);
		SmartDashboard.putString("Elevator Calibration", "Calibrated");
	}

	@Override
	protected void interrupted() {
		
	}

}
