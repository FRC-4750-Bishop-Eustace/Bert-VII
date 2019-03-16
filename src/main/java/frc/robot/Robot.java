package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.subsystems.Arm;
import frc.subsystems.CargoArm;
import frc.subsystems.DriveTrain;
import frc.subsystems.Hatch;
import frc.subsystems.IMU;
import frc.subsystems.Limelight;
import frc.subsystems.ObjectSensor;
import frc.subsystems.PressureSensor;
import frc.subsystems.Sleigh;
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
  public static DriveTrain driveTrain = new DriveTrain(RobotMap.LEFT_MASTER_ID, RobotMap.LEFT_FOLLOWER_ONE_ID,
      RobotMap.LEFT_FOLLOWER_TWO_ID, RobotMap.RIGHT_MASTER_ID, RobotMap.RIGHT_FOLLOWER_ONE_ID,
      RobotMap.RIGHT_FOLLOWER_TWO_ID);
  public static Hatch hatch = new Hatch();
  public static Arm arm = new Arm();
  public static Wrist wrist = new Wrist();
  public static CargoArm cargoArm = new CargoArm();
  public static Sleigh sleigh = new Sleigh();

  NetworkTable table = NetworkTableInstance.getDefault().getTable("dashboard");

  // Initialize OI
  public static OI oi = new OI();

  @Override
  public void robotInit() {
    driveTrain.resetEncoders();
    limelight.drivingMode();
    limelight.toggleSnapshots();
    arm.resetArm();
    cargoArm.resetArm();
  }

  @Override
  public void robotPeriodic() {
    Scheduler.getInstance().run();
    outputData();
    limelight.drivingMode();
    if (arm.getLimit()) {
      arm.resetArm();
    }
    if (cargoArm.getLimit()) {
      cargoArm.resetArm();
    }
    System.out.println("Cargo arm: " + cargoArm.getArmPosition());
    System.out.println("Hatch arm: " + arm.getArmPosition());
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
    table.getEntry("distance").setNumber(ultrasonic.getInches());
    table.getEntry("angle").setNumber(imu.getHeading());
    table.getEntry("time").setNumber(DriverStation.getInstance().getMatchTime());
    table.getEntry("pressure").setNumber(pressureSensor.getPressure());
    table.getEntry("hatch-panel").setBoolean(hatchDetector.get());
    table.getEntry("battery-voltage").setNumber(RobotController.getBatteryVoltage());
    table.getEntry("hasTarget").setBoolean(limelight.getHasTarget());
    table.getEntry("wrist").setBoolean(wrist.get());
    table.getEntry("arm").setNumber(arm.getArmPosition());
    table.getEntry("sleigh").setNumber(cargoArm.getArmPosition());
  }
}
