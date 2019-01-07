package frc.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.commands.AlignWithHatch;

public class DriveTrain extends Subsystem {

    WPI_TalonSRX leftMotorOne,
                 leftMotorTwo,
                 leftMotorThree,
                 rightMotorOne,
                 rightMotorTwo,
                 rightMotorThree;

    SpeedControllerGroup leftMotors,
                         rightMotors;

    DifferentialDrive robotDrive;

    public DriveTrain(int leftMotorOneId, int leftMotorTwoId, int leftMotorThreeId, int rightMotorOneId, int rightMotorTwoId, int rightMotorThreeId) {
        leftMotorOne = new WPI_TalonSRX(leftMotorOneId);
        leftMotorTwo = new WPI_TalonSRX(leftMotorTwoId);
        leftMotorThree = new WPI_TalonSRX(leftMotorThreeId);
        rightMotorOne = new WPI_TalonSRX(rightMotorOneId);
        rightMotorTwo = new WPI_TalonSRX(rightMotorTwoId);
        rightMotorThree = new WPI_TalonSRX(rightMotorThreeId);

        leftMotors = new SpeedControllerGroup(leftMotorOne, leftMotorTwo, leftMotorThree);
        rightMotors = new SpeedControllerGroup(rightMotorOne, rightMotorTwo, rightMotorThree);

        robotDrive = new DifferentialDrive(leftMotors, rightMotors);
    }

    public void joystickDrive(double speed, double rotate) {
        robotDrive.arcadeDrive(speed, rotate);
    }

    public void turn(double speed) {
        robotDrive.arcadeDrive(0, speed);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new AlignWithHatch());
    }

}