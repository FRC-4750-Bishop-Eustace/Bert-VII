package frc.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class PositionCargoArm extends Command {

    // The current target count
    double onTargetCount = 0;
    // The max target count
    double maxTargetCount = 7.5;
    // The target (in inches) we want to get to
    int target;
    Timer timer = new Timer();

    public PositionCargoArm(int counts) {
        target = counts;
        requires(Robot.cargoArm);
    }

    @Override
    protected void initialize() {
        System.out.println("Initialize ran!");
        Robot.cargoArm.setArmPosition(target); // Start position control
        timer.reset();
        timer.start();
    }

    @Override
    protected void end() {
        System.out.println("PositionCargoArm ended!");
        Robot.cargoArm.stop(); // Stop motors
    }

    @Override
    protected boolean isFinished() {
        System.out.println("PositionCargoArm running!");
        System.out.println("OnTarget: " + Robot.cargoArm.isOnTarget(target));
        System.out.println("Timer: " + timer.get());
        if (Robot.cargoArm.isOnTarget(target) || timer.get() > 2) { // If we are on target
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