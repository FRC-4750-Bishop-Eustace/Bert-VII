package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.commands.AlignWithHatch;
import frc.commands.PlaceHatch;
import frc.commands.PositionArm;
import frc.commands.ResetArm;
import frc.commands.ReverseDriveTrain;
import frc.commands.TankDrive;
import frc.commands.ToggleHatch;
import frc.commands.ToggleSleigh;
import frc.commands.ToggleWrist;

/**
 * Handles all of the joystick inputs
 */
public class OI {

    // Joysticks
    public static Joystick driveStick = new Joystick(RobotMap.DRIVESTICK_PORT);
    public static Joystick controlStick = new Joystick(RobotMap.CONTROLSTICK_PORT);

    // Drivestick Buttons
    Button toggleHatchButton = new JoystickButton(driveStick, 1);
    Button placeHatch = new JoystickButton(driveStick, 2);
    Button breakButton = new JoystickButton(driveStick, 3);
    Button reverseButton = new JoystickButton(driveStick, 6);

    Button align = new JoystickButton(controlStick, 7);

    // Control stick buttons
    Button armToFloorButton = new JoystickButton(controlStick, 1);
    Button armToPlaceButton = new JoystickButton(controlStick, 3);
    Button armToResetButton = new JoystickButton(controlStick, 5);
    Button toggleWristButton = new JoystickButton(controlStick, 4);
    Button toggleSleighButton = new JoystickButton(controlStick, 8);

    public OI() {
        toggleHatchButton.whenReleased(new ToggleHatch());
        placeHatch.whenReleased(new PlaceHatch());
        breakButton.whenReleased(new TankDrive());
        reverseButton.whenReleased(new ReverseDriveTrain());

        armToFloorButton.whenReleased(new PositionArm(RobotMap.FLOOR_COUNTS));
        armToPlaceButton.whenReleased(new PositionArm(RobotMap.PLACE_COUNTS));
        armToResetButton.whenReleased(new ResetArm());
        toggleWristButton.whenReleased(new ToggleWrist());
        toggleSleighButton.whenReleased(new ToggleSleigh());

        align.whenReleased(new AlignWithHatch());
    }
}