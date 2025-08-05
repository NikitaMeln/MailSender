package mail.app.stream;

import java.io.OutputStream;
import javax.swing.*;

public class CustomOutputStream extends OutputStream {
    private final JTextArea textArea;

    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) {
        SwingUtilities.invokeLater(() -> textArea.append(String.valueOf((char) b)));
    }
}
