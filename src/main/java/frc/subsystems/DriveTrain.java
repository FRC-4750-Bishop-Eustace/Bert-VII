package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.commands.TankDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * This class manages the drive train
 */
public class DriveTrain extends Subsystem {

    // Create motors
    public WPI_TalonSRX leftMaster, leftFollowerOne, leftFollowerTwo, rightMaster, rightFollowerOne, rightFollowerTwo;
    // Create motor groups
    SpeedControllerGroup leftMotors, rightMotors;
    // Create drive
    DifferentialDrive robotDrive;

    double p = 0.3;
    double i = 0.0007; // 0.001
    double d = 110;
    double f = 0.0;

    // The total degrees off we can call "on target"
    double driveStraightTolerance = 0.5;
    // PID constants
    double kP = 0.01;

    public DriveTrain(int leftMasterId, int leftFollowerOneId, int leftFollowerTwoId, int rightMasterId,
            int rightFollowerOneId, int rightFollowerTwoId) {
        // Initialize motors
        leftMaster = new WPI_TalonSRX(leftMasterId);
        leftFollowerOne = new WPI_TalonSRX(leftFollowerOneId);
        leftFollowerTwo = new WPI_TalonSRX(leftFollowerTwoId);
        rightMaster = new WPI_TalonSRX(rightMasterId);
        rightFollowerOne = new WPI_TalonSRX(rightFollowerOneId);
        rightFollowerTwo = new WPI_TalonSRX(rightFollowerTwoId);

        // Set motor and encoder phases
        leftMaster.setInverted(RobotMap.LEFT_INVERT);
        leftMaster.setSensorPhase(RobotMap.LEFT_PHASE);
        rightMaster.setInverted(RobotMap.RIGHT_INVERT);
        rightMaster.setSensorPhase(RobotMap.RIGHT_PHASE);

        // Set motors to follow masters
        leftFollowerOne.follow(leftMaster);
        leftFollowerTwo.follow(leftMaster);
        rightFollowerOne.follow(rightMaster);
        rightFollowerTwo.follow(rightMaster);
        leftFollowerOne.setInverted(InvertType.FollowMaster);
        leftFollowerTwo.setInverted(InvertType.FollowMaster);
        rightFollowerOne.setInverted(InvertType.FollowMaster);
        rightFollowerTwo.setInverted(InvertType.FollowMaster);

        // Configure left motor
        leftMaster.configFactoryDefault();
        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.PID_INDEX,
                RobotMap.TIMEOUT);
        leftMaster.configMotionCruiseVelocity(1830);
        leftMaster.configMotionAcceleration(1300);
        leftMaster.configAllowableClosedloopError(RobotMap.PID_INDEX, RobotMap.DRIVE_TOLERANCE, RobotMap.TIMEOUT);
        leftMaster.config_kP(0, p);
        leftMaster.config_kI(0, i);
        leftMaster.config_kD(0, d);
        leftMaster.config_kF(0, f);
        leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);

        // Configure right motor
        rightMaster.configFactoryDefault();
        rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.PID_INDEX,
                RobotMap.TIMEOUT);
        rightMaster.configMotionCruiseVelocity(1830);
        rightMaster.configMotionAcceleration(1300);
        rightMaster.configAllowableClosedloopError(RobotMap.PID_INDEX, RobotMap.DRIVE_TOLERANCE, RobotMap.TIMEOUT);
        rightMaster.config_kP(0, p);
        rightMaster.config_kI(0, i);
        rightMaster.config_kD(0, d);
        rightMaster.config_kF(0, f);
        rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);

        robotDrive = new DifferentialDrive(leftMaster, rightMaster);
        // Stop "output not updated often enough" error from printing
        robotDrive.setSafetyEnabled(false);
    }

    /**
     * Passes speed and rotate values to the drive train
     * 
     * @param speed  + is forward, - is backward
     * @param rotate + is right, - is left
     */
    public void joystickDrive(double speed, double rotate) {
        robotDrive.arcadeDrive(desensitize(speed), desensitize(rotate));
    }

    /**
     * Turns the drive train
     * 
     * @param speed + is right, - is left
     */
    public void turn(double speed) {
        robotDrive.arcadeDrive(0, -speed);
    }

    /**
     * Drive the drive train
     * 
     * @param speed + is right, - is left
     */
    public void drive(double speed) {
        robotDrive.arcadeDrive(speed, 0);
    }

    /**
     * Drive the drive train straight forward using the imu
     * 
     * @param speed + is forward, - is backward
     */
    public void driveStraight(double speed) {
        double turnSpeed = 0;
        if (Robot.imu.getAngle() > Robot.imu.getCommandedHeading() + driveStraightTolerance) { // If we are to the right
                                                                                               // of the
            // tolerance
            turnSpeed += (Robot.imu.getAngle() - (Robot.imu.getCommandedHeading() + driveStraightTolerance)) * kP; // Re-adjust
                                                                                                                   // to
            // the left
        } else if (Robot.imu.getAngle() < Robot.imu.getCommandedHeading() - driveStraightTolerance) { // If we are to
                                                                                                      // the left of the
            // tolerance
            turnSpeed += (Robot.imu.getAngle() - (Robot.imu.getCommandedHeading() + driveStraightTolerance)) * kP; // Re-adjust
                                                                                                                   // to
            // the right
        }
        robotDrive.arcadeDrive(speed, turnSpeed);
    }

    /**
     * Drives to a distance using motion magic
     * 
     * @param counts the counts to drive
     */
    public void driveToDistance(double inches) {
        int counts = (int) (inches / RobotMap.CIRCUMFERENCE) * RobotMap.PULSES_PER_REVOLUTION;
        rightMaster.set(ControlMode.MotionMagic, counts);
        leftMaster.set(ControlMode.MotionMagic, counts);
    }

    /**
     * Returns whether the encoders are on target
     * 
     * @param inches
     * @return onTarget
     */
    public boolean isOnTarget(double inches) {
        int counts = (int) (inches / RobotMap.CIRCUMFERENCE) * RobotMap.PULSES_PER_REVOLUTION;
        return Math.abs(leftMaster.getSelectedSensorPosition() - counts) < RobotMap.DRIVE_TOLERANCE
                && Math.abs(rightMaster.getSelectedSensorPosition() - counts) < RobotMap.DRIVE_TOLERANCE;
    }

    /**
     * Brakes all motors on the drive train
     */
    public void brake() {
        leftMaster.stopMotor();
        rightMaster.stopMotor();
    }

    /**
     * Returns the left encoder count
     * 
     * @return the left encoder counts
     */
    public int getLeftEncoder() {
        return leftMaster.getSelectedSensorPosition();
    }

    /**
     * Returns the right encoder count
     * 
     * @return the right encoder counts
     */
    public int getRightEncoder() {
        return rightMaster.getSelectedSensorPosition();
    }

    /**
     * Zeroes both drive encoders
     */
    public void resetEncoders() {
        leftMaster.setSelectedSensorPosition(0, RobotMap.PID_INDEX, RobotMap.TIMEOUT);
        rightMaster.setSelectedSensorPosition(0, RobotMap.PID_INDEX, RobotMap.TIMEOUT);
    }

    /**
     * Zeroes the left drive encoder
     */
    public void resetLeftEncoder() {
        leftMaster.setSelectedSensorPosition(0, RobotMap.PID_INDEX, RobotMap.TIMEOUT);
    }

    /**
     * Zeroes the right drive encoder
     */
    public void resetRightEncoder() {
        rightMaster.setSelectedSensorPosition(0, RobotMap.PID_INDEX, RobotMap.TIMEOUT);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    };

    /**
     * Desensitizes the joystick values at low speeds
     * 
     */
    protected double desensitize(double value) {
        return 0.1 * Math.pow(value, 3) + (1 - 0.3) * value;
    }

    /**
     * Initializes motion magic
     */
    public void setupMotionMagic() {
        rightMaster.setInverted(!RobotMap.RIGHT_INVERT);
        rightMaster.setSensorPhase(!RobotMap.RIGHT_PHASE);
    }

    /**
     * Resets for regular driving
     */
    public void endMotionMagic() {
        rightMaster.setInverted(RobotMap.RIGHT_INVERT);
        rightMaster.setSensorPhase(RobotMap.RIGHT_PHASE);
    }

}