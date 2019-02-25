package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ResetArm extends Command {

    boolean finished = false;

    public ResetArm() {
        requires(Robot.arm);
    }

    @Override
    protected void initialize() {
        finished = false;
    }

    @Override
    protected void execute() {
        if (!Robot.arm.getLimit()) {
            Robot.arm.run(-RobotMap.ARM_SPEED);
        } else {
            Robot.arm.stop();
            Robot.arm.resetArm();
            finished = true;
        }
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

}