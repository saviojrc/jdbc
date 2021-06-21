package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Manipulador {

    private static String pathFile;

    public static Properties getProp() throws IOException {
        try {
            pathFile = "src/main/resources/connection.properties";
            Properties props = new Properties();
            FileInputStream file = new FileInputStream(pathFile);
            props.load(file);
            return props;

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e.getMessage());
        }

    }

}
