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
				
				addSequential(new DriveXTimeVerticalCommand(0.55, 0.6));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new SetClawCommand(Claw.ClawState.CLAMP_DOWN_CLOSE));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.TWO_TOTES));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new DriveXTimeHorizontalCommand(3.0, 0.45));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new DriveXTimeVerticalCommand(4.0, 0.8));
				addSequential(new WaitCommand(1.0)); 
				
				break;
				
			case BIN:
				addSequential(new CalibrateElevatorCommand());
				addSequential(new SetClawCommand(Claw.ClawState.CLAMP_UP_OPEN));
				
				addSequential(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.BIN_PICKUP_AUTO));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new SetClawCommand(Claw.ClawState.CLAMP_DOWN_OPEN));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new DriveXTimeVerticalCommand(0.55, 0.65));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new SetClawCommand(Claw.ClawState.CLAMP_DOWN_CLOSE));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.TWO_TOTES));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new DriveXTimeVerticalCommand(4.0, 0.65));
				addSequential(new WaitCommand(1.0));
				
				break;
				
			case TOTE:
				addSequential(new CalibrateElevatorCommand());
				
				addSequential(new SetClawCommand(Claw.ClawState.CLAMP_UP_OPEN));
				addSequential(new WaitCommand(1.0));
				
				addSequential(new DriveXTimeVerticalCommand(5.0, 0.65));
				addSequential(new WaitCommand(1.0));
				
				break;
		}		
	}		
	
	public enum AutoMode {
		BIN_TOTE,
		BIN,
		TOTE;
	}
	
}

