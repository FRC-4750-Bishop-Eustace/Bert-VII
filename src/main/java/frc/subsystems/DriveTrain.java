package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.commands.TankDrive;

/**
 * This class manages the drive train
 */
public class DriveTrain extends Subsystem {

    // Create motors
    WPI_TalonSRX leftMotorOne, leftMotorTwo, leftMotorThree, rightMotorOne, rightMotorTwo, rightMotorThree;
    // Create motor groups
    SpeedControllerGroup leftMotors, rightMotors;
    // Create drive
    DifferentialDrive robotDrive;

    WPI_TalonSRX encoderMotor;

    // The total degrees off we can call "on target"
    double tolerance = 0.5;

    public DriveTrain(int leftMotorOneId, int leftMotorTwoId, int leftMotorThreeId, int rightMotorOneId, int rightMotorTwoId, int rightMotorThreeId) {
        leftMotorOne = new WPI_TalonSRX(leftMotorOneId);
        leftMotorTwo = new WPI_TalonSRX(leftMotorTwoId);
        leftMotorThree = new WPI_TalonSRX(leftMotorThreeId);
        rightMotorOne = new WPI_TalonSRX(rightMotorOneId);
        rightMotorTwo = new WPI_TalonSRX(rightMotorTwoId);
        rightMotorThree = new WPI_TalonSRX(rightMotorThreeId);

        leftMotorTwo.follow(leftMotorOne);
        leftMotorThree.follow(leftMotorOne);
        rightMotorTwo.follow(rightMotorOne);
        rightMotorThree.follow(rightMotorOne);

        robotDrive = new DifferentialDrive(leftMotorOne, rightMotorOne);
        // Stop "output not updated often enough" error from printing
        robotDrive.setSafetyEnabled(false);

        encoderMotor = new WPI_TalonSRX(7);
        encoderMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    }

    /**
     * Passes speed and rotate values to the drive train
     * 
     * @param speed + is forward, - is backward
     * @param rotate + is right, - is left
     */
    public void joystickDrive(double speed, double rotate) {
        robotDrive.arcadeDrive(speed, rotate);
    }

    /**
     * Turns the drive train
     * 
     * @param speed + is right, - is left
     */
    public void turn(double speed) {
        leftMotorOne.set(ControlMode.PercentOutput, -speed);
        rightMotorOne.set(ControlMode.PercentOutput, -speed);
    }

    /**
     * Drive the drive train straight forward using the imu
     * 
     * @param speed + is forward, - is backward
     */
    public void driveStraightForward(double speed) {
        leftMotorOne.set(ControlMode.PercentOutput, speed);
        rightMotorOne.set(ControlMode.PercentOutput, -speed);
    }

    /**
     * Brakes all motors on the drive train
     */
    public void brake() {
        leftMotorOne.stopMotor();
        rightMotorOne.stopMotor();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }

}