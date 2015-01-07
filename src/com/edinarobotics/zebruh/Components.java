package com.edinarobotics.zebruh;

import com.edinarobotics.zebruh.subsystems.Drivetrain;

public class Components {
	private static Components instance;
	
	public Drivetrain drivetrain;
	
	//PWM Constants
		//Drivetrain Constants
		private static final int TOP_LEFT_CANTALON = 1;
		private static final int TOP_RIGHT_CANTALON = 1;
		private static final int BOTTOM_LEFT_CANTALON = 1;
		private static final int BOTTOM_RIGHT_CANTALON = 1;
		private static final int MIDDLE_TOP_CANTALON = 1;
		private static final int MIDDLE_BOTTOM_CANTALON = 1;
		//End Drivetrain Constants
	//End PWM Constants
	
	
	
	/**
     * Returns a new instance of {@link Components}, creating one if null.
     * @return {@link Components}
     */
    public static Components getInstance() {
        if(instance == null){
            instance = new Components();
        }
        return instance;
    }

}
