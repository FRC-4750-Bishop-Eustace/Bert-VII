package frc.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

/**
 * Aligns the robot with a target using the Limelight
 */
public class AlignWithHatch extends PIDCommand {

    // The total degrees off we can call "on target"
    double tolerance = 0.3;
    // The current target count
    double onTargetCount = 0;
    // The max target count
    double maxTargetCount = 7.5;

    public AlignWithHatch() {
        // Pass in P, I, D to PIDCommand
        super(0.01, 0.001, 0.0);
        // Require the drive train
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        // The min and max values the Limelight can input
        getPIDController().setInputRange(-45, 45);
        // The min and max values we want the PIDCommand to output
        getPIDController().setOutputRange(-1, 1);
        // The tolerance that is considered "on target"
        getPIDController().setAbsoluteTolerance(tolerance);
        // The robot's values will wrap around
        getPIDController().setContinuous();
        // We want to get to 0 on the Limelight
        getPIDController().setSetpoint(0);
    }

    @Override
    protected void end() {
        // When we end, brake the drive train
        Robot.driveTrain.brake();
    }

    @Override
    protected boolean isFinished() {
        if (getPIDController().onTarget() || !Robot.limelight.getHasTarget()) { // If we are on target or if we don't
                                                                                // have a target anymore
            // Add a target count
            onTargetCount++;
        } else { // Otherwise
            // Reset the target count
            onTargetCount = 0;
        }
        // We are finished once the target count hits the max target count
        return (onTargetCount > maxTargetCount);
    }

    @Override
    protected double returnPIDInput() {
        // Input the X offset from the Limelight
        return Robot.limelight.getXOffset();
    }

    @Override
    protected void usePIDOutput(double output) {
        // Output the calculated speeds to turn()
        Robot.driveTrain.turn(output);
    }

}