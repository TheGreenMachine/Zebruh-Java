package com.edinarobotics.zebruh;

import com.edinarobotics.zebruh.subsystems.Drivetrain;
import com.edinarobotics.zebruh.subsystems.HorizontalStrafe;
import com.edinarobotics.zebruh.subsystems.RotationDrive;
import com.edinarobotics.zebruh.subsystems.VerticalStrafe;

public class Components {
	private static Components instance;

	public Drivetrain drivetrain;
	public RotationDrive rotationDrive;
	public HorizontalStrafe horizontalStrafe;
	public VerticalStrafe verticalStrafe;

	// PWM Constants
		// Drivetrain Constants
		private static final int TOP_LEFT_CANTALON = 1;
		private static final int TOP_RIGHT_CANTALON = 1;
		private static final int BOTTOM_LEFT_CANTALON = 1;
		private static final int BOTTOM_RIGHT_CANTALON = 1;
		private static final int MIDDLE_TOP_CANTALON = 1;
		private static final int MIDDLE_BOTTOM_CANTALON = 1;
		// End Drivetrain Constants
	// End PWM Constants

	private Components() {
		this.drivetrain = new Drivetrain(TOP_LEFT_CANTALON, TOP_RIGHT_CANTALON,
				BOTTOM_LEFT_CANTALON, BOTTOM_RIGHT_CANTALON,
				MIDDLE_TOP_CANTALON, MIDDLE_BOTTOM_CANTALON);
		this.rotationDrive = new RotationDrive(drivetrain);
		this.horizontalStrafe = new HorizontalStrafe(drivetrain);
		this.verticalStrafe = new VerticalStrafe(drivetrain);
	}

	/**
	 * Returns a new instance of {@link Components}, creating one if null.
	 * 
	 * @return {@link Components}
	 */
	public static Components getInstance() {
		if (instance == null) {
			instance = new Components();
		}
		return instance;
	}

}
