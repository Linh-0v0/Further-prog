package App;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;

class ReadFileFromResourcesUsingGetResource {
    public static void main(final String[] args) throws IOException
    {
        String basePath = new File("").getAbsolutePath();
        System.out.println(basePath);

        String path = new File("src/main/resources/default.csv")
                .getAbsolutePath();
        System.out.println(path);
    }
}