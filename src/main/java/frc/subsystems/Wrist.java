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
        wristPiston = new Solenoid(RobotMap.WRIST_PISTON_FOR_ID);
    }

    /**
     * Toggles the wrist
     *
     */
    public void toggle() {
        wristPiston.set(!wristPiston.get());
        // wristPiston.set(wristPiston.get() == Value.kForward ? Value.kReverse :
        // Value.kForward);
        // System.out.println(wristPiston.get());
    }

    /**
     * Lowers the wrist
     *
     */
    public void lowerWrist() {
        wristPiston.set(true);
        // wristPiston.set(Value.kForward);
    }

    /**
     * Raises the wrist
     *
     */
    public void raiseWrist() {
        wristPiston.set(false);
        // wristPiston.set(Value.kReverse);
    }

    /**
     * Returns the position of the wrist
     *
     * @return the position of the wrist
     */
    public boolean get() {
        return wristPiston.get();
        // return wristPiston.get() == Value.kForward ? true : false;
    }

    @Override
    protected void initDefaultCommand() {
        // No default command
    }

}