package frc.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Command group to align, drive, and place hatch
 */
public class PlaceHatch extends CommandGroup {

    public PlaceHatch() {
        addSequential(new AlignWithHatch()); // First align
        addSequential(new DriveToHatch());
    }
}