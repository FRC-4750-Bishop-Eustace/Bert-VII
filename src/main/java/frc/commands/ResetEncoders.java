package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ResetEncoders extends InstantCommand {

    @Override
    protected void initialize() {
        Robot.driveTrain.resetEncoders();
    }
}