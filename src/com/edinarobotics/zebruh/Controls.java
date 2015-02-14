
package com.edinarobotics.zebruh;

import java.util.ArrayList;
import java.util.List;

import com.edinarobotics.utils.gamepad.FilteredGamepad;
import com.edinarobotics.utils.gamepad.GamepadNew;
import com.edinarobotics.utils.gamepad.gamepadfilters.DeadzoneFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilterSet;
import com.edinarobotics.utils.gamepad.gamepadfilters.PowerFilter;
import com.edinarobotics.zebruh.commands.RunElevatorManualCommand;
import com.edinarobotics.zebruh.commands.RunElevatorToLevelCommand;
import com.edinarobotics.zebruh.commands.SetClawCommand;
import com.edinarobotics.zebruh.commands.SetDrivetrainCommand;
import com.edinarobotics.zebruh.commands.SetLowGearCommand;
import com.edinarobotics.zebruh.subsystems.Claw;
import com.edinarobotics.zebruh.subsystems.Elevator;

public class Controls {
	private static Controls instance;

	public final GamepadNew gamepad0;
	public final GamepadNew gamepad1;
	
	private final int MANUAL_TICKS_UP = -500;
	private final int MANUAL_TICKS_DOWN = -250;
	
 final double MANUAL_MOVEMENT = 0.5;

	private Controls() {
		// Drivetrain control
		//hi eric

		List<GamepadFilter> gamepadFilters = new ArrayList<GamepadFilter>();
		gamepadFilters.add(new DeadzoneFilter(0.1));
		gamepadFilters.add(new PowerFilter(1));
		GamepadFilterSet driveGamepadFilterSet = new GamepadFilterSet(gamepadFilters);
		gamepad0 = new FilteredGamepad(0, driveGamepadFilterSet);
		
		gamepad0.leftBumper().whenPressed(new SetClawCommand(Claw.ClawState.CLAMP_DOWN_OPEN));
	    gamepad0.leftTrigger().whenPressed(new SetClawCommand(Claw.ClawState.CLAMP_DOWN_CLOSE));
	    gamepad0.rightBumper().whenPressed(new SetClawCommand(Claw.ClawState.CLAMP_UP_CLOSE));
	    gamepad0.rightTrigger().whenPressed(new SetLowGearCommand(true));
	    gamepad0.rightTrigger().whenReleased(new SetLowGearCommand(false));
	    
	    gamepad0.dPadUp().whenPressed(new SetDrivetrainCommand(MANUAL_MOVEMENT, 0.0, 0.0));
	    gamepad0.dPadUp().whenReleased(new SetDrivetrainCommand(0.0, 0.0, 0.0));
	    gamepad0.dPadRight().whenPressed(new SetDrivetrainCommand(0.0, MANUAL_MOVEMENT, 0.0));
	    gamepad0.dPadRight().whenReleased(new SetDrivetrainCommand(0.0, 0.0, 0.0));
	    gamepad0.dPadDown().whenPressed(new SetDrivetrainCommand(-MANUAL_MOVEMENT, 0.0, 0.0));
	    gamepad0.dPadDown().whenReleased(new SetDrivetrainCommand(0.0, 0.0, 0.0));
	    gamepad0.dPadLeft().whenPressed(new SetDrivetrainCommand(0.0, -MANUAL_MOVEMENT, 0.0));
	    gamepad0.dPadLeft().whenReleased(new SetDrivetrainCommand(0.0, 0.0, 0.0));
	    gamepad0.diamondLeft().whenPressed(new SetDrivetrainCommand(0.0, 0.0, -MANUAL_MOVEMENT));
	    gamepad0.diamondLeft().whenReleased(new SetDrivetrainCommand(0.0, 0.0, -0.0));
	    gamepad0.diamondRight().whenPressed(new SetDrivetrainCommand(0.0, 0.0, MANUAL_MOVEMENT));
	    gamepad0.diamondLeft().whenReleased(new SetDrivetrainCommand(0.0, 0.0, 0.0));
	    
		//Elevator control gamepad
		List<GamepadFilter> filters = new ArrayList<GamepadFilter>();
		filters.add(new DeadzoneFilter(0.1));
		filters.add(new PowerFilter(1));
		GamepadFilterSet elevatorGamepadFilterSet = new GamepadFilterSet(filters);
		gamepad1 = new FilteredGamepad(1, elevatorGamepadFilterSet);

		gamepad1.diamondUp().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.BOTTOM));
		gamepad1.diamondRight().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.PICKUP));
		gamepad1.diamondDown().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.ONE_TOTE));
		gamepad1.diamondLeft().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.TWO_TOTES));
		gamepad1.rightBumper().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.THREE_TOTES));
		gamepad1.rightTrigger().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.TOP));

	}

	/**
	 * Returns the proper instance of Controls. This method creates a new
	 * Controls object the first time it is called and returns that object for
	 * each subsequent call.
	 *
	 * @return The current instance of Controls.
	 */
	public static Controls getInstance() {
		if (instance == null) {
			instance = new Controls();
		}
		return instance;
	}
}
