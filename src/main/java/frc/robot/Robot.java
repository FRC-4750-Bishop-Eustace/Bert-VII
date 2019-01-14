package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.subsystems.DigitalSensor;
import frc.subsystems.DriveTrain;
import frc.subsystems.IMU;
import frc.subsystems.Limelight;
import frc.subsystems.Ultrasonics;

public class Robot extends TimedRobot {

  // Initialize mechanisms
  public static DriveTrain driveTrain = new DriveTrain(RobotMap.LEFT_MOTOR_ONE_ID, RobotMap.LEFT_MOTOR_TWO_ID, RobotMap.LEFT_MOTOR_THREE_ID, RobotMap.RIGHT_MOTOR_ONE_ID, RobotMap.RIGHT_MOTOR_TWO_ID, RobotMap.RIGHT_MOTOR_THREE_ID);

  // Initialize sensors
  public static Limelight limelight = new Limelight();
  public static Ultrasonics ultrasonic = new Ultrasonics();
  public static IMU imu = new IMU();
  public static DigitalSensor hatchDetector = new DigitalSensor(RobotMap.HATCH_SENSOR);
  public static DigitalSensor cargoDetector = new DigitalSensor(RobotMap.CARGO_SENSOR);

  // Initialize OI
  public static OI oi = new OI();

  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    System.out.println("Hatch: " + hatchDetector.get());
    System.out.println("Cargo: " + cargoDetector.get());
  }

  @Override
  public void testPeriodic() {
  }
}
