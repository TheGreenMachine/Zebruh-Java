package com.edinarobotics.zebruh;

import java.util.Vector;

import com.edinarobotics.utils.gamepad.FilteredGamepad;
import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.gamepad.gamepadfilters.DeadzoneFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilterSet;
import com.edinarobotics.utils.gamepad.gamepadfilters.PowerFilter;

public class Controls {
	private static Controls instance;
	
	public final Gamepad gamepad;
	
	 private Controls() {        
	        Vector gamepadFilters = new Vector();
	        gamepadFilters.addElement(new DeadzoneFilter(0.1));
	        gamepadFilters.addElement(new PowerFilter(2));
	        GamepadFilterSet shootGamepadFilterSet = new GamepadFilterSet(gamepadFilters);
	        gamepad = new FilteredGamepad(1, shootGamepadFilterSet); 
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
