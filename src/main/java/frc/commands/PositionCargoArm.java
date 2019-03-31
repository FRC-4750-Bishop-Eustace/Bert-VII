package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class PositionCargoArm extends Command {

    // The current target count
    double onTargetCount = 0;
    // The max target count
    double maxTargetCount = 7.5;
    // The target (in inches) we want to get to
    int target;

    public PositionCargoArm(int counts) {
        target = counts;
        requires(Robot.cargoArm);
    }

    @Override
    protected void initialize() {
        Robot.cargoArm.setArmPosition(target); // Start position control
    }

    @Override
    protected void end() {
        Robot.cargoArm.stop(); // Stop motors
    }

    @Override
    protected boolean isFinished() {
        if (Math.abs(OI.controller.getThrottle()) > 0.1) { // If we are on target
            // Add a target count
            onTargetCount++;
        } else { // Otherwise
            // Reset the target count
            onTargetCount = 0;
        }
        // We are finished once the target count hits the max target count
        return (onTargetCount > maxTargetCount);
    }

}