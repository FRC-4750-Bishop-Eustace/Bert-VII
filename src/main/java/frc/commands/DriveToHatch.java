package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * Aligns the robot with a target using the Limelight
 */
public class DriveToHatch extends Command {

    public DriveToHatch() {
        // Require the drive train
        requires(Robot.driveTrain);
    }

    @Override
    protected void execute() {
        Robot.driveTrain.driveStraight(OI.driveStick.getY());
    }

    @Override
    protected void end() {
        // When we end, brake the drive train
        Robot.driveTrain.brake();
        System.out.println("DriveToHatch() Done!");
    }

    @Override
    protected boolean isFinished() {
        return OI.driveStick.getRawButton(1);
    }

}