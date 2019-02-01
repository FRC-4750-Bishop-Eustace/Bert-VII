package frc.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * This class manages the wrist
 */
public class Wrist extends Subsystem {

    // Create solenoid
    public Solenoid wristPiston;

    public Wrist() {
        // Initialize solenoid
        // wristPiston = new Solenoid(RobotMap.WRIST_PISTON_ID);
    }

    /**
     * Toggles the wrist
     * 
     */
    public void toggle() {
        wristPiston.set(wristPiston.get() ? false : true);
    }

    /**
     * Lowers the wrist
     * 
     */
    public void lowerWrist() {
        wristPiston.set(true);
    }

    /**
     * Raises the wrist
     * 
     */
    public void raiseWrist() {
        wristPiston.set(false);
    }

    @Override
    protected void initDefaultCommand() {
        // No default command
    }

}