package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class RunCargoArm extends Command {

    Command command;

    public RunCargoArm() {
        requires(Robot.cargoArm);
    }

    @Override
    protected void execute() {
        if (OI.controller.getPOV() == 270) {
            System.out.println("To reset");
            command = new ResetCargoArm();
            command.start();
        } else if (OI.controller.getPOV() == 180) {
            System.out.println("To place");
            command = new PositionCargoArm(RobotMap.CARGO_PLACE_COUNTS);
            command.start();
        } else if (OI.controller.getPOV() == 90) {
            System.out.println("To floor");
            command = new PositionCargoArm(RobotMap.CARGO_FLOOR_COUNTS);
            command.start();
        } else {
            Robot.cargoArm.run(-OI.controller.getY());
        }
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