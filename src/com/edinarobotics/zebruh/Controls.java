package com.edinarobotics.zebruh;

import java.util.Vector;

import com.edinarobotics.utils.gamepad.FilteredGamepad;
import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.gamepad.gamepadfilters.DeadzoneFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilterSet;
import com.edinarobotics.utils.gamepad.gamepadfilters.PowerFilter;
import com.edinarobotics.zebruh.commands.RunElevatorManualCommand;
import com.edinarobotics.zebruh.commands.RunElevatorToLevelCommand;
import com.edinarobotics.zebruh.commands.SetClawClampCommand;
import com.edinarobotics.zebruh.commands.SetClawRotateCommand;
import com.edinarobotics.zebruh.subsystems.Elevator;
import com.edinarobotics.zebruh.subsystems.Claw.SolenoidState;

public class Controls {
	private static Controls instance;

	public final Gamepad gamepad;
	public final Gamepad gamepad1;

	private static final double MANUAL_SPEED = .25;

	private Controls() {
		// Drivetrain control
		Vector gamepadFilters = new Vector();
		gamepadFilters.add(new DeadzoneFilter(0.1));
		gamepadFilters.add(new PowerFilter(2));
		GamepadFilterSet driveGamepadFilterSet = new GamepadFilterSet(gamepadFilters);
		GamepadFilterSet something = new GamepadFilterSet(gamepadFilters);
		gamepad = new FilteredGamepad(1, driveGamepadFilterSet);
		
		gamepad.diamondUp().whenPressed(new SetClawRotateCommand(SolenoidState.ROTATE_UP));
		gamepad.diamondDown().whenReleased(new SetClawRotateCommand(SolenoidState.ROTATE_DOWN));
		gamepad.diamondRight().whenReleased(new SetClawClampCommand(SolenoidState.CLAMP_OPEN));
		gamepad.diamondLeft().whenReleased(new SetClawClampCommand(SolenoidState.CLAMP_CLOSE));
		
		
		
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

		gamepad1.leftBumper().whenPressed(new RunElevatorManualCommand(MANUAL_SPEED));
		gamepad1.leftBumper().whenReleased(new RunElevatorManualCommand(0.0, true));
		gamepad1.leftTrigger().whenPressed(new RunElevatorManualCommand(-MANUAL_SPEED));
		gamepad1.leftTrigger().whenReleased(new RunElevatorManualCommand(0.0, false));
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
