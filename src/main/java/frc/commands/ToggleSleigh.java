package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleSleigh extends InstantCommand {

    public ToggleSleigh() {
        requires(Robot.sleigh);
    }

    @Override
    protected void initialize() {
        Robot.sleigh.toggle();
    }
}