package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleLimelightMode extends InstantCommand {

    @Override
    protected void initialize() {
        Robot.limelight.resetLEDMode();
    }
}