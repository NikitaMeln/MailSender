package mail.app.ui.impl;

import java.awt.*;
import java.io.File;
import java.io.PrintStream;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import mail.app.controller.MailController;
import mail.app.storage.DataStorage;
import mail.app.stream.CustomOutputStream;
import mail.app.ui.UserPanel;
import org.springframework.stereotype.Component;

@Component
public class UserPanelImpl implements UserPanel {
    private final MailController mailController;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField commandField;
    private JTextField filePathField;

    private JTextArea logArea;

    public UserPanelImpl(MailController mailController) {
        this.mailController = mailController;
    }

    @Override
    public void start() {
        JFrame frame = new JFrame();
        frame.setTitle("Login Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        emailField.setColumns(15);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordField.setColumns(15);

        JLabel commandLabel = new JLabel("Command:");
        commandField = new JTextField();
        commandField.setColumns(15);

        JLabel pathLabel = new JLabel("Path to the Excel file:");
        commandField = new JTextField();
        commandField.setColumns(15);

        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        commandPanel.add(commandLabel);
        commandPanel.add(commandField);

        JPanel pathToFilePanel = new JPanel();
        pathToFilePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        filePathField = new JTextField();
        filePathField.setColumns(15);
        pathToFilePanel.add(pathLabel);
        pathToFilePanel.add(filePathField);
        JButton openMenuItem = new JButton("Open");
        pathToFilePanel.add(openMenuItem);

        openMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filePathField.setText(selectedFile.getAbsolutePath());
            }
        });

        JButton startButton = new JButton("Start");

        frame.add(emailPanel);
        frame.add(passwordPanel);
        frame.add(commandPanel);
        frame.add(pathToFilePanel);
        frame.add(startButton);
        frame.setVisible(true);

        startButton.addActionListener(e -> {
            startSendingMail();
        });
    }

    private void openLogPanel() {
        JFrame logFrame = new JFrame("log");
        logFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        logFrame.setSize(400, 200);

        logArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(logArea);
        DefaultCaret caret = (DefaultCaret) logArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        logFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        logFrame.setVisible(true);

        PrintStream printStream = new PrintStream(new CustomOutputStream(logArea));
        System.setOut(printStream);
    }

    private void startSendingMail() {
        Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
                openLogPanel();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                DataStorage.saveData(emailField.getText(),
                        password,
                        commandField.getText(),
                        new File(filePathField.getText()));
                mailController.starCommand();
            }
        });
        taskThread.start();
    }
}
