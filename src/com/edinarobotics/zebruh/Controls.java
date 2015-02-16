
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
import com.edinarobotics.zebruh.commands.SetLowGearCommand;
import com.edinarobotics.zebruh.commands.SetSingleSolenoidStateCommand;
import com.edinarobotics.zebruh.subsystems.Claw.ClawState;
import com.edinarobotics.zebruh.subsystems.Elevator;

public class Controls {
	private static Controls instance;

	public final GamepadNew gamepad0;
	public final GamepadNew gamepad1;
	
	public static final int MANUAL_TICKS_UP = -500;
	public static final int MANUAL_TICKS_DOWN = -250;
	
 final double MANUAL_MOVEMENT = 0.5;

	private Controls() {
		// Drivetrain control
		//hi eric

		List<GamepadFilter> gamepadFilters = new ArrayList<GamepadFilter>();
		gamepadFilters.add(new DeadzoneFilter(0.1));
		gamepadFilters.add(new PowerFilter(1));
		GamepadFilterSet driveGamepadFilterSet = new GamepadFilterSet(gamepadFilters);
		gamepad0 = new FilteredGamepad(0, driveGamepadFilterSet);
	    gamepad0.rightTrigger().whenPressed(new SetLowGearCommand(true));
	    gamepad0.rightTrigger().whenReleased(new SetLowGearCommand(false));
	    
	    
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
		
		
		gamepad1.dPadUp().whenPressed(new SetClawCommand(ClawState.CLAMP_DOWN_OPEN));
		gamepad1.dPadDown().whenPressed(new SetClawCommand(ClawState.CLAMP_UP_CLOSE));
		gamepad1.dPadRight().whenPressed(new SetClawCommand(ClawState.CLAMP_DOWN_CLOSE));
		gamepad1.dPadLeft().whenPressed(new SetClawCommand(ClawState.CLAMP_DOWN_OPEN));
		
//		gamepad1.leftBumper().whenPressed(new SetSingleSolenoidStateCommand(true));
		

		gamepad1.leftBumper().whileHeld(new RunElevatorManualCommand(MANUAL_TICKS_UP, true));
		gamepad1.leftTrigger().whileHeld(new RunElevatorManualCommand(-MANUAL_TICKS_DOWN, false));

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
