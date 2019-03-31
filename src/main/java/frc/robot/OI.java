package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.commands.PositionCargoArm;
import frc.commands.ResetCargoArm;
import frc.commands.ReverseDriveTrain;
import frc.commands.ToggleBackPistons;
import frc.commands.ToggleFrontPistons;
import frc.commands.TogglePincer;
import frc.commands.ToggleSleigh;

/**
 * Handles all of the joystick inputs
 */
public class OI {

    // Joysticks
    public static Joystick driveStick = new Joystick(RobotMap.DRIVESTICK_PORT);
    public static Joystick controller = new Joystick(RobotMap.CONTROLSTICK_PORT);

    // Drivestick Buttons
    Button toggleFrontPistons = new JoystickButton(driveStick, 5);
    Button toggleBackPistons = new JoystickButton(driveStick, 6);
    Button reverseButton = new JoystickButton(driveStick, 2);

    // Control stick buttons
    // Button armToResetButton = new JoystickButton(controller, 5);
    // Button armToPlaceButton = new JoystickButton(controller, 3);
    // Button armToFloorButton = new JoystickButton(controller, 1);
    // Button toggleSleighButton = new JoystickButton(controller, 8);
    // Button toggleWristButton = new JoystickButton(controller, 2);

    Button cargoArmToResetButton = new JoystickButton(controller, 1);
    Button cargoArmToPlaceButton = new JoystickButton(controller, 2);
    Button cargoArmToFloorButton = new JoystickButton(controller, 3);
    Button cargoArmToRocketButton = new JoystickButton(controller, 4);
    Button cargoArmToResetSlowButton = new JoystickButton(controller, 6);
    Button togglePincerButton = new JoystickButton(controller, 7);
    Button toggleSleighButton = new JoystickButton(controller, 8);

    public OI() {
        reverseButton.whenReleased(new ReverseDriveTrain());
        toggleFrontPistons.whenReleased(new ToggleFrontPistons());
        toggleBackPistons.whenReleased(new ToggleBackPistons());

        cargoArmToFloorButton.whenReleased(new PositionCargoArm(RobotMap.CARGO_FLOOR_COUNTS));
        cargoArmToPlaceButton.whenReleased(new PositionCargoArm(RobotMap.CARGO_PLACE_COUNTS));
        cargoArmToResetButton.whenReleased(new ResetCargoArm(RobotMap.CARGO_ARM_SPEED));
        cargoArmToRocketButton.whenReleased(new PositionCargoArm(RobotMap.CARGO_ROCKET_COUNTS));
        cargoArmToResetSlowButton.whenReleased(new ResetCargoArm(RobotMap.CARGO_ARM_SPEED / 2));
        togglePincerButton.whenReleased(new TogglePincer());
        toggleSleighButton.whenReleased(new ToggleSleigh());
    }
}