package mail.app.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import mail.app.service.TxtFileReader;
import org.springframework.stereotype.Service;


@Service
public class TxtFileReaderImpl implements TxtFileReader {
    File inputFile;

    @Override
    public String getString(String filePath) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + filePath);
        }
        StringBuilder sb = new StringBuilder();
        try (InputStreamReader  streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader bReader = new BufferedReader(streamReader)) {
            String bodyMessage;
            while ((bodyMessage = bReader.readLine()) != null) {
                sb.append(bodyMessage).append("\n");
            }
        } catch (Exception e) {
            System.err.println("Can't find file: " + filePath);
        }
        return sb.toString();
    }
}
