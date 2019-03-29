package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class RunCargoArm extends Command {

    Command command;

    public RunCargoArm() {
        requires(Robot.cargoArm);
    }

    @Override
    protected void execute() {
        Robot.cargoArm.run(-OI.controller.getThrottle());
        // if (OI.controller.getRawButton(9)) {
        // Robot.cargoArm.run(-RobotMap.CARGO_ARM_SPEED);
        // } else if (OI.controller.getRawButton(6)) {
        // Robot.cargoArm.run(RobotMap.CARGO_ARM_SPEED);
        // } else {
        // Robot.cargoArm.stop();
        // }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}