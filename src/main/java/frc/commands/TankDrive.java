package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Drives the drive train with a joystick
 */
public class TankDrive extends Command {

    public TankDrive() {
        // Require the drive train
        requires(Robot.driveTrain);
    }

    @Override
    protected void execute() {
        // Pass the joystick values to joystickDrive()
        if(OI.driveStick.getRawButton(1) && Robot.ultrasonic.getInches() <= RobotMap.STOP_DISTANCE) {
            Robot.driveTrain.brake();
        }else {
            if (!Robot.driveTrain.reversed()) {
                Robot.driveTrain.joystickDrive(-OI.driveStick.getY(), OI.driveStick.getThrottle());
                Robot.limelight.forward();
            } else {
                Robot.driveTrain.joystickDrive(OI.driveStick.getY(), OI.driveStick.getThrottle());
                Robot.limelight.reverse();
            }
        }
    }

    @Override
    protected boolean isFinished() {
        // Never finish
        return false;
    }

}