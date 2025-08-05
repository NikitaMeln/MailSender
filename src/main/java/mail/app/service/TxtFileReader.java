package mail.app.service;

import java.io.IOException;

public interface TxtFileReader {
    String getString(String filePath) throws IOException;
}
