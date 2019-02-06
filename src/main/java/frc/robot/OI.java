package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.commands.AlignWithHatch;
import frc.commands.DriveToHatch;
import frc.commands.PlaceHatch;
import frc.commands.ToggleHatch;
import frc.commands.ToggleWrist;

/**
 * Handles all of the joystick inputs
 */
public class OI {

    // Joysticks
    public static Joystick driveStick = new Joystick(RobotMap.DRIVESTICK_PORT);
    public static Joystick controlStick = new Joystick(RobotMap.CONTROLSTICK_PORT);

    // Drivestick Buttons
    Button alignButton = new JoystickButton(driveStick, 2);

    // Control stick buttons
    Button toggleHatchButton = new JoystickButton(driveStick, 1);
    Button placeHatchButton = new JoystickButton(controlStick, 8);
    Button toggleWristButton = new JoystickButton(controlStick, 3);
    Button driveToHatchButton = new JoystickButton(controlStick, 5);

    public OI() {
        alignButton.whenReleased(new AlignWithHatch());
        toggleHatchButton.whenReleased(new ToggleHatch());
        toggleWristButton.whenReleased(new ToggleWrist());
        driveToHatchButton.whenReleased(new DriveToHatch());
        placeHatchButton.whenReleased(new PlaceHatch());
    }
}