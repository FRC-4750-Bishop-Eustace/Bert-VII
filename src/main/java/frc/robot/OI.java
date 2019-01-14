package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.commands.AlignWithHatch;
import frc.commands.DriveToHatch;
import frc.commands.ToggleHatch;

/**
 * Handles all of the joystick inputs
 */
public class OI {

    // Joysticks
    public static Joystick driveStick = new Joystick(RobotMap.DRIVESTICK_PORT);

    // Buttons
    Button alignButton = new JoystickButton(driveStick, 2);
    Button driveToButton = new JoystickButton(driveStick, 3);
    Button toggleHatchButton = new JoystickButton(driveStick, 4);

    public OI() {
        // When released, align with the hatch
        alignButton.whenReleased(new AlignWithHatch());
        // When released, drive to the hatch
        driveToButton.whenReleased(new DriveToHatch());
        // When released, toggle hatch pistons
        toggleHatchButton.whenReleased(new ToggleHatch());
    }
}