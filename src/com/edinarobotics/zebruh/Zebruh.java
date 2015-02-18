package com.edinarobotics.zebruh;

import com.edinarobotics.utils.gamepad.GamepadNew;
import com.edinarobotics.zebruh.commands.AutonomousCommand;
import com.edinarobotics.zebruh.commands.AutonomousCommand.AutoMode;
import com.edinarobotics.zebruh.commands.CalibrateElevatorCommand;
import com.edinarobotics.zebruh.commands.GamepadDriveCommand;
import com.edinarobotics.zebruh.commands.RunElevatorManualCommand;
import com.edinarobotics.zebruh.commands.SetClawCommand;
import com.edinarobotics.zebruh.subsystems.Claw;
import com.edinarobotics.zebruh.subsystems.Claw.ClawState;
import com.edinarobotics.zebruh.subsystems.Drivetrain;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Zebruh extends IterativeRobot {
	private AutoMode lastAutoMode;
	private Drivetrain drivetrain;
	private SendableChooser autoChooser;
	private Elevator elevator;
	private boolean wasAutonomous = false;
	private AutonomousCommand autonomousCommand;

	public void robotInit() {
		Controls.getInstance();
		Components.getInstance();
		drivetrain = Components.getInstance().drivetrain;
		elevator = Components.getInstance().elevator;
		
		autoChooser = new SendableChooser();
		
		autoChooser.addDefault("BIN AND TOTE", new AutonomousCommand(AutoMode.BIN_TOTE));
		autoChooser.addObject("BIN", new AutonomousCommand(AutoMode.BIN));
		autoChooser.addObject("TOTE", new AutonomousCommand(AutoMode.TOTE));
		SmartDashboard.putData("Autonomous Chooser", autoChooser);
	}
	
	@Override
	public void autonomousInit() {
		wasAutonomous = true;
		
		autonomousCommand = (AutonomousCommand) autoChooser.getSelected();
		lastAutoMode = autonomousCommand.getAutoMode();
		
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
		
		if(lastAutoMode == AutoMode.BIN_TOTE && wasAutonomous) {
			SetClawCommand setClaw = new SetClawCommand(ClawState.CLAMP_DOWN_OPEN);
			setClaw.start();
		}
		
		if(!wasAutonomous) {
			CalibrateElevatorCommand calibration = new CalibrateElevatorCommand();
			calibration.start();
			
			SetClawCommand setClaw1 = new SetClawCommand(Claw.ClawState.CLAMP_UP_MIDDLE_CLOSE);
			setClaw1.start();
		}
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
	}
	
	private void updateDashboard() {
		SmartDashboard.putString("Current Level", elevator.getCurrentLevel());
		SmartDashboard.putNumber("Current Encoder Ticks", elevator.getEncoderTicks());
		SmartDashboard.putNumber("Target Encoder Ticks", elevator.getLevel().ticks);
	}

	@Override
	public void disabledInit() {
		stop();
	}

	public void testPeriodic() {

	}

	public void stop() {
		 drivetrain.setHorizontalStrafe(0.0);
		 drivetrain.setRotation(0.0);
		 drivetrain.setVerticalStrafe(0.0);
	}
}
