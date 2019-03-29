package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class TogglePusher extends InstantCommand {

    public TogglePusher() {
        requires(Robot.pusher);
    }

    @Override
    protected void initialize() {
        Robot.pusher.toggle();
    }
}