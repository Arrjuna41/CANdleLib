package frc.robot;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdleConfiguration;

/**
 * Mock implementation of CANdle for testing without physical hardware.
 * 
 * <p>This class extends CANdle and overrides key methods to print debug
 * information to the console instead of controlling actual LED hardware.
 * Perfect for testing robot code when the CANdle device is not connected.
 * 
 * <p>Example usage:
 * <pre>
 * // In RobotContainer
 * private final CANdle m_candle = Robot.isReal() 
 *     ? m_candleSubsystem.createCANdle() 
 *     : new MockCANdle(1);
 * </pre>
 */
public class MockCANdle extends CANdle {
    private int deviceNumber;
    private int[] currentColor = new int[]{0, 0, 0};
    private String currentAnimation = "None";

    /**
     * Creates a new MockCANdle with the specified CAN device number.
     * 
     * @param deviceNumber the CAN bus ID (for logging purposes only)
     */
    public MockCANdle(int deviceNumber) {
        super(deviceNumber);
        this.deviceNumber = deviceNumber;
        System.out.println("[MockCANdle-" + deviceNumber + "] Created");
    }

    @Override
    public ErrorCode configAllSettings(CANdleConfiguration allConfigs, int timeoutMs) {
        System.out.println("[MockCANdle-" + deviceNumber + "] Configured with strip type: " + allConfigs.stripType);
        return ErrorCode.OK;
    }

    @Override
    public ErrorCode configAllSettings(CANdleConfiguration allConfigs) {
        return configAllSettings(allConfigs, 50);
    }

    @Override
    public ErrorCode setLEDs(int r, int g, int b) {
        currentColor[0] = r;
        currentColor[1] = g;
        currentColor[2] = b;
        currentAnimation = "None";
        System.out.println("[MockCANdle-" + deviceNumber + "] Set ALL LEDs to RGB(" + r + ", " + g + ", " + b + ")");
        return ErrorCode.OK;
    }

    @Override
    public ErrorCode setLEDs(int r, int g, int b, int w, int startIdx, int count) {
        currentColor[0] = r;
        currentColor[1] = g;
        currentColor[2] = b;
        currentAnimation = "None";
        System.out.println("[MockCANdle-" + deviceNumber + "] Set LEDs [" + startIdx + " to " + (startIdx + count - 1) + "] to RGB(" + r + ", " + g + ", " + b + ")");
        return ErrorCode.OK;
    }

    @Override
    public ErrorCode animate(Animation animation) {
        return animate(animation, 0);
    }

    @Override
    public ErrorCode animate(Animation animation, int animationSlot) {
        currentAnimation = animation.getClass().getSimpleName();
        System.out.println("[MockCANdle-" + deviceNumber + "] Started animation: " + currentAnimation + " (Slot: " + animationSlot + ")");
        return ErrorCode.OK;
    }

    @Override
    public ErrorCode clearAnimation(int animationSlot) {
        currentAnimation = "None";
        System.out.println("[MockCANdle-" + deviceNumber + "] Cleared animation slot: " + animationSlot);
        return ErrorCode.OK;
    }

    /**
     * Gets the current color of the mock CANdle (for testing/debugging).
     * 
     * @return array of [red, green, blue] values
     */
    public int[] getCurrentColor() {
        return currentColor.clone();
    }

    /**
     * Gets the current animation name (for testing/debugging).
     * 
     * @return the simple class name of the current animation, or "None"
     */
    public String getCurrentAnimation() {
        return currentAnimation;
    }

    /**
     * Prints the current state of the mock CANdle to console.
     */
    public void printState() {
        System.out.println("[MockCANdle-" + deviceNumber + "] Current State:");
        System.out.println("  Color: RGB(" + currentColor[0] + ", " + currentColor[1] + ", " + currentColor[2] + ")");
        System.out.println("  Animation: " + currentAnimation);
    }
}