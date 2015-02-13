package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.subsystems.Claw;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousCommand extends CommandGroup {
	
	public AutonomousCommand(AutonomousCommand.AutoMode autoMode) {
		switch(autoMode) {
			case BIN_TOTE:
				addSequential(new CalibrateElevatorCommand());

				addSequential(new SetClawCommand(Claw.ClawState.CLAMP_UP_OPEN));	
				
				addSequential(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.BIN_PICKUP_AUTO));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new SetClawCommand(Claw.ClawState.CLAMP_DOWN_OPEN));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new DriveXTimeVerticalCommand(0.4, 0.5));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new SetClawCommand(Claw.ClawState.CLAMP_DOWN_CLOSE));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.THREE_TOTES));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new DriveXTimeHorizontalCommand(3.0, -0.3));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new DriveXTimeVerticalCommand(4.0, 0.65));
				addSequential(new WaitCommand(1.0));
				
				
				
//				addSequential(new SetClawCommand(Claw.ClawState.CLAMP_DOWN_OPEN));
//				addSequential(new DriveXTimeVerticalCommand(1.0, 0.5));
//				addSequential(new SetClawCommand(Claw.ClawState.CLAMP_DOWN_CLOSE));
//				addParallel(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.TWO_TOTES));
//				addSequential(new DriveXTimeVerticalCommand(1.0, -0.5));
//				addSequential(new DriveXTimeHorizontalCommand(0.5, -0.5));
//				addParallel(new DriveXTimeVerticalCommand(2.0, 0.5));
//				addParallel(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.ONE_TOTE));
		}
	}		
	
	public enum AutoMode {
		BIN_TOTE;
	}
	
}

