package frc.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

/**
 * Aligns the robot with a target using the Limelight
 */
public class AlignWithHatch extends PIDCommand {

    // The total degrees off we can call "on target"
    double tolerance = 0.7;
    // The current target count
    double onTargetCount = 0;
    // The max target count
    double maxTargetCount = 7.5;

    Timer timer = new Timer();

    public AlignWithHatch() {
        // Pass in P, I, D to PIDCommand
        super(0.06, 0.001, 0.1);
        // super(0.025, 0.003, 0.002);
        // Require the drive train
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        timer.reset();
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
        timer.start();
    }

    @Override
    protected void end() {
        // When we end, brake the drive train
        Robot.driveTrain.brake();
        Robot.limelight.drivingMode();
        System.out.println("AlignWithHatch() Done!");
    }

    @Override
    protected boolean isFinished() {
        if (getPIDController().onTarget() || !Robot.limelight.getHasTarget() || timer.get() > 3) { // If we are on
                                                                                                   // target or if we
                                                                                                   // don't
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