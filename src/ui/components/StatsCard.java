package ui.components;

import java.awt.*;
import javax.swing.*;
import utils.ThemeManager;


public class StatsCard extends RoundedPanel {

    private JLabel iconLabel;
    private JLabel valueLabel;
    private JLabel titleLabel;
    private Color  accentColor;

    // public StatsCard(String icon, String title, String value, Color accent) {
    public StatsCard(String title, String value, Color accent) {
        super(ThemeManager.BG_CARD, 14);
        this.accentColor = accent;
        withBorder(new Color(accent.getRed(), accent.getGreen(), accent.getBlue(), 60));

        setLayout(new BorderLayout(6, 4));
        setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        // iconLabel = new JLabel(icon);
        // iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        // iconLabel.setForeground(accent);

        valueLabel = new JLabel(value);
        valueLabel.setFont(ThemeManager.FONT_LARGE);
        valueLabel.setForeground(Color.WHITE);

        titleLabel = new JLabel(title.toUpperCase());
        titleLabel.setFont(ThemeManager.FONT_SMALL);
        titleLabel.setForeground(ThemeManager.TEXT_SECONDARY);

        JPanel center = new JPanel(new GridLayout(2, 1, 0, 2));
        center.setOpaque(false);
        center.add(valueLabel);
        center.add(titleLabel);

        add(center, BorderLayout.CENTER);
    }

    public void setValue(String value) {
        valueLabel.setText(value);
    }

}
