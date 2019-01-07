package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.commands.AlignWithHatch;

public class OI {

    public static Joystick driveStick = new Joystick(RobotMap.DRIVESTICK_PORT);

    Button alignButton = new JoystickButton(driveStick, 0);

    public OI() {
        alignButton.whenReleased(new AlignWithHatch());
    }
}