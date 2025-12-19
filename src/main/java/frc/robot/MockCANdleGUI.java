package frc.robot;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdleConfiguration;
import edu.wpi.first.wpilibj.RobotBase;

import javax.swing.*;
import java.awt.*;

public class MockCANdleGUI extends CANdle {

    private static final int LED_COUNT = 100;

    private final Color[] leds = new Color[LED_COUNT];

    private JFrame frame;
    private JPanel stripPanel;
    private JLabel statusLabel;

    public MockCANdleGUI(int deviceNumber) {
        super(deviceNumber);

        if (!RobotBase.isSimulation()) {
            throw new IllegalStateException(
                "MockCANdleLEDStripGUI must only be used in simulation"
            );
        }

        // Initialize LEDs to off
        for (int i = 0; i < LED_COUNT; i++) {
            leds[i] = Color.BLACK;
        }

        SwingUtilities.invokeLater(this::createGUI);
    }

    private void createGUI() {
        frame = new JFrame("Mock CANdle LED Strip");
        frame.setSize(800, 180);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        stripPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int ledWidth = getWidth() / LED_COUNT;
                int height = getHeight();

                for (int i = 0; i < LED_COUNT; i++) {
                    g.setColor(leds[i]);
                    g.fillRect(i * ledWidth, 0, ledWidth - 1, height);
                }
            }
        };

        statusLabel = new JLabel("Animation: None", SwingConstants.CENTER);

        frame.add(stripPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);
    }

    /* ============================
       CANdle OVERRIDES
       ============================ */

    @Override
    public ErrorCode configAllSettings(CANdleConfiguration config, int timeoutMs) {
        return ErrorCode.OK;
    }

    @Override
    public ErrorCode setLEDs(int r, int g, int b) {
        return setLEDs(r, g, b, 0, 0, LED_COUNT);
    }

    @Override
    public ErrorCode setLEDs(int r, int g, int b, int w, int startIdx, int count) {
        Color color = new Color(clamp(r), clamp(g), clamp(b));

        int end = Math.min(startIdx + count, LED_COUNT);
        for (int i = startIdx; i < end; i++) {
            if (i >= 0) {
                leds[i] = color;
            }
        }

        repaintStrip();
        return ErrorCode.OK;
    }

    @Override
    public ErrorCode animate(Animation animation) {
        return animate(animation, 0);
    }

    @Override
    public ErrorCode animate(Animation animation, int slot) {
        statusLabel.setText("Animation: " + animation.getClass().getSimpleName());
        return ErrorCode.OK;
    }

    @Override
    public ErrorCode clearAnimation(int slot) {
        statusLabel.setText("Animation: None");
        return ErrorCode.OK;
    }

    /* ============================ */

    private void repaintStrip() {
        SwingUtilities.invokeLater(stripPanel::repaint);
    }

    private int clamp(int v) {
        return Math.max(0, Math.min(255, v));
    }
}
