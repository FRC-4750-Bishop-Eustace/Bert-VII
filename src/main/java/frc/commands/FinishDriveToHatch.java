package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class FinishDriveToHatch extends Command {

    public FinishDriveToHatch() {
        // Require the drive train
        requires(Robot.driveTrain);
    }

    @Override
    protected void initialize() {
        Robot.imu.setCommandedHeading(Robot.imu.getAngle() + 4);
    }

    @Override
    protected void execute() {
        Robot.driveTrain.driveStraightGyro(OI.driveStick.getY());
    }

    @Override
    protected void end() {
        // When we end, brake the drive train
        Robot.driveTrain.brake();
        System.out.println("FinishDriveToHatch() Done!");
    }

    @Override
    protected boolean isFinished() {
        return OI.driveStick.getRawButton(3) || OI.driveStick.getRawButton(1) && Robot.ultrasonic.getInches() <= 18;
    }

}