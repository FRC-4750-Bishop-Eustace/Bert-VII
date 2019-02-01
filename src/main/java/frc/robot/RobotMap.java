package frc.robot;

public class RobotMap {

    // Joystick ports (USB)
    public static final int DRIVESTICK_PORT = 0;
    public static final int CONTROLSTICK_PORT = 1;

    // Drive train motor IDs (CAN)
    public static final int LEFT_MOTOR_ONE_ID = 0;
    public static final int LEFT_MOTOR_TWO_ID = 1;
    public static final int LEFT_MOTOR_THREE_ID = 2;
    public static final int RIGHT_MOTOR_ONE_ID = 3;
    public static final int RIGHT_MOTOR_TWO_ID = 4;
    public static final int RIGHT_MOTOR_THREE_ID = 5;

    // Digital sensor ports (DIO)
    public static final int HATCH_SENSOR = 0;
    public static final int CARGO_SENSOR = 1;
    public static final int ULTRASONIC_TRIGGER = 2;
    public static final int ULTRASONIC_ECHO = 3;

    // Analog sensor ports (Analog)
    public static final int PRESSURE_SENSOR = 0;

    // Hatch piston IDs (PCM)
    public static final int HATCH_PISTON_ID = 0;

    // Arm motor IDs (CAN)
    public static final int ARM_MOTOR_ONE_ID = 6;
    public static final int ARM_MOTOR_TWO_ID = 7;

    // Arm piston IDs (PCM)
    public static final int WRIST_PISTON_ID = 1;

    // Constants
    public static final double ENC_CONST = (6 * Math.PI) / 4096;
    public static final int timeoutMs = 30;
    public static final double ARM_SPEED = 1;
    public static final double PRESSURE_CONST = 4.8;
}