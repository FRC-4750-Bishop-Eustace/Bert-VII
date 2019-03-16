package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ResetCargoArm extends Command {

    boolean finished = false;

    public ResetCargoArm() {
        requires(Robot.cargoArm);
    }

    @Override
    protected void initialize() {
        finished = false;
    }

    @Override
    protected void execute() {
        if (!Robot.cargoArm.getLimit()) {
            Robot.cargoArm.run(-RobotMap.CARGO_ARM_SPEED);
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