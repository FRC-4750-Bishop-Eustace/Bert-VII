package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class TogglePincer extends InstantCommand {

    public TogglePincer() {
        requires(Robot.pincer);
    }

    @Override
    protected void initialize() {
        Robot.pincer.toggle();
    }
}