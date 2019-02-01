package frc.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.commands.Output;
import frc.robot.RobotMap;

public class PressureSensor extends Subsystem {

    // Sensor
    public AnalogInput sensor;

    public PressureSensor() {
        // Create an analog input
        sensor = new AnalogInput(RobotMap.PRESSURE_SENSOR);
    }

    /**
     * Returns the current pressure
     * 
     * @return current pressure (psi)
     */
    public double getPressure() {
        // return sensor.getVoltage() / (0.004 * 120 + 0.1);
        return 250 * (sensor.getVoltage() / RobotMap.PRESSURE_CONST) - 25;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Output());
    }
}