package frc.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Toggles the pistons on the hatch mechanism
 */
public class ToggleHatch extends Command {

    Timer timer = new Timer();
    boolean finished = false;

    public ToggleHatch() {
        // Require the hatch
        requires(Robot.hatch);
    }

    @Override
    protected void initialize() {
        // Toggle the hatch pistons
        Robot.hatch.toggle();
        timer.reset();
        timer.start();
        finished = false;
    }

    @Override
    protected void execute() {
        if (timer.get() >= 4) {
            Robot.hatch.toggle();
            finished = true;
        }
    }

    @Override
    protected void end() {
        timer.stop();
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

}