package frc.robot;

public class RobotMap {

    // Joystick ports
    public static final int DRIVESTICK_PORT = 0;

    // Drive train motor ports
    public static final int LEFT_MOTOR_ONE_ID = 0;
    public static final int LEFT_MOTOR_TWO_ID = 1;
    public static final int LEFT_MOTOR_THREE_ID = 2;
    public static final int RIGHT_MOTOR_ONE_ID = 3;
    public static final int RIGHT_MOTOR_TWO_ID = 4;
    public static final int RIGHT_MOTOR_THREE_ID = 5;

    // Drive train constants
    public static final double ENC_CONST = (6 * Math.PI) / 4096;

    // Digital sensor ports
    public static final int HATCH_SENSOR = 0;
    public static final int CARGO_SENSOR = 1;
    public static final int ULTRASONIC_TRIGGER = 2;
    public static final int ULTRASONIC_ECHO = 3;

    // Analog sensor ports
    public static final int PRESSURE_SENSOR = 0;

    // Hatch piston IDs
    public static final int HATCH_PISTON_ID = 0;
}