package mail.app.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.springframework.stereotype.Service;
import mail.app.service.TxtFileReader;


@Service
public class TxtFileReaderImpl implements TxtFileReader {

    @Override
    public String getString(String filePath) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bReader = new BufferedReader(new FileReader(filePath))) {
            String bodyMessage;
            while ((bodyMessage = bReader.readLine()) != null) {
                sb.append(bodyMessage).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't find file: " + filePath);
        } catch (IOException e) {
            System.err.println("Can't read file: " + filePath);
        }
        return sb.toString();
    }
}
