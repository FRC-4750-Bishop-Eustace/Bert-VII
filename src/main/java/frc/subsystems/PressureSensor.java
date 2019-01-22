package frc.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class PressureSensor extends Subsystem {

    // Sensor
    public AnalogInput sensor;

    public PressureSensor() {
        sensor = new AnalogInput(RobotMap.PRESSURE_SENSOR);
    }

    public double getPressure() {
        return 250*(sensor.getValue() / 4034.4828) - 25;
    }
    @Override
    protected void initDefaultCommand() {

    }
}