package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * Runs the arm using the joystick
 */
public class RunArm extends Command {

    public RunArm() {
        // Require the arm
        requires(Robot.arm);
    }

    @Override
    protected void execute() {
        Robot.arm.run(-OI.controller.getThrottle());
    }

    @Override
    protected boolean isFinished() {
        // Never finish
        return false;
    }

}