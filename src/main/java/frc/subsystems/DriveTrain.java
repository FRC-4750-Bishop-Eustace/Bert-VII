package frc.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
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
    boolean reversed = false;

    // The total degrees off we can call "on target"
    double driveStraightTolerance = 0.005;
    double limelightTolerance = 0.7;
    // PID constants
    double kP = 0.15;

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
        robotDrive.arcadeDrive(desensitize(speed * 0.85, 0.577), rotate * 0.85);
        // .28, .15
    }

    /**
     * Reverses the drive train
     * 
     * @param _reversed true for reversed, false for regular
     */
    public void reverseDriveTrain(boolean _reversed) {
        reversed = _reversed;
    }

    /**
     * Returns whether the drive train is reversed
     */
    public boolean reversed() {
        return reversed;
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
     * Drive the drive train straight forward using the imu
     * 
     * @param speed + is forward, - is backward
     */
    public void driveStraightLimelight(double speed) {
        double turnSpeed = 0;
        if (Robot.limelight.getXOffset() > limelightTolerance && Robot.limelight.getHasTarget()) {
            turnSpeed += Robot.limelight.getXOffset() * (kP * 0.8);
        } else if (Robot.limelight.getXOffset() < -limelightTolerance && Robot.limelight.getHasTarget()) {
            turnSpeed += Robot.limelight.getXOffset() * (kP * 0.8);
        }
        robotDrive.arcadeDrive(-speed, turnSpeed);
    }

    public void driveStraightGyro(double speed) {
        double turnSpeed = 0;
        if (Robot.imu.getAngle() > Robot.imu.getCommandedHeading() + driveStraightTolerance) { // If we are to the right
                                                                                               // of the tolerance
            turnSpeed += (Robot.imu.getAngle() - (Robot.imu.getCommandedHeading() + driveStraightTolerance))
                    * (kP * 1.1); // Re-adjust
            // to
            // the
            // left
        } else if (Robot.imu.getAngle() < Robot.imu.getCommandedHeading() - driveStraightTolerance) { // If we are to
                                                                                                      // the left of the
                                                                                                      // tolerance
            turnSpeed += (Robot.imu.getAngle() - (Robot.imu.getCommandedHeading() + driveStraightTolerance))
                    * (kP * 1.1); // Re-adjust
            // to
            // the
            // right
        }
        robotDrive.arcadeDrive(-speed, -turnSpeed);
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
    protected double desensitize(double value, double gain) {
        return gain * Math.pow(value, 3) + (1 - gain) * value;
    }

}