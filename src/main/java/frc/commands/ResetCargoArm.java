package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ResetCargoArm extends Command {

    boolean finished = false;
    double speed;

    public ResetCargoArm(double _speed) {
        requires(Robot.cargoArm);
        speed = _speed;
    }

    @Override
    protected void initialize() {
        finished = false;
    }

    @Override
    protected void execute() {
        if (!Robot.cargoArm.getLimit()) {
            Robot.cargoArm.run(speed);
        } else {
            Robot.cargoArm.stop();
            Robot.cargoArm.resetArm();
            finished = true;
        }
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

}