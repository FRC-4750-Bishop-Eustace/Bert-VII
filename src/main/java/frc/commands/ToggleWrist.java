package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Toggles the piston on the wrist mechanism
 */
public class ToggleWrist extends InstantCommand {

    public ToggleWrist() {
        // Require the wrist
        requires(Robot.wrist);
    }

    @Override
    protected void initialize() {
        // Toggle the wrist piston
        Robot.wrist.toggle();
    }

}