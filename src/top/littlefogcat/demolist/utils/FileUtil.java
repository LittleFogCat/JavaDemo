package top.littlefogcat.demolist.utils;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class FileUtil {

    public static List<String> readTextFileByLine(String file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void writeLinesToFile(String file, List<?> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Object line : lines) {
                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void checkAndCreateFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            try {
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
