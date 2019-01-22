package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Drives straight until a certain distance using an ultrasonic
 */
public class DriveStraight extends Command {

    // The total inches off we can call "on target"
    double tolerance = 0.5;
    // How many inches away we want to drive until
    double setpoint = 12;
    // The current target count
    double onTargetCount = 0;
    // The max target count
    double maxTargetCount = 7.5;

    boolean finished = false;

    public DriveStraight() {
        // Require the drive train
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        if(Robot.ultrasonic.getInches() > 6) {
            Robot.driveTrain.driveStraightForward(0.2);
            finished = false;
        }else {
            finished = true;
        }
    }

    @Override
    protected void end() {
        System.out.println("Done!");
        // When we end, brake the drive train
        Robot.driveTrain.brake();
    }

    @Override
    protected boolean isFinished() {
        // We are finished once the target count hits the max count
        return finished;
    }

}