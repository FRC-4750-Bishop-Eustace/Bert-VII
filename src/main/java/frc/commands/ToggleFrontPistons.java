package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleFrontPistons extends InstantCommand {

    public ToggleFrontPistons() {
        requires(Robot.lifter);
    }

    @Override
    protected void initialize() {
        Robot.lifter.toggleFront();
    }
}