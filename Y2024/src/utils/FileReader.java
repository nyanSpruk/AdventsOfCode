package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader {
    public static List<String> readPublic(int day, int part) throws IOException {
        String formattedDay = String.format("%02d", day);
        String path = "./src/day" + formattedDay + "/data/p.txt";
        return Files.readAllLines(Path.of(path));
    }

    public static List<String> readHidden(int day) throws IOException {
        String formattedDay = String.format("%02d", day);
        String path = "./src/day" + formattedDay + "/data/hidden.txt";
        return Files.readAllLines(Path.of(path));
    }
}
