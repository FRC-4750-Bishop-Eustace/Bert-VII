package frc.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PositionArm extends Command {

    // The current target count
    double onTargetCount = 0;
    // The max target count
    double maxTargetCount = 7.5;
    // The target (in inches) we want to get to
    int target;
    Timer timer = new Timer();

    public PositionArm(int counts) {
        target = counts;
        requires(Robot.arm);
    }

    @Override
    protected void initialize() {
        Robot.arm.setArmPosition(target); // Start position control
        timer.reset();
        timer.start();
    }

    @Override
    protected void end() {
        Robot.arm.stop(); // Stop motors
    }

    @Override
    protected boolean isFinished() {
        if (Robot.arm.isOnTarget(target) || timer.get() > 2) { // If we are on target
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