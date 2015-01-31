package com.edinarobotics.zebruh;

import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.zebruh.commands.GamepadHorizontalStrafeCommand;
import com.edinarobotics.zebruh.commands.GamepadRotationCommand;
import com.edinarobotics.zebruh.commands.GamepadVerticalStrafeCommand;
import com.edinarobotics.zebruh.subsystems.Drivetrain;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

		Components.getInstance().rotationDrive
				.setDefaultCommand(new GamepadRotationCommand(gamepad1));
		Components.getInstance().verticalStrafe
				.setDefaultCommand(new GamepadVerticalStrafeCommand(gamepad1));
		Components.getInstance().horizontalStrafe
				.setDefaultCommand(new GamepadHorizontalStrafeCommand(gamepad1));
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//System.out.println("Encoder: " + elevator.getEncoderTicks());
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
