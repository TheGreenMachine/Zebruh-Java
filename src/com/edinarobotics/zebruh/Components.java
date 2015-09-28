package com.edinarobotics.zebruh;

import com.edinarobotics.zebruh.subsystems.Arms;
import com.edinarobotics.zebruh.subsystems.Claw;
import com.edinarobotics.zebruh.subsystems.Drivetrain;
import com.edinarobotics.zebruh.subsystems.Elevator;
import edu.wpi.first.wpilibj.Compressor;

public class Components {
	private static Components instance;

	public Drivetrain drivetrain;
	public Claw claw;
	public Compressor compressor;
	public Arms arms;
	public Elevator elevator;
	
	// CAN Constants
		// Drivetrain Constants
		private final int TOP_LEFT_CANTALON = 3;
		private final int TOP_RIGHT_CANTALON = 1;
		private final int BOTTOM_LEFT_CANTALON = 4;
		private final int BOTTOM_RIGHT_CANTALON = 6;
		private final int MIDDLE_TOP_CANTALON = 2;
		private final int MIDDLE_BOTTOM_CANTALON = 5;
		// End Drivetrain Constants
		
		//Elevator Constants
		private final int ELEVATOR_CANTALON1 = 8;
		private final int ELEVATOR_CANTALON2 = 7;
		
		private final int LIMIT_SWITCH_BOTTOM = 0;
		private final int LIMIT_SWITCH_TOP = 3;
		//End Elevator Constant
		
		//Claw Constants
		private final int CLAW_SOLENOID_CHANNEL_A = 0;
		private final int CLAW_SOLENOID_CHANNEL_B = 1;
		
		private final int ROTATE_SOLENOID_CHANNEL_A = 3;
		private final int ROTATE_SOLENOID_CHANNEL_B = 2;
		
		private final int SINGLE_SOLENOID_CHANNEL = 4;
		//End Claw Constants
		
		//Arms Constants
		private final int ARMS_LEFT_SOLENOID_CHANNEL = 5;
		private final int ARMS_RIGHT_SOLENOID_CHANNEL = 6;
		//End Arms Constants
		
		private final int PCM_NODE_ID = 10;
		
	// End CAN Constants

	private Components() {
		drivetrain = new Drivetrain(TOP_LEFT_CANTALON, TOP_RIGHT_CANTALON,
				BOTTOM_LEFT_CANTALON, BOTTOM_RIGHT_CANTALON,
				MIDDLE_TOP_CANTALON, MIDDLE_BOTTOM_CANTALON);
		
//		rotationDrive = new RotationDrive(drivetrain);
//		horizontalStrafe = new HorizontalStrafe(drivetrain);
//		verticalStrafe = new VerticalStrafe(drivetrain);
		
		claw = new Claw(CLAW_SOLENOID_CHANNEL_A, CLAW_SOLENOID_CHANNEL_B, ROTATE_SOLENOID_CHANNEL_A, ROTATE_SOLENOID_CHANNEL_B,
				SINGLE_SOLENOID_CHANNEL, PCM_NODE_ID);
		
		arms = new Arms(ARMS_LEFT_SOLENOID_CHANNEL, ARMS_RIGHT_SOLENOID_CHANNEL, PCM_NODE_ID);
		
		elevator = new Elevator(ELEVATOR_CANTALON1, ELEVATOR_CANTALON2, LIMIT_SWITCH_BOTTOM, LIMIT_SWITCH_TOP);
		
		elevator.setClaw(claw);
		claw.setElevator(elevator);
		
		compressor = new Compressor(PCM_NODE_ID);
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
