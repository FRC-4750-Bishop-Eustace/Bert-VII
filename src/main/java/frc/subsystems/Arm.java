package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.commands.RunArm;
import frc.robot.RobotMap;

/**
 * This class manages the arm
 */
public class Arm extends Subsystem {

    // Create motors
    public WPI_TalonSRX armMaster, armFollower;

    public Arm() {
        // Initialize motors
        armMaster = new WPI_TalonSRX(RobotMap.ARM_MASTER_ID);
        armFollower = new WPI_TalonSRX(RobotMap.ARM_FOLLOWER_ID);

        // Configure the encoder
        armMaster.configFactoryDefault();
        armMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, RobotMap.PID_INDEX,
                RobotMap.TIMEOUT);
        armMaster.setInverted(RobotMap.ARM_INVERT);
        // armFollower.setInverted(RobotMap.ARM_INVERT);
        armMaster.setSensorPhase(RobotMap.ARM_PHASE);
        armMaster.configAllowableClosedloopError(RobotMap.PID_INDEX, RobotMap.ARM_TOLERANCE, RobotMap.TIMEOUT);
        armMaster.config_kF(RobotMap.PID_INDEX, 0.0, RobotMap.TIMEOUT);
        armMaster.config_kP(RobotMap.PID_INDEX, 0.5, RobotMap.TIMEOUT);
        armMaster.config_kI(RobotMap.PID_INDEX, 0.0, RobotMap.TIMEOUT);
        armMaster.config_kD(RobotMap.PID_INDEX, 0.0, RobotMap.TIMEOUT);
        armMaster.configReverseLimitSwitchSource(RemoteLimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.NormallyOpen,
                RobotMap.ARM_FOLLOWER_ID, RobotMap.TIMEOUT);
        armFollower.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

        // Sets the second motor to follow the master
        armFollower.follow(armMaster);
        armFollower.setInverted(InvertType.FollowMaster);

        int absolutePosition = armMaster.getSensorCollection().getPulseWidthPosition();

        /* Mask out overflows, keep bottom 12 bits */
        absolutePosition &= 0xFFF;
        if (RobotMap.ARM_PHASE) {
            absolutePosition *= -1;
        }
        if (RobotMap.ARM_INVERT) {
            absolutePosition *= -1;
        }

        /* Set the quadrature (relative) sensor to match absolute */
        armMaster.setSelectedSensorPosition(absolutePosition, RobotMap.PID_INDEX, RobotMap.TIMEOUT);
    }

    /**
     * Set the position of the arm
     * 
     * @param counts count to go to
     */
    public void setArmPosition(int counts) {
        armMaster.set(ControlMode.Position, counts);
        armFollower.set(ControlMode.Follower, RobotMap.ARM_MASTER_ID);
    }

    /**
     * Run the arm using the joystick
     * 
     * @param speed joystick input
     */
    public void run(double speed) {
        armMaster.set(ControlMode.PercentOutput, speed);
        armFollower.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Brake all motors on the arm
     * 
     */
    public void stop() {
        armMaster.stopMotor();
        armFollower.stopMotor();
    }

    /**
     * Returns the arm encoder count
     * 
     * @return the arm encoder counts
     */
    public int getArmPosition() {
        return armMaster.getSelectedSensorPosition();
    }

    /**
     * Returns whether the encoder is on target
     * 
     * @param counts
     * @return onTarget
     */
    public boolean isOnTarget(int counts) {
        return Math.abs(armMaster.getSelectedSensorPosition() - counts) < RobotMap.ARM_TOLERANCE;
    }

    /**
     * Zeroes the arm
     */
    public void resetArm() {
        armMaster.setSelectedSensorPosition(0, RobotMap.PID_INDEX, RobotMap.TIMEOUT);
    }

    public boolean getLimit() {
        return armFollower.getSensorCollection().isRevLimitSwitchClosed();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RunArm());
    }

}