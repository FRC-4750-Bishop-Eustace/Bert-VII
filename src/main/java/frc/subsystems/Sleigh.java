package frc.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Sleigh extends Subsystem {

    Solenoid piston;

    public Sleigh() {
        piston = new Solenoid(RobotMap.SLEIGH_ID);
    }

    public void toggle() {
        piston.set(!piston.get());
    }

    @Override
    protected void initDefaultCommand() {
        // No default command
    }

}