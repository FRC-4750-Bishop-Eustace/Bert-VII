package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class RunPusher extends Command {

    public RunPusher() {
        requires(Robot.pusher);
    }

    @Override
    protected void execute() {
        if (OI.controller.getPOV() == 0) {
            Robot.pusher.extend();
        } else if (OI.controller.getPOV() == 180) {
            Robot.pusher.retract();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}