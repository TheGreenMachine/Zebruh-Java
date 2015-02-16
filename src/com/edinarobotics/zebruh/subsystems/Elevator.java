package com.edinarobotics.zebruh.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;
import com.edinarobotics.zebruh.Controls;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class Elevator extends Subsystem1816 {

	private CANTalon talonA, talonB;
	private DigitalInput ls1, ls2, ls3, ls4;

	private Claw claw;

	private final double P_AUTO_UP = 0.8;
	private final double I_AUTO_UP = 0.00001;
	private final double D_AUTO_UP = 0.0;

	private final double P_AUTO_DOWN = 1.6;
	private final double I_AUTO_DOWN = 0.00001;
	private final double D_AUTO_DOWN = 100.0;

	private final double P_MANUAL_UP = 1.0;
	private final double D_MANUAL_UP = 0.0;
	private final double I_MANUAL_UP = 0.0;

	private int talonAChannel;

	public static final int CLAW_UP_MAXIMUM_HEIGHT = -6500;

	private final int MAXIMUM_TICKS = -7400;
	private final int MANUAL_TICKS_UP = -700;
	private final int MANUAL_TICKS_DOWN = -200;
	
	private Elevator.ElevatorLevel level;
	private boolean override, downAuto, downManual;
	private int currentTicks;

	private boolean hasHitL3 = false;
	private boolean hasHitL4 = false;

	private int stopEncoderPoint = 0;

	public Elevator(int talonAChannel, int talonBChannel, int ls1Channel,
			int ls2Channel, int ls3Channel, int ls4Channel) {
		talonA = new CANTalon(talonAChannel);
		talonB = new CANTalon(talonBChannel);
		ls1 = new DigitalInput(ls1Channel);
		ls2 = new DigitalInput(ls2Channel);
		ls3 = new DigitalInput(ls3Channel);
		ls4 = new DigitalInput(ls4Channel);
		this.talonAChannel = talonAChannel;
		talonA.changeControlMode(CANTalon.ControlMode.Position);
		talonA.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		talonA.setPID(P_AUTO_UP, I_AUTO_UP, D_AUTO_UP);
		talonB.changeControlMode(CANTalon.ControlMode.Follower);
		// talonA.setVoltageRampRate(RAMP_RATE);
		// talonB.setVoltageRampRate(RAMP_RATE);
		override = false;
		level = ElevatorLevel.BOTTOM;
		downAuto = true;
	}

	public enum ElevatorLevel {
		BOTTOM(0), PICKUP(-500), ONE_TOTE(-2400), TWO_TOTES(-3850), THREE_TOTES(
				-6400), TOP(-7000), BIN_PICKUP_AUTO(-1500);

		public int ticks;

		ElevatorLevel(int ticks) {
			this.ticks = ticks;
		}

		public static void setBottom(int ticks) {
			BOTTOM.ticks = ticks;
		}
	}

	public void printInformation() {
		System.out.println(getLS1() + "                 " + getLS4());
		System.out.println("Actual: " + getEncoderTicks() + "         Target: "
				+ currentTicks + " Level ticks: " + level.ticks);
	}

	public int getEncoderTicks() {
		return talonA.getEncPosition();
	}

	public boolean getLS1() {
		return !ls1.get();
	}

	public boolean getLS2() {
		return !ls2.get();
	}

	public boolean getLS3() {
		return !ls3.get();
	}

	public boolean getLS4() {
		return !ls4.get();
	}

	public void setElevatorState(ElevatorLevel state) {
		setOverride(false);
		if (getEncoderTicks() < state.ticks) {
			downAuto = true;
			talonA.setPID(P_AUTO_DOWN, I_AUTO_DOWN, D_AUTO_DOWN);
		} else {
			downAuto = false;
			talonA.setPID(P_AUTO_UP, I_AUTO_UP, D_AUTO_UP);
		}
		level = state;
		update();
	}

	public void setClaw(Claw claw) {
		this.claw = claw;
	}

	public Claw getClaw() {
		return claw;
	}

	public Elevator.ElevatorLevel getLevel() {
		return level;
	}

	public boolean isDownAutoDone() {
		// Remember, we are working with negative ticks. So everything is
		// backwards.
		return talonA.getEncPosition() > level.ticks;
	}

	
	public void setManualTicks(double value, boolean isUp) {
		int multiplier;
		if ((!getLS1() && !getLS4())
				|| ((getLS1() && isUp) || (getLS4() && !isUp))) {
			
			if (isUp) {
				downManual = false;
				multiplier = MANUAL_TICKS_UP;
				talonA.setPID(P_MANUAL_UP, I_MANUAL_UP, D_MANUAL_UP);
			} else {
				downManual = true;
				multiplier = MANUAL_TICKS_DOWN;
				talonA.setPID(P_AUTO_DOWN, I_AUTO_DOWN, D_AUTO_DOWN);
			}
			
			if(claw.getRotateState() == Value.kReverse && currentTicks > CLAW_UP_MAXIMUM_HEIGHT) {
				currentTicks = (int) (talonA.getEncPosition() + (value * multiplier));
				if(currentTicks > CLAW_UP_MAXIMUM_HEIGHT)
					currentTicks = CLAW_UP_MAXIMUM_HEIGHT;
			}
			 
			if(claw.getRotateState() == Value.kForward && currentTicks > MAXIMUM_TICKS) {
				currentTicks = (int) (talonA.getEncPosition() + (value * multiplier));
				if(currentTicks > MAXIMUM_TICKS)
					currentTicks = MAXIMUM_TICKS;
			}
			
			if(currentTicks < ElevatorLevel.BOTTOM.ticks) {
				currentTicks = (int) (talonA.getEncPosition() + (value * multiplier));
				if(currentTicks > ElevatorLevel.BOTTOM.ticks)
					currentTicks = ElevatorLevel.BOTTOM.ticks;
			}
				
			update();
		}
	}

	public void setElevatorDown(int ticks) {
		setOverride(false);
		currentTicks = talonA.getEncPosition() + ticks;
		update();
	}

	public void setPosition(int ticks) {
		talonA.setPosition(ticks);
	}

	public void setOverride(boolean override) {
		this.override = override;
	}

	private void setTalons(int tick) {
		talonA.set(tick);
		talonB.set(talonAChannel);
	}

	@Override
	public void update() {
		System.out.println("Target: " + level.ticks + "      Current: "
				+ getEncoderTicks());
		// System.out.println("Limit 1: " + getLS1() + " Limit 4: " + getLS4());
		System.out.println("LimitSwitch4 not on");
		if (override) {
			if (currentTicks <= CLAW_UP_MAXIMUM_HEIGHT
					&& claw.getRotateState() == DoubleSolenoid.Value.kReverse
					&& !downManual) {
				setTalons(CLAW_UP_MAXIMUM_HEIGHT);
			} else if (getLS3() && !downManual
					&& claw.getRotateState() == DoubleSolenoid.Value.kReverse) {
				if (!hasHitL3) {
					stopEncoderPoint = getEncoderTicks();
					hasHitL3 = true;
				}
				setTalons(stopEncoderPoint);
			} else {
				if (!getLS4()) {
					setTalons(currentTicks);
				} else if (downManual) {
					setTalons(currentTicks);
				} else if (getLS4()) {
					setTalons(MAXIMUM_TICKS);
				} else if (talonA.getEncPosition() > ElevatorLevel.BOTTOM.ticks) {
					setTalons(ElevatorLevel.BOTTOM.ticks);
				}
			}
		} else {
			if (!downAuto) {
				if (level == ElevatorLevel.TOP
						&& claw.getRotateState() == DoubleSolenoid.Value.kReverse) {
					setTalons(ElevatorLevel.THREE_TOTES.ticks);
				} else {
					setTalons(level.ticks);
				}
			} else {
				setTalons(currentTicks);
			}
		}
	}

	public void setDefaultCommand(Command command) {
		if (getDefaultCommand() != null) {
			super.getDefaultCommand().cancel();
		}
		super.setDefaultCommand(command);
	}
}
