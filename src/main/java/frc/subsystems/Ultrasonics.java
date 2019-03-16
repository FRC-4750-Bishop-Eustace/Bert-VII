package frc.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * This class is the interface to the ultrasonic hardware
 */
public class Ultrasonics extends Subsystem {

	// Sensors
	public Ultrasonic ultrasonic;

	public Ultrasonics() {
		// Create an ultrasonic sensor with pingChannel = trigger, echoChannel = echo
		ultrasonic = new Ultrasonic(RobotMap.ULTRASONIC_TRIGGER, RobotMap.ULTRASONIC_ECHO);
		// Automatically send and receive pulses
		ultrasonic.setAutomaticMode(true);
	}

	/**
	 * Returns the range in inches
	 * 
	 * @return inches from object
	 */
	public double getInches() {
		return ultrasonic.getRangeInches();
	}

	/**
	 * Returns the range in feet
	 * 
	 * @return feet from object
	 */
	public double getFeet() {
		return ultrasonic.getRangeInches() / 12;
	}

	public boolean isInRangePlace() {
		return getInches() >= 15 && getInches() <= 19 ? true : false;
	}

	public boolean isInRangePickup() {
		return getInches() <= 15 ? true : false;
	}

	@Override
	protected void initDefaultCommand() {
		// No default command
	}
}