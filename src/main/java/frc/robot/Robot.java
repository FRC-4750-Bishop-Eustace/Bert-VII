package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.subsystems.Arm;
import frc.subsystems.DriveTrain;
import frc.subsystems.Hatch;
import frc.subsystems.IMU;
import frc.subsystems.Limelight;
import frc.subsystems.ObjectSensor;
import frc.subsystems.PressureSensor;
import frc.subsystems.Ultrasonics;
import frc.subsystems.Wrist;

public class Robot extends TimedRobot {

  // Initialize sensors
  public static Limelight limelight = new Limelight();
  public static Ultrasonics ultrasonic = new Ultrasonics();
  public static IMU imu = new IMU();
  public static PressureSensor pressureSensor = new PressureSensor();
  public static ObjectSensor hatchDetector = new ObjectSensor(RobotMap.HATCH_SENSOR);

  // Initialize mechanisms
  public static DriveTrain driveTrain = new DriveTrain(RobotMap.LEFT_MOTOR_ONE_ID, RobotMap.LEFT_MOTOR_TWO_ID,
      RobotMap.LEFT_MOTOR_THREE_ID, RobotMap.RIGHT_MOTOR_ONE_ID, RobotMap.RIGHT_MOTOR_TWO_ID,
      RobotMap.RIGHT_MOTOR_THREE_ID);
  public static Hatch hatch = new Hatch();
  public static Arm arm = new Arm();
  public static Wrist wrist = new Wrist();

  NetworkTable table = NetworkTableInstance.getDefault().getTable("dashboard");

  // Initialize OI
  public static OI oi = new OI();

  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
    table.getEntry("distance").setNumber(ultrasonic.getInches());
    table.getEntry("angle").setNumber(imu.getHeading());
    table.getEntry("time").setNumber(DriverStation.getInstance().getMatchTime());
    table.getEntry("pressure").setNumber(pressureSensor.getPressure());
    table.getEntry("hatch-panel").setBoolean(hatchDetector.get());
    table.getEntry("battery-voltage").setNumber(RobotController.getBatteryVoltage());
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }
}
