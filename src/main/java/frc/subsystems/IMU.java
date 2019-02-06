package frc.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This class is the interface to the IMU hardware
 * 
 */
public class IMU extends Subsystem {

	// Create IMU
	public AHRS ahrs;

	// Turning heading
	public double commandedHeading = 0.0;

	public IMU() {
		try {
			// Initialize IMU
			ahrs = new AHRS(SPI.Port.kMXP);
		} catch (Exception e) {
			System.out.println("IMU failed to connect");
		}
	}

	/**
	 * Returns the pitch
	 * 
	 * @return the degrees - is forward + is back
	 */
	public float getPitch() {
		return ahrs.getPitch();
	}

	/**
	 * Returns the angle
	 * 
	 * @return the degrees - is left + is right
	 */
	public float getAngle() {
		return (float) ahrs.getAngle();
	}

	/**
	 * Returns the current heading
	 * 
	 * @return the degrees - is left + is right
	 */
	public float getHeading() {
		return ahrs.getYaw();
	}

	/**
	 * Resets the IMU
	 * 
	 */
	public void reset() {
		ahrs.reset();
	}

	/**
	 * Sets the commanded heading
	 * 
	 */
	public void setCommandedHeading(double heading) {
		commandedHeading = heading;
	}

	/**
	 * Returns the commanded heading
	 * 
	 * @return commanded heading
	 */
	public double getCommandedHeading() {
		return commandedHeading;
	}

	@Override
	public void initDefaultCommand() {
		// No default command
	}
}