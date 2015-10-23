
package com.edinarobotics.zebruh;

import java.util.ArrayList;
import java.util.List;

import com.edinarobotics.utils.gamepad.FilteredGamepad;
import com.edinarobotics.utils.gamepad.GamepadNew;
import com.edinarobotics.utils.gamepad.gamepadfilters.DeadzoneFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilterSet;
import com.edinarobotics.utils.gamepad.gamepadfilters.PowerFilter;
import com.edinarobotics.zebruh.commands.RunElevatorToLevelCommand;
import com.edinarobotics.zebruh.commands.SetArmsCommand;
import com.edinarobotics.zebruh.commands.SetClawCommand;
import com.edinarobotics.zebruh.commands.SetLowSpeedCommand;
import com.edinarobotics.zebruh.commands.SetMediumSpeedCommand;
import com.edinarobotics.zebruh.commands.ToggleArmsCommand;
import com.edinarobotics.zebruh.subsystems.Claw.ClampState;
import com.edinarobotics.zebruh.subsystems.Claw.ClawState;
import com.edinarobotics.zebruh.commands.SetClampCommand;
import com.edinarobotics.zebruh.subsystems.Arms;
import com.edinarobotics.zebruh.subsystems.Elevator;

public class Controls {
	private static Controls instance;

	public final GamepadNew gamepad0;
	public final GamepadNew gamepad1;

	private Controls() {
		// Drivetrain control
		List<GamepadFilter> gamepadFilters = new ArrayList<GamepadFilter>();
		gamepadFilters.add(new DeadzoneFilter(0.1));
		gamepadFilters.add(new PowerFilter(1));
		GamepadFilterSet driveGamepadFilterSet = new GamepadFilterSet(gamepadFilters);
		gamepad0 = new FilteredGamepad(0, driveGamepadFilterSet);
	    gamepad0.leftBumper().whenPressed(new SetLowSpeedCommand(true));
	    gamepad0.leftBumper().whenReleased(new SetLowSpeedCommand(false));
	    
	    gamepad0.rightBumper().whenPressed(new SetMediumSpeedCommand(true));
	    gamepad0.rightBumper().whenReleased(new SetMediumSpeedCommand(false));
	    
		//Elevator control gamepad
		List<GamepadFilter> filters = new ArrayList<GamepadFilter>();
		filters.add(new DeadzoneFilter(0.1));
		filters.add(new PowerFilter(1));
		GamepadFilterSet elevatorGamepadFilterSet = new GamepadFilterSet(filters);
		gamepad1 = new FilteredGamepad(1, elevatorGamepadFilterSet);

		gamepad1.diamondUp().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.BOTTOM));
		gamepad1.diamondRight().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.TWO_TOTES));
		//gamepad1.diamondDown().whenPressed(new SetClawCommand(ClawState.CLAW_VERTICAL));
		gamepad1.rightBumper().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.BIN_FOUR));
		gamepad1.rightTrigger().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.BIN_FIVE));
		gamepad1.leftTrigger().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.BIN_PICKUP_SIDE));
		
		gamepad1.dPadDown().whenPressed(new SetClawCommand(ClawState.CLAW_VERTICAL));
		gamepad1.dPadUp().whenPressed(new SetClawCommand(ClawState.CLAW_DOWN));
		gamepad1.leftBumper().whenPressed(new SetClawCommand(ClawState.CLAW_CARRY));
		gamepad1.dPadRight().whenPressed(new SetClampCommand(ClampState.CLAMP_CLOSED));
		gamepad1.dPadLeft().whenPressed(new SetClampCommand(ClampState.CLAMP_OPEN));
		
		gamepad1.middleLeft().whenPressed(new ToggleArmsCommand(false));
		gamepad1.middleRight().whenPressed(new ToggleArmsCommand(true));

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
