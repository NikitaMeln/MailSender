package mail.app.storage;

import java.io.File;
import org.springframework.stereotype.Repository;

@Repository
public class DataStorage {
    private static String mail;
    private static String password;
    private static String command;

    private static File file;

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        DataStorage.mail = mail;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DataStorage.password = password;
    }

    public static String getCommand() {
        return command;
    }

    public static void setCommand(String command) {
        DataStorage.command = command;
    }

    public static File getFile() {
        return file;
    }

    public static void setFile(File file) {
        DataStorage.file = file;
    }

    public static void saveData(String newMail, String newPassword, String newCommand, File newFile) {
        mail = newMail;
        password = newPassword;
        command = newCommand;
        file = newFile;
    }
}
