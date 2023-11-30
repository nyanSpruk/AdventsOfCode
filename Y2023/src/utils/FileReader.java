package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader {
    public static List<String> readLines(String inputFolder, int day) throws IOException
    {
        String path = "./src/data/"+ inputFolder + "/day" + day + ".txt";
        return Files.readAllLines(Path.of(path));
    }
}
