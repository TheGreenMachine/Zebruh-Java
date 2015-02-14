package com.edinarobotics.zebruh;

import com.edinarobotics.utils.commands.MaintainStateCommand;
import com.edinarobotics.utils.gamepad.GamepadNew;
import com.edinarobotics.zebruh.commands.AutonomousCommand;
import com.edinarobotics.zebruh.commands.CalibrateElevatorCommand;
import com.edinarobotics.zebruh.commands.GamepadDriveCommand;
import com.edinarobotics.zebruh.commands.RunElevatorManualCommand;
import com.edinarobotics.zebruh.commands.SetClawCommand;
import com.edinarobotics.zebruh.subsystems.Claw;
//import com.edinarobotics.zebruh.subsystems.Claw;
import com.edinarobotics.zebruh.subsystems.Drivetrain;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Zebruh extends IterativeRobot {
	private Drivetrain drivetrain;
//	private Claw claw;
	private Elevator elevator;
	
	private boolean wasAutonomous = false;
	
	private Command autonomousCommand;

	public void robotInit() {
		Controls.getInstance();
		Components.getInstance();
		drivetrain = Components.getInstance().drivetrain;
		elevator = Components.getInstance().elevator;
		
		
		//claw = Components.getInstance().claw;
	}
	
	@Override
	public void autonomousInit() {
		wasAutonomous = true;
		
		autonomousCommand = new AutonomousCommand(AutonomousCommand.AutoMode.BIN_TOTE);
		
		autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		GamepadNew gamepad0 = Controls.getInstance().gamepad0;
		GamepadNew gamepad1 = Controls.getInstance().gamepad1;

		Components.getInstance().drivetrain
				.setDefaultCommand(new GamepadDriveCommand(gamepad0));
		Components.getInstance().elevator.setDefaultCommand(new RunElevatorManualCommand(gamepad1));
		
		if(!wasAutonomous) {
			CalibrateElevatorCommand calibration = new CalibrateElevatorCommand();
			calibration.start();
			
			SetClawCommand setClaw = new SetClawCommand(Claw.ClawState.CLAMP_UP_CLOSE);
			setClaw.start();
		}
		
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
