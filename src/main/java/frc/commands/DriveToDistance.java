package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class DriveToDistance extends InstantCommand {

    @Override
    protected void initialize() {
        Robot.driveTrain.driveToDistance(4096 * 10);
    }

}