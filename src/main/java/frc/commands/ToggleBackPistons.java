package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleBackPistons extends InstantCommand {

    public ToggleBackPistons() {
        requires(Robot.lifter);
    }

    @Override
    protected void initialize() {
        Robot.lifter.toggleBack();
    }
}