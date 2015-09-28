package com.edinarobotics.zebruh;

import com.edinarobotics.utils.gamepad.GamepadNew;
import com.edinarobotics.zebruh.commands.AutonomousCommand;
import com.edinarobotics.zebruh.commands.AutonomousCommand.AutoMode;
import com.edinarobotics.zebruh.commands.CalibrateElevatorCommand;
import com.edinarobotics.zebruh.commands.GamepadDriveCommand;
import com.edinarobotics.zebruh.commands.RunElevatorManualCommand;
import com.edinarobotics.zebruh.subsystems.Claw;
import com.edinarobotics.zebruh.subsystems.Drivetrain;
import com.edinarobotics.zebruh.subsystems.Elevator;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Zebruh extends IterativeRobot {
	private Drivetrain drivetrain;
	private Claw claw;
	private SendableChooser autoChooser;
	private Elevator elevator;
	private boolean wasAutonomous;
	private Command autonomousCommand;

	public void robotInit() {
		Controls.getInstance();
		Components.getInstance();
		drivetrain = Components.getInstance().drivetrain;
		elevator = Components.getInstance().elevator;
		claw = Components.getInstance().claw;
		
		wasAutonomous = false;
		
		autoChooser = new SendableChooser();
		
		autoChooser.addDefault("BIN AND TOTE", new AutonomousCommand(AutoMode.BIN_TOTE));
		autoChooser.addObject("BIN", new AutonomousCommand(AutoMode.BIN));
		autoChooser.addObject("TOTE", new AutonomousCommand(AutoMode.TOTE));
		autoChooser.addObject("TWO BINS", new AutonomousCommand(AutoMode.TWO_BINS));
		autoChooser.addObject("TWO BINS BATTLE", new AutonomousCommand(AutoMode.TWO_BINS_BATTLE));
		autoChooser.addObject("TWO BINS WAIT", new AutonomousCommand(AutoMode.TWO_BINS_WAIT));
		autoChooser.addObject("STAND STILL", new AutonomousCommand(AutoMode.DO_NOTHING));
		SmartDashboard.putData("Autonomous Chooser", autoChooser);
	}
	
	@Override
	public void autonomousInit() {
		wasAutonomous = true;
		
		autonomousCommand = (Command) autoChooser.getSelected();
		
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
		}
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
	}
	
	private void updateDashboard() {
		SmartDashboard.putBoolean("Claw Pickup?", claw.pickUpPosition());
		SmartDashboard.putBoolean("Elevator Pickup?", elevator.pickUpPosition());
		SmartDashboard.putNumber("Current Encoder Ticks", elevator.getEncoderTicks(