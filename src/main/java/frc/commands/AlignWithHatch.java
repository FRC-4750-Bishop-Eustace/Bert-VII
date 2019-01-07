package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AlignWithHatch extends Command {

    double kP = 0.01;
    boolean finished = false;

    public AlignWithHatch() {
        requires(Robot.driveTrain);
    }

    @Override
    protected void execute() {
        double offset = Robot.limelight.getXOffset();
        System.out.println("running");
        if(Math.abs(offset) < 0.1) finished = true;
        double adjust = kP * offset;
        Robot.driveTrain.turn(adjust);
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

}