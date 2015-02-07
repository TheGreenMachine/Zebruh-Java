package com.edinarobotics.zebruh;

import com.edinarobotics.zebruh.subsystems.Claw;
import com.edinarobotics.zebruh.subsystems.Drivetrain;
import com.edinarobotics.zebruh.subsystems.Elevator;
import com.edinarobotics.zebruh.subsystems.HorizontalStrafe;
import com.edinarobotics.zebruh.subsystems.RotationDrive;
import com.edinarobotics.zebruh.subsystems.VerticalStrafe;

import edu.wpi.first.wpilibj.Compressor;

public class Components {
	private static Components instance;

	public Drivetrain drivetrain;
	public RotationDrive rotationDrive;
	public HorizontalStrafe horizontalStrafe;
	public VerticalStrafe verticalStrafe;
	public Elevator elevator;
	//public Claw claw;
	public Compressor compressor;
	
	// PWM Constants
		// Drivetrain Constants
		private static final int TOP_LEFT_CANTALON = 3;
		private static final int TOP_RIGHT_CANTALON = 1;
		private static final int BOTTOM_LEFT_CANTALON = 4;
		private static final int BOTTOM_RIGHT_CANTALON = 6;
		private static final int MIDDLE_TOP_CANTALON = 2;
		private static final int MIDDLE_BOTTOM_CANTALON = 5;
		// End Drivetrain Constants
		
		//Elevator Constants
		private static final int ELEVATOR_CANTALON1 = 8;
		private static final int ELEVATOR_CANTALON2 = 7;
		
		private static final int LIMIT_SWITCH_1 = 0;
		private static final int LIMIT_SWITCH_2 = 1;
		private static final int LIMIT_SWITCH_3 = 2;
		private static final int LIMIT_SWITCH_4 = 3;
		//End Elevator Constant
		
		//Claw Constants
		private static final int CLAW_SOLENOID_CHANNEL_A = 0;
		private static final int CLAW_SOLENOID_CHANNEL_B = 1;
		private static final int ROTATE_SOLENOID_CHANNEL_A = 3;
		private static final int ROTATE_SOLENOID_CHANNEL_B = 2;
		//End Claw Constants
		
		//Compressor
		private static final int COMPRESSOR_CHANNEL = 10;
		
	// End PWM Constants

	private Components() {
		drivetrain = new Drivetrain(TOP_LEFT_CANTALON, TOP_RIGHT_CANTALON,
				BOTTOM_LEFT_CANTALON, BOTTOM_RIGHT_CANTALON,
				MIDDLE_TOP_CANTALON, MIDDLE_BOTTOM_CANTALON);
		rotationDrive = new RotationDrive(drivetrain);
		horizontalStrafe = new HorizontalStrafe(drivetrain);
		verticalStrafe = new VerticalStrafe(drivetrain);
		elevator = new Elevator(ELEVATOR_CANTALON1, ELEVATOR_CANTALON2, 
				LIMIT_SWITCH_1, LIMIT_SWITCH_2, LIMIT_SWITCH_3, LIMIT_SWITCH_4);
		//claw = new Claw(CLAW_SOLENOID_CHANNEL_A, CLAW_SOLENOID_CHANNEL_B, ROTATE_SOLENOID_CHANNEL_A, ROTATE_SOLENOID_CHANNEL_B);
		compressor = new Compressor(COMPRESSOR_CHANNEL);
		compressor.start();
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
