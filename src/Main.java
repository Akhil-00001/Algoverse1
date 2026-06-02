import java.io.File;
import javax.swing.*;
import managers.SessionManager;
import ui.MainFrame;
import utils.ThemeManager;


public class Main {
    public static void main(String[] args) {
        new File("data").mkdirs();
        new File("data/users.txt").getParentFile().mkdirs();
        new File("data/scores.txt").getParentFile().mkdirs();
        
        ThemeManager.applyDarkTheme();
        SwingUtilities.invokeLater(() -> {
            
            SessionManager.getInstance().loadRememberedSession();

            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
