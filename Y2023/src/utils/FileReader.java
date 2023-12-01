package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader {
    public static List<String> readLines(String inputFolder, int day, int part) throws IOException
    {
        String path = "./src/data/"+ inputFolder + "/day" + day + "_"+part+".txt";
        return Files.readAllLines(Path.of(path));
    }
}
