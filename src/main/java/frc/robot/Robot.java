package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.subsystems.CargoArm;
import frc.subsystems.Lifter;
import frc.subsystems.DriveTrain;
import frc.subsystems.IMU;
import frc.subsystems.Limelight;
import frc.subsystems.ObjectSensor;
import frc.subsystems.Pincer;
import frc.subsystems.PressureSensor;
import frc.subsystems.Pusher;
import frc.subsystems.Sleigh;

public class Robot extends TimedRobot {

  // Initialize sensors
  public static Limelight limelight = new Limelight();
  public static IMU imu = new IMU();
  public static PressureSensor pressureSensor = new PressureSensor();
  public static ObjectSensor hatchDetector = new ObjectSensor(RobotMap.HATCH_SENSOR);

  // Initialize mechanisms
  public static DriveTrain driveTrain = new DriveTrain(RobotMap.LEFT_MASTER_ID, RobotMap.LEFT_FOLLOWER_ONE_ID,
      RobotMap.LEFT_FOLLOWER_TWO_ID, RobotMap.RIGHT_MASTER_ID, RobotMap.RIGHT_FOLLOWER_ONE_ID,
      RobotMap.RIGHT_FOLLOWER_TWO_ID);
  public static Pincer pincer = new Pincer();
  public static Pusher pusher = new Pusher();
  public static CargoArm cargoArm = new CargoArm();
  public static Sleigh sleigh = new Sleigh();
  public static Lifter lifter = new Lifter();

  NetworkTable table = NetworkTableInstance.getDefault().getTable("dashboard");

  // Initialize OI
  public static OI oi = new OI();

  @Override
  public void robotInit() {
    driveTrain.resetEncoders();
    limelight.forward();
    limelight.toggleSnapshots();
    cargoArm.resetArm();
  }

  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
    outputData();
    if (cargoArm.getLimit()) {
      cargoArm.resetArm();
    }
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

  private void outputData() {
    table.getEntry("time").setString(DriverStation.getInstance().getMatchTime() < 0 ? "0:00" : Math.floor(DriverStation.getInstance().getMatchTime() / 60) + ":" + (DriverStation.getInstance().getMatchTime() % 60 < 10 ? "0" : "") + Math.floor(DriverStation.getInstance().getMatchTime() % 60));
    table.getEntry("pressure").setNumber(pressureSensor.getPressure());
    table.getEntry("battery-voltage").setNumber(RobotController.getBatteryVoltage());
    table.getEntry("pincer-state").setBoolean(pincer.get());
    table.getEntry("pusher-state").setBoolean(pusher.get());
  }
}
