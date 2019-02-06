package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.commands.RunArm;
import frc.robot.RobotMap;

/**
 * This class manages the arm
 */
public class Arm extends Subsystem {

    // Create motors
    public WPI_TalonSRX armMotorOne, armMotorTwo;

    public Arm() {
        // Initialize motors
        armMotorOne = new WPI_TalonSRX(RobotMap.ARM_MOTOR_ONE_ID);
        armMotorTwo = new WPI_TalonSRX(RobotMap.ARM_MOTOR_TWO_ID);

        // Configure the encoder
        armMotorOne.configFactoryDefault();
        armMotorOne.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.timeoutMs);
        armMotorOne.setSensorPhase(true);

        // Sets the second motor to follow the master
        armMotorTwo.follow(armMotorOne);
    }

    /**
     * Set the position of the arm
     * 
     * @param counts count to go to
     */
    public void setArmPosition(int counts) {
        armMotorOne.set(ControlMode.MotionMagic, counts);
    }

    /**
     * Run the arm using the joystick
     * 
     * @param speed joystick input
     */
    public void run(double speed) {
        armMotorOne.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Brake all motors on the arm
     * 
     */
    public void stop() {
        armMotorOne.stopMotor();
        armMotorTwo.stopMotor();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RunArm());
    }

}