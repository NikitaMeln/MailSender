package mail.app;

import javax.swing.*;
import mail.app.config.AppConfig;
import mail.app.ui.UserPanel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private final UserPanel userPanel;

    public Main(UserPanel userPanel) {
        this.userPanel = userPanel;
    }

    public static void main(String[] args) {
        // -Вызвать наш фрейм ввести данные
        // -Фрейм запускает программу с введенными данными
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserPanel userPanel = context.getBean(UserPanel.class);

        Main app = new Main(userPanel);
        SwingUtilities.invokeLater(app::run);
    }

    public void run() {
        userPanel.start();
    }
}
