package mail.app.service;

import java.io.IOException;

public interface TxtFileReader {
    public String getString(String filePath) throws IOException;
}
