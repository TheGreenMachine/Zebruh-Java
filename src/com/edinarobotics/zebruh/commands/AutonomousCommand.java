package com.edinarobotics.zebruh.commands;

import com.edinarobotics.zebruh.subsystems.Arms;
import com.edinarobotics.zebruh.subsystems.Claw;
import com.edinarobotics.zebruh.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousCommand extends CommandGroup {
	
	public AutonomousCommand(AutonomousCommand.AutoMode autoMode) {
		switch(autoMode) {
		
			case BIN_TOTE:
				addParallel(new CalibrateElevatorCommand());

				addSequential(new SetClawCommand(Claw.ClawState.CLAW_DOWN));
				addSequential(new SetClampCommand(Claw.ClampState.CLAMP_OPEN));
				
				addSequential(new WaitCommand(1.0));
				addSequential(new SetClampCommand(Claw.ClampState.CLAMP_CLOSED));
				
				addSequential(new WaitCommand(1.0));
				addSequential(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.THREE_TOTES));
				
				addSequential(new WaitCommand(1.0));
				addSequential(new DriveXTimeHorizontalCommand(1.5, 0.45));
				
				addSequential(new WaitCommand(1.0));
				addSequential(new DriveXTimeVerticalCommand(3.0, 0.8));
				break;
			
			
		
			case BIN:
				addParallel(new CalibrateElevatorCommand());

				addSequential(new SetClawCommand(Claw.ClawState.CLAW_DOWN));
				addSequential(new SetClampCommand(Claw.ClampState.CLAMP_OPEN));
				
				addSequential(new WaitCommand(1.0));
				addSequential(new SetClampCommand(Claw.ClampState.CLAMP_CLOSED));

				addSequential(new WaitCommand(1.0));
				addSequential(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.BIN_PICKUP_AUTO));
				
				addSequential(new WaitCommand(1.0));
				addSequential(new DriveXTimeVerticalCommand(3.0, 0.8));
				break;
				
			case TOTE:
				addParallel(new CalibrateElevatorCommand());
				
				addSequential(new SetClawCommand(Claw.ClawState.CLAW_VERTICAL));
				addSequential(new SetClampCommand(Claw.ClampState.CLAMP_CLOSED));
				
				addSequential(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.TWO_TOTES));
				
				addSequential(new WaitCommand(1.0));
				addSequential(new DriveXTimeVerticalCommand(3.0, 0.8));
				break;
			
			case TWO_BINS:
				addParallel(new CalibrateElevatorCommand());
				addSequential(new SetArmsCommand(Arms.ArmState.BOTH_DOWN));
				addSequential(new WaitCommand(2.0));
				addSequential(new DriveXTimeVerticalCommand(1.25, 1.0));
				break;
				
			case TWO_BINS_BATTLE:
				addParallel(new CalibrateElevatorCommand());
				addSequential(new SetArmsCommand(Arms.ArmState.BOTH_DOWN));
				addSequential(new WaitCommand(1.5));
				addSequential(new DriveXTimeVerticalCommand(1.25, 1.0));
				break;
				
			case TWO_BINS_WAIT:
				//addParallel(new CalibrateElevatorCommand());		
				addSequential(new SetArmsCommand(Arms.ArmState.BOTH_DOWN));
				break;
				
			case DO_NOTHING:
				addSequential(new CalibrateElevatorCommand());
				break;
		}		
	}
	
	public enum AutoMode {
		BIN_TOTE,
		BIN,
		TOTE,
		TWO_BINS,
		TWO_BINS_BATTLE,
		TWO_BINS_WAIT,
		DO_NOTHING;
	}
	
}

