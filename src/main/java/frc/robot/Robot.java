package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.subsystems.DriveTrain;
import frc.subsystems.Limelight;

public class Robot extends TimedRobot {

  public static DriveTrain driveTrain = new DriveTrain(RobotMap.LEFT_MOTOR_ONE_ID,
  RobotMap.LEFT_MOTOR_TWO_ID,
  RobotMap.LEFT_MOTOR_THREE_ID,
  RobotMap.RIGHT_MOTOR_ONE_ID,
  RobotMap.RIGHT_MOTOR_TWO_ID,
  RobotMap.RIGHT_MOTOR_THREE_ID);
  public static Limelight limelight = new Limelight();

  @Override
  public void robotInit() {

  }

  @Override
  public void robotPeriodic() {

  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void testPeriodic() {

  }
}
