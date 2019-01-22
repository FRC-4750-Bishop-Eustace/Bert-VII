package frc.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceHatch extends CommandGroup {
    public PlaceHatch() {
        addSequential(new AlignWithHatch());
        addSequential(new DriveStraight());
        addSequential(new ToggleHatch());
    }
}