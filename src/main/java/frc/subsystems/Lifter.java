package frc.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Lifter extends Subsystem {

    Solenoid frontPistons;
    Solenoid backPistons;

    public Lifter() {
        frontPistons = new Solenoid(RobotMap.FRONT_LIFTER);
        backPistons = new Solenoid(RobotMap.BACK_LIFTER);
    }

    public void extendFront() {
        frontPistons.set(true);
    }

    public void retractFront() {
        frontPistons.set(false);
    }

    public void toggleFront() {
        frontPistons.set(!frontPistons.get());
    }

    public void extendBack() {
        backPistons.set(true);
    }

    public void retractBack() {
        backPistons.set(false);
    }

    public void toggleBack() {
        backPistons.set(!backPistons.get());
    }

    @Override
    public void initDefaultCommand() {
        // No default command
    }
}