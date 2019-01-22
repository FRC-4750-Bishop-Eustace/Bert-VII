package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Toggles the pistons on the hatch mechanism
 */
public class ToggleHatch extends InstantCommand {

    public ToggleHatch() {
        // Require the hatch
        requires(Robot.hatch);
    }

    @Override
    protected void initialize() {
        // Toggle the hatch pistons
        Robot.hatch.toggle();
    }

}