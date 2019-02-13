package frc.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
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
        Timer timer = new Timer();
        timer.reset();
        hatchPistons.set(hatchPistons.get() ? false : true);
        timer.start();
        while (timer.get() < 0.5) {
        }
        hatchPistons.set(hatchPistons.get() ? false : true);
        timer.stop();
    }

    @Override
    public void initDefaultCommand() {
        // No default command
    }
}