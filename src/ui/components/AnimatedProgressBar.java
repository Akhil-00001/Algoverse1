package ui.components;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import utils.ThemeManager;


public class AnimatedProgressBar extends JComponent {

    private float current = 0f;   
    private float target  = 0f;   
    private Color trackColor = ThemeManager.BG_SURFACE;
    private Color fillColor1 = ThemeManager.ACCENT;
    private Color fillColor2 = ThemeManager.ACCENT_CYAN;
    private int   arc = 8;
    private String label = null;
    private Timer timer;

    public AnimatedProgressBar() {
        setPreferredSize(new Dimension(200, 18));
        timer = new Timer(16, e -> {
            float diff = target - current;
            if (Math.abs(diff) < 0.001f) {
                current = target;
                timer.stop();
            } else {
                current += diff * 0.08f;
            }
            repaint();
        });
    }

    
    public void setProgress(float value) {
        target = Math.clamp(value, 0f, 1f); 

        if (!timer.isRunning()) timer.start();
    }

    
    public void setProgressImmediate(float value) {
        timer.stop();
        current = target = Math.clamp(value, 0f, 1f); 
        repaint();
    }

    public void setLabel(String label) { this.label = label; repaint(); }
    public void setFillColors(Color c1, Color c2) { fillColor1=c1; fillColor2=c2; repaint(); }
    public void setTrackColor(Color c) { trackColor = c; repaint(); }
    public void setArc(int arc)        { this.arc = arc; repaint(); }

    public float getProgress()         { return target; }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth(), h = getHeight();

        g2.setColor(trackColor);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, arc, arc));
        int fillW = (int)(w * current);
        if (fillW > 0) {
            Shape clip = g2.getClip();
            g2.setClip(new RoundRectangle2D.Float(0, 0, w, h, arc, arc));
            g2.setPaint(new GradientPaint(0, 0, fillColor1, fillW, 0, fillColor2));
            g2.fillRect(0, 0, fillW, h);
            g2.setClip(clip);
        } 

        if (label != null) {
            g2.setFont(ThemeManager.FONT_SMALL);
            g2.setColor(Color.WHITE);
            FontMetrics fm = g2.getFontMetrics();
            int tx = (w - fm.stringWidth(label)) / 2;
            int ty = (h + fm.getAscent() - fm.getDescent()) / 2;
            g2.drawString(label, tx, ty);
        }

        g2.dispose();
    }
}
