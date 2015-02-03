package com.edinarobotics.zebruh.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {
	
	public AutonomousCommand(AutonomousCommand.AutoMode autoMode) {
		switch(autoMode) {
			case BIN:
				
				break;
			case TOTE:
				break;
			case LANDFILL:
				break;
			case PREPARE:
				break;
		}
	}		
	
	
	enum AutoMode {
		BIN(),
		TOTE(),
		LANDFILL(),
		PREPARE();
		
		AutoMode() {
			
		}
	}
	
}

