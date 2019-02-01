package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * Handles outputting all sensor data
 */
public class Output extends Command {

    public Output() {
        // Require the pressure sensorS
        requires(Robot.pressureSensor);
    }

    @Override
    protected void execute() {
        // Output the current pressure
        SmartDashboard.putNumber("Pressure: ", Robot.pressureSensor.getPressure());
        // Output if we have the hatch
        SmartDashboard.putBoolean("Hatch? ", Robot.hatchDetector.get());

        SmartDashboard.putBoolean("Hatch Piston State: ", Robot.hatch.getState());
    }

    @Override
    protected boolean isFinished() {
        // Never finish
        return false;
    }

}