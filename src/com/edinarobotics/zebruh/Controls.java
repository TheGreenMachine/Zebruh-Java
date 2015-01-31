package com.edinarobotics.zebruh;

import java.util.Vector;

import com.edinarobotics.utils.gamepad.FilteredGamepad;
import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.gamepad.gamepadfilters.DeadzoneFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilterSet;
import com.edinarobotics.utils.gamepad.gamepadfilters.PowerFilter;
import com.edinarobotics.zebruh.commands.RunElevatorManualCommand;
import com.edinarobotics.zebruh.commands.RunElevatorToLevelCommand;
import com.edinarobotics.zebruh.subsystems.Elevator;
import com.edinarobotics.zebruh.subsystems.Elevator.ElevatorLevel;

public class Controls {
	private static Controls instance;
	
	public final Gamepad gamepad;
	public final Gamepad gamepad1;
	
	private static final double MANUAL_SPEED = .25;
	
	 private Controls() {        
	        Vector gamepadFilters = new Vector();
	        gamepadFilters.add(new DeadzoneFilter(0.1));
	        gamepadFilters.add(new PowerFilter(2));
	        GamepadFilterSet driveGamepadFilterSet = new GamepadFilterSet(gamepadFilters);
	        GamepadFilterSet something = new GamepadFilterSet(gamepadFilters);
	        //Drivetrain control
	        gamepad = new FilteredGamepad(1, driveGamepadFilterSet); 
	        
	        //Elevator control gamepad
	        
	        Vector elevatorGamepadFilters = new Vector();
	        elevatorGamepadFilters.add(new DeadzoneFilter(0.1));
	        elevatorGamepadFilters.add(new PowerFilter(2));
	        GamepadFilterSet elevatorGamepadFilterSet = new GamepadFilterSet(elevatorGamepadFilters);
	        gamepad1 = new FilteredGamepad(2,elevatorGamepadFilterSet);
	        
		    gamepad1.diamondUp().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.A));
		    gamepad1.diamondRight().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.B));
		    gamepad1.diamondDown().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.C));
		    gamepad1.diamondLeft().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.D));
		    gamepad1.rightBumper().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.E));
		    gamepad1.rightTrigger().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.F));
		    gamepad1.leftBumper().whenPressed(new RunElevatorToLevelCommand(Elevator.ElevatorLevel.G));
		    
		    gamepad.diamondUp().whenPressed(new RunElevatorManualCommand(0.25));
		    gamepad.diamondUp().whenReleased(new RunElevatorManualCommand(0.0));
		    gamepad.diamondDown().whenPressed(new RunElevatorManualCommand(-0.25));
		    gamepad.diamondDown().whenReleased(new RunElevatorManualCommand(0.0));
		    
		    
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
