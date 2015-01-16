package com.edinarobotics.zebruh;

import com.edinarobotics.zebruh.subsystems.Claw;
import com.edinarobotics.zebruh.subsystems.Drivetrain;
import com.edinarobotics.zebruh.subsystems.Elevator;
import com.edinarobotics.zebruh.subsystems.HorizontalStrafe;
import com.edinarobotics.zebruh.subsystems.RotationDrive;
import com.edinarobotics.zebruh.subsystems.VerticalStrafe;

public class Components {
	private static Components instance;

	public Drivetrain drivetrain;
	public RotationDrive rotationDrive;
	public HorizontalStrafe horizontalStrafe;
	public VerticalStrafe verticalStrafe;
	public Elevator elevator;
	public Claw claw;
	
	// PWM Constants
		// Drivetrain Constants
		private static final int TOP_LEFT_CANTALON = 4;
		private static final int TOP_RIGHT_CANTALON = 6;
		private static final int BOTTOM_LEFT_CANTALON = 3;
		private static final int BOTTOM_RIGHT_CANTALON = 1;
		private static final int MIDDLE_TOP_CANTALON = 5;
		private static final int MIDDLE_BOTTOM_CANTALON = 2;
		// End Drivetrain Constants
		
		//Elevator Constants
		private static final int ELEVATOR_CANTALON1 = 12;
		private static final int ELEVATOR_CANTALON2 = 13;
		private static final int ELEVATOR_ENCODER_CHANNEL_A = 14;
		private static final int ELEVATOR_ENCODER_CHANNEL_B = 199;
		//End Elevator Constant
		
		//Claw Constants
		private static final int CLAW_SOLENOID_CHANNEL_A = 15;
		private static final int CLAW_SOLENOID_CHANNEL_B = 16;
		private static final int ROTATE_SOLENOID_CHANNEL_A = 17;
		private static final int ROTATE_SOLENOID_CHANNEL_B = 18;
		
		
		//DOI Constants
		private static final int UPPER_ELEVATOR_SWTICH = 19;
		private static final int LOWER_ELEVATOR_SWTICH = 100;
		//End DOI Constants
		
	// End PWM Constants

	private Components() {
		this.drivetrain = new Drivetrain(TOP_LEFT_CANTALON, TOP_RIGHT_CANTALON,
				BOTTOM_LEFT_CANTALON, BOTTOM_RIGHT_CANTALON,
				MIDDLE_TOP_CANTALON, MIDDLE_BOTTOM_CANTALON);
		this.rotationDrive = new RotationDrive(drivetrain);
		this.horizontalStrafe = new HorizontalStrafe(drivetrain);
		this.verticalStrafe = new VerticalStrafe(drivetrain);
		//this.elevator = new Elevator(ELEVATOR_CANTALON1, ELEVATOR_CANTALON2, ELEVATOR_ENCODER_CHANNEL_A,
		//		ELEVATOR_ENCODER_CHANNEL_B, UPPER_ELEVATOR_SWTICH, LOWER_ELEVATOR_SWTICH);
		//this.claw = new Claw(CLAW_SOLENOID_CHANNEL_A, CLAW_SOLENOID_CHANNEL_B, ROTATE_SOLENOID_CHANNEL_A, ROTATE_SOLENOID_CHANNEL_B);
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
