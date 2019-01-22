package frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This class is the interface to the digital sensor hardware
 */
public class ObjectSensor extends Subsystem {

	// Sensor
	public DigitalInput sensor;

	public ObjectSensor(int digitalPort) {
		// Create an digital sensor with digitalPort
		sensor = new DigitalInput(digitalPort);
	}

	/**
	 * Use this to get whether the sensor is true or false
	 * 
	 * @return true or false
	 */
	public boolean get() {
		return !sensor.get();
	}

	@Override
	public void initDefaultCommand() {
		// No default command
	}
}