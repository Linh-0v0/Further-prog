package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetResource {
    //Get the relative path of files in resources (CSV Files)
    public String getResourceFile(final String fileName) {
        URL url = this.getClass()
                .getClassLoader()
                .getResource(fileName);

        if (url == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }

        return url.getFile();
    }
}


