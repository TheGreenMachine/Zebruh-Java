package com.edinarobotics.zebruh.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;

public class HRobotDrive extends RobotDrive {

	private CANTalon middleBottom, middleTop;
	
	public HRobotDrive(int frontLeftMotor,
			int rearLeftMotor, int frontRightMotor,
			int rearRightMotor, int middleBottomMotor,
			int middleTopMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		middleBottom = new CANTalon(middleBottomMotor);
		middleTop = new CANTalon(middleTopMotor);
	}
	
	public void hDrive(double verticalStrafe, double horizontalStrafe, double rotation) {
		arcadeDrive(verticalStrafe, rotation);
		middleBottom.set(horizontalStrafe);
		middleTop.set(horizontalStrafe);
	}

}
