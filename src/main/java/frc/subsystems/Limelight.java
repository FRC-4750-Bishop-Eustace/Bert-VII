package frc.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This class handles the interfacing for the limelight camera
 */
public class Limelight extends Subsystem {

	// Create network table
	public NetworkTable table;

	public Limelight() {
		// Instantiate limelight network table
		table = NetworkTableInstance.getDefault().getTable("limelight");
	}

	/**
	 * Returns whether the target is in view or not
	 * 
	 * @return target is in view
	 */
	public boolean getHasTarget() {
		return table.getEntry("tv").getDouble(0) == 0 ? false : true;
	}

	/**
	 * Returns the xOffset from the target
	 * 
	 * @return xOffset from target
	 */
	public double getXOffset() {
		return table.getEntry("tx").getDouble(0);
	}

	/**
	 * Returns the yOffset from the target
	 * 
	 * @return yOffset from target
	 */
	public double getYOffset() {
		return table.getEntry("ty").getDouble(0);
	}

	/**
	 * Returns the area of the target
	 * 
	 * @return area of target
	 */
	public double getArea() {
		return table.getEntry("ta").getDouble(0);
	}

	/**
	 * Returns the skew of the target
	 * 
	 * @return skew of target
	 */
	public double getSkew() {
		return table.getEntry("ts").getDouble(0);
	}

	/**
	 * Returns the latency of the pipeline
	 * 
	 * @return latency of pipeline
	 */
	public double getLatency() {
		return table.getEntry("tl").getDouble(0);
	}

	/**
	 * Returns the length of the shortest side of the bounding box
	 * 
	 * @return length of short side
	 */
	public double getShort() {
		return table.getEntry("tshort").getDouble(0);
	}

	/**
	 * Returns the length of the longest side of the bounding box
	 * 
	 * @return length of long side
	 */
	public double getLong() {
		return table.getEntry("tlong").getDouble(0);
	}

	/**
	 * Returns the length of the horizontal side of the bounding box
	 * 
	 * @return length of horizontal side
	 */
	public double getHorizontal() {
		return table.getEntry("thor").getDouble(0);
	}

	/**
	 * Returns the length of the vertical side of the bounding box
	 * 
	 * @return length of vertical side
	 */
	public double getVertical() {
		return table.getEntry("tvert").getDouble(0);
	}

	/**
	 * Returns the current pipeline index
	 * 
	 * @return length of vertical side
	 */
	public double getPipeline() {
		return table.getEntry("getpipe").getDouble(0);
	}

	/**
	 * Returns the real-world translation of the camera from the target { x, y, z,
	 * pitch, yaw, roll }
	 * 
	 * @return { x, y, z, pitch, yaw, roll }
	 */
	public Number[] getTranslation() {
		Number[] def = { 0, 0, 0, 0, 0, 0 };
		return table.getEntry("camtran").getNumberArray(def);
	}

	/**
	 * Returns the LED mode
	 * 
	 * @return state of the LED
	 */
	public double getLEDMode() {
		return table.getEntry("ledMode").getDouble(0);
	}

	/**
	 * Sets the LED mode
	 * 
	 * @param on state of the LED
	 */
	public void setLEDMode(boolean on) {
		table.getEntry("ledMode").setDouble(on ? 3 : 1);
	}

	/**
	 * Sets the LED mode to pipeline
	 * 
	 */
	public void resetLEDMode() {
		table.getEntry("ledMode").setDouble(0);
	}

	/**
	 * Toggles the camera mode
	 * 
	 */
	public void toggleCameraMode() {
		table.getEntry("camMode").setDouble(table.getEntry("camMode").getDouble(0) == 0 ? 1 : 0);
	}

	/**
	 * Set camera mode to vision or camera mode
	 * 
	 */
	public void setCameraMode(String mode) {
		table.getEntry("camMode").setDouble(mode.equalsIgnoreCase("camera") ? 1 : 0);
	}

	/**
	 * Sets the current pipeline
	 * 
	 * @param pipeline pipeline index
	 */
	public void setPipeline(double pipeline) {
		table.getEntry("pipeline").setDouble(pipeline);
	}

	/**
	 * Toggles the stream mode
	 * 
	 */
	public void toggleStreamMode() {
		table.getEntry("stream")
				.setDouble(table.getEntry("stream").getDouble(0) == 2 ? 0 : table.getEntry("stream").getDouble(0) + 1);
	}

	/**
	 * Sets the stream mode
	 * 
	 * @param mode stream mode
	 */
	public void setStreamMode(String mode) {
		table.getEntry("stream").setDouble(mode.equalsIgnoreCase("double") ? 0 : mode.equalsIgnoreCase("main") ? 1 : 2);
	}

	/**
	 * Toggles snapshot creation
	 * 
	 */
	public void toggleSnapshots() {
		table.getEntry("snapshot").setDouble(table.getEntry("snapshot").getDouble(0) == 0 ? 1 : 0);
	}

	/**
	 * Configures the Limelight for forward driving
	 */
	public void forward() {
		setStreamMode("main");
	}

	/**
	 * Configures the Limelight for reverse driving
	 */
	public void reverse() {
		setStreamMode("secondary");
	}

	@Override
	public void initDefaultCommand() {
		// No default command
	}
}