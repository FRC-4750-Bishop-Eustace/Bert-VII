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
     * Returns the current hatch piston state
     * 
     * @return hatch pistons state
     */
    public boolean getState() {
        return hatchPistons.get();
    }

    /**
     * Toggles the hatch piston
     */
    public void toggle() {
        hatchPistons.set(hatchPistons.get() ? false : true);
    }

    @Override
    public void initDefaultCommand() {
        // No default command
    }
}