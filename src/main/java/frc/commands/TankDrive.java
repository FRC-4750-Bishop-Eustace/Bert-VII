package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class TankDrive extends Command {

    public TankDrive() {
        requires(Robot.driveTrain);
    }

    @Override
    protected void execute() {
        Robot.driveTrain.joystickDrive(OI.driveStick.getX(), OI.driveStick.getThrottle());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}