package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

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
        if (OI.controlStick.getY() == -1) { // If the joystick is going forward
            // Run the arm down
            Robot.arm.run(RobotMap.ARM_SPEED);
        } else if (OI.controlStick.getY() == 1) { // If the joystick is going backwards
            // Run the arm up
            Robot.arm.run(-RobotMap.ARM_SPEED);
        } else { // Otherwise
            // Stop the arm
            Robot.arm.stop();
        }
    }

    @Override
    protected boolean isFinished() {
        // Never finish
        return false;
    }

}