package frc.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.commands.RunPusher;
import frc.robot.RobotMap;

public class Pusher extends Subsystem {

    DoubleSolenoid piston;

    public Pusher() {
        piston = new DoubleSolenoid(RobotMap.PUSHER_FOR_ID, RobotMap.PUSHER_REV_ID);
    }

    public void toggle() {
        piston.set(piston.get() == Value.kForward ? Value.kReverse : Value.kForward);
    }

    public void extend() {
        piston.set(Value.kForward);
    }

    public void retract() {
        piston.set(Value.kReverse);
    }

    public boolean get() {
        return piston.get() == Value.kForward ? true : false;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RunPusher());
    }

}