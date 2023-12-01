package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader {
    public static List<String> readPublic(int day, int part) throws IOException
    {
        String path = "./src/day" + day + "/data/p" + part + ".txt";
        return Files.readAllLines(Path.of(path));
    }

    public static List<String> readHidden(int day) throws IOException {
        String path = "./src/day" + day + "/data/hidden.txt";
        return Files.readAllLines(Path.of(path));
    }
}
