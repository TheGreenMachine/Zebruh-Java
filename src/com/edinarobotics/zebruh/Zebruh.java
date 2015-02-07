package com.edinarobotics.zebruh;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zebruh.commands.CalibrateElevatorCommand;
import com.edinarobotics.zebruh.commands.GamepadHorizontalStrafeCommand;
import com.edinarobotics.zebruh.commands.GamepadRotationCommand;
import com.edinarobotics.zebruh.commands.GamepadVerticalStrafeCommand;
import com.edinarobotics.zebruh.subsystems.Claw;
import com.edinarobotics.zebruh.subsystems.Drivetrain;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Zebruh extends IterativeRobot {
	private Drivetrain drivetrain;
	private Claw claw;
	private Elevator elevator;

	public void robotInit() {
		Controls.getInstance();
		Components.getInstance();
		drivetrain = Components.getInstance().drivetrain;
		elevator = Components.getInstance().elevator;
		//claw = Components.getInstance().claw;
	}

	public void autonomousPeriodic() {
		
	}

	@Override
	public void teleopInit() {
		Gamepad gamepad0 = Controls.getInstance().gamepad0;

		Components.getInstance().rotationDrive
				.setDefaultCommand(new GamepadRotationCommand(gamepad0));
		Components.getInstance().verticalStrafe
				.setDefaultCommand(new GamepadVerticalStrafeCommand(gamepad0));
		Components.getInstance().horizontalStrafe
				.setDefaultCommand(new GamepadHorizontalStrafeCommand(gamepad0));
		
		CalibrateElevatorCommand calibration = new CalibrateElevatorCommand();
		calibration.start();
		DoubleSolenoid d = new DoubleSolenoid(0, 1);
		d.set(Value.kReverse);
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//elevator.printInformation();
		/*double p = SmartDashboard.getNumber("DB/Slider 0");
		double i = SmartDashboard.getNumber("DB/Slider 1");
		double d = SmartDashboard.getNumber("DB/Slider 2");
		Components.getInstance().elevator.setPID(p, i, d);
		System.out.println("P: " + p + "  |   I: " + i + "  |    D: " + d);*/
	}

	@Override
	public void disabledInit() {
		stop();
	}

	public void testPeriodic() {

	}

	public void stop() {
		// drivetrain.setHorizontalStrafe(0.0);
		// drivetrain.setRotation(0.0);
		// drivetrain.setVerticalStrafe(0.0);
	}
}
