package com.edinarobotics.zebruh;

import java.util.Vector;

import com.edinarobotics.utils.gamepad.FilteredGamepad;
import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.gamepad.gamepadfilters.DeadzoneFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilterSet;
import com.edinarobotics.utils.gamepad.gamepadfilters.PowerFilter;
import com.edinarobotics.zebruh.commands.RunElevatorManualCommand;
import com.edinarobotics.zebruh.commands.RunElevatorToLevelCommand;
import com.edinarobotics.zebruh.commands.SetClawCommand;
import com.edinarobotics.zebruh.subsystems.Claw;
import com.edinarobotics.zebruh.subsystems.Elevator;

public class Controls {
	private static Controls instance;

	public final Gamepad gamepad;
	public final Gamepad gamepad1;

	private static final int MANUAL_TICKS = -250;

	private Controls() {
		// Drivetrain control
		Vector gamepadFilters = new Vector();
		gamepadFilters.add(new DeadzoneFilter(0.1));
		gamepadFilters.add(new PowerFilter(2));
		GamepadFilterSet driveGamepadFilterSet = new GamepadFilterSet(gamepadFilters);
		GamepadFilterSet something = new GamepadFilterSet(gamepadFilters);
		gamepad = new FilteredGamepad(1, driveGamepadFilterSet);
		
		gamepad.leftBumper().whenPressed(new SetClawCommand(Claw.ClawState.CLAMP_DOWN_OPEN));
	    gamepad.leftTrigger().whenPressed(new SetClawCommand(Claw.ClawState.CLAMP_DOWN_CLOSE));
	    gamepad.rightBumper().whenPressed(new SetClawCommand(Claw.ClawState.CLAMP_UP_CLOSE));
		
		
		// Elevator control gamepad
		Vector elevatorGamepadFilters = new Vector();
		elevatorGamepadFilters.add(new DeadzoneFilter(0.1));
		elevatorGamepadFilters.add(new PowerFilter(2));
		GamepadFilterSet elevatorGamepadFilterSet = new GamepadFilterSet(elevatorGamepadFilters);
		gamepad1 = new FilteredGamepad(2, elevatorGamepadFilterSet);

		gamepad1.diamondUp().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.BOTTOM));
		gamepad1.diamondRight().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.PICKUP));
		gamepad1.diamondDown().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.ONE_TOTE));
		gamepad1.diamondLeft().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.TWO_TOTES));
		gamepad1.rightBumper().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.THREE_TOTES));
		gamepad1.rightTrigger().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.TOP));

		gamepad1.leftBumper().whileHeld(new RunElevatorManualCommand(MANUAL_TICKS, true));
		gamepad1.leftTrigger().whileHeld(new RunElevatorManualCommand(-MANUAL_TICKS, false));
		
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
