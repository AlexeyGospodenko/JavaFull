package lesson4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class FileHistoryService implements HistoryService {

    private static FileHistoryService instance;
    private final String path = "history.txt";

    public static FileHistoryService getInstance() {

        return instance == null ? new FileHistoryService() : instance;
    }

    private FileHistoryService() {

    }

    public void save(List<String> chat) {
        try {
            Files.write(Paths.get(path), chat, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> load() {

        try {
            return Files.newBufferedReader(Paths.get(path))
                    .lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }
}