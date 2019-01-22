package frc.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * This class manages the hatch mechanism
 * 
 */
public class Hatch extends Subsystem {

	// Create piston
	public Solenoid hatchPistons;

	public Hatch() {
        // Initialize piston to ports
        hatchPistons = new Solenoid(RobotMap.HATCH_PISTON_ID);
    }

    /**
     * Toggles the hatch piston
     */
    public void toggle() {
        if(hatchPistons.get()) {
            hatchPistons.set(false);
        }else {
            hatchPistons.set(true);
        }
    }

	@Override
	public void initDefaultCommand() {
		
	}
}