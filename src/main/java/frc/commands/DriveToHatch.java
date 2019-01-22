package frc.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

/**
 * Drives straight until a certain distance using an ultrasonic
 */
public class DriveToHatch extends PIDCommand {

    // The total inches off we can call "on target"
    double tolerance = 0.5;
    // How many inches away we want to drive until
    double setpoint = 12;
    // The current target count
    double onTargetCount = 0;
    // The max target count
    double maxTargetCount = 7.5;

    public DriveToHatch() {
        // Pass in P, I, D to PIDCommand
        super(0.005, 0.00005, 0.0);
        // Require the drive train
        requires(Robot.driveTrain);
        // Set the commanded heading
        Robot.imu.setCommandedHeading(Robot.imu.getAngle());
    }

    @Override
    protected void initialize() {
        // The min and max values we want the PIDCommand to output
        getPIDController().setOutputRange(-1, 1);
        // The tolerance that is considered "on target"
        getPIDController().setAbsoluteTolerance(tolerance);
        // We want to get to 4 on the ultrasonic sensor
        getPIDController().setSetpoint(setpoint);
    }

    @Override
    protected void end() {
        System.out.println("Done!");
        // When we end, brake the drive train
        Robot.driveTrain.brake();
    }

    @Override
    protected boolean isFinished() {
        // If we are on target
        if(getPIDController().onTarget()) {
            // Add a target count
            onTargetCount++;
        }else {
            // If not, target count is reset
            onTargetCount = 0;
        }
        // We are finished once the target count hits the max count
        return (onTargetCount > maxTargetCount);
    }

    @Override
    protected double returnPIDInput() {
        System.out.println(Robot.ultrasonic.getInches());
        // Input inches from the ultrasonic
        return Robot.ultrasonic.getInches();
    }

    @Override
    protected void usePIDOutput(double output) {
        // Output the calculated speeds to driveForward()
        Robot.driveTrain.driveStraightForward(output);
    }

}