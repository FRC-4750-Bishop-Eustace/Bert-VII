package frc.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * This class manages the hatch mechanism
 * 
 */
public class Hatch extends Subsystem {

	// Create piston
	public DoubleSolenoid hatchPistons;

	public Hatch() {
        // Initialize piston to ports
        hatchPistons = new DoubleSolenoid(RobotMap.HATCH_FORWARD_ID, RobotMap.HATCH_REVERSE_ID);
    }

    /**
     * Toggles the hatch piston
     */
    public void toggle() {
        if(hatchPistons.get() == Value.kForward) {
            hatchPistons.set(Value.kReverse);
        }else if(hatchPistons.get() == Value.kReverse) {
            hatchPistons.set(Value.kForward);
        }
    }

	@Override
	public void initDefaultCommand() {
		
	}
}