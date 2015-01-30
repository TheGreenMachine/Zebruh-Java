package com.edinarobotics.zebruh;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zebruh.commands.GamepadHorizontalStrafeCommand;
import com.edinarobotics.zebruh.commands.GamepadRotationCommand;
import com.edinarobotics.zebruh.commands.GamepadVerticalStrafeCommand;
import com.edinarobotics.zebruh.commands.RunElevatorManualCommand;
import com.edinarobotics.zebruh.subsystems.Drivetrain;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Zebruh extends IterativeRobot {
	private Drivetrain drivetrain;
	private Elevator elevator;

	public void robotInit() {
		Controls.getInstance();
		Components.getInstance();
		drivetrain = Components.getInstance().drivetrain;
		elevator = Components.getInstance().elevator;
	}

	public void autonomousPeriodic() {

	}

	@Override
	public void teleopInit() {
		Gamepad gamepad1 = Controls.getInstance().gamepad;

		Gamepad gamepadManual = Controls.getInstance().gamepad1;

		Components.getInstance().rotationDrive
				.setDefaultCommand(new GamepadRotationCommand(gamepad1));
		Components.getInstance().verticalStrafe
				.setDefaultCommand(new GamepadVerticalStrafeCommand(gamepad1));
		Components.getInstance().horizontalStrafe
				.setDefaultCommand(new GamepadHorizontalStrafeCommand(gamepad1));
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		System.out.println(elevator.getEncoderTicks());
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
