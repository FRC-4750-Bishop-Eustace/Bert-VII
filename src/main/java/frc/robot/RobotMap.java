package frc.robot;

public class RobotMap {

    // Joystick ports (USB)
    public static final int DRIVESTICK_PORT = 0;
    public static final int CONTROLSTICK_PORT = 1;

    // Drive train motor IDs (CAN)
    public static final int LEFT_MASTER_ID = 2;
    public static final int LEFT_FOLLOWER_ONE_ID = 0;
    public static final int LEFT_FOLLOWER_TWO_ID = 1;
    public static final int RIGHT_MASTER_ID = 5;
    public static final int RIGHT_FOLLOWER_ONE_ID = 3;
    public static final int RIGHT_FOLLOWER_TWO_ID = 4;

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
    public static final int ARM_MASTER_ID = 6;
    public static final int ARM_FOLLOWER_ID = 7;

    // Arm piston IDs (PCM)
    public static final int WRIST_PISTON_ID = 1;

    // Drive Train Constants
    public static final double WHEEL_RADIUS = 3;
    public static final double CIRCUMFERENCE = 2 * Math.PI * WHEEL_RADIUS;
    public static final int PULSES_PER_REVOLUTION = 4096;
    public static final boolean LEFT_INVERT = false;
    public static final boolean LEFT_PHASE = false;
    public static final boolean RIGHT_INVERT = false;
    public static final boolean RIGHT_PHASE = true;
    public static final int DRIVE_TOLERANCE = 5;

    // Arm Constants
    public static final double ARM_SPEED = 1;
    public static final boolean ARM_INVERT = false;
    public static final boolean ARM_PHASE = false;
    public static final int ARM_TOLERANCE = 0;

    // Talon SRX Constants
    public static final int TIMEOUT = 30;
    public static final int PID_INDEX = 0;

    // Pressure Constants
    public static final double PRESSURE_CONST = 4.8;
}