package frc.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Pincer extends Subsystem {

    Solenoid piston;

    public Pincer() {
        piston = new Solenoid(RobotMap.PINCER_ID);
    }

    public void toggle() {
        piston.set(!piston.get());
    }

    public void open() {
        piston.set(true);
    }

    public void close() {
        piston.set(false);
    }

    public boolean get() {
        return piston.get();
    }

    @Override
    protected void initDefaultCommand() {

    }

}