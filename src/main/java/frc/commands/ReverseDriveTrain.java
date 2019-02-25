package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ReverseDriveTrain extends InstantCommand {
    @Override
    protected void initialize() {
        Robot.driveTrain.reverseDriveTrain(!Robot.driveTrain.reversed());
    }
}