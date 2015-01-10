package com.edinarobotics.zebruh.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

public class HRobotDrive extends RobotDrive {

	private CANTalon middleBottom, middleTop;
	
	public HRobotDrive(SpeedController frontLeftMotor,
			SpeedController rearLeftMotor, SpeedController frontRightMotor,
			SpeedController rearRightMotor, int middleBottomMotor,
			int middleTopMotor) {
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		middleBottom = new CANTalon(middleBottomMotor);
		middleTop = new CANTalon(middleTopMotor);
		middleBottom.changeControlMode(ControlMode.PercentVbus);
		middleBottom.changeControlMode(ControlMode.PercentVbus);
	}
	
	public void hDrive(double verticalStrafe, double horizontalStrafe, double rotation) {
		arcadeDrive(verticalStrafe, rotation);
		middleBottom.set(horizontalStrafe);
		middleTop.set(horizontalStrafe);
	}

}
