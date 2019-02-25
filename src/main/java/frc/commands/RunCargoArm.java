package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class RunCargoArm extends Command {

    public RunCargoArm() {
        requires(Robot.cargoArm);
    }

    @Override
    protected void execute() {
        if (OI.controlStick.getRawButton(9)) {
            Robot.cargoArm.run(RobotMap.CARGO_SPEED);
        } else if (OI.controlStick.getRawButton(6)) {
            Robot.cargoArm.run(-RobotMap.CARGO_SPEED);
        } else {
            Robot.cargoArm.stop();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}