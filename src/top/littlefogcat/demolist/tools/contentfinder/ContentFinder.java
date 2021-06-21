package top.littlefogcat.demolist.tools.contentfinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class ContentFinder {
    private String findString;
    private String rootPath;
    private Set<String> exclude;
    private Predicate<String> filter;

    public ContentFinder(String findString, String rootPath, String... exclude) {
        this.findString = findString;
        this.rootPath = rootPath;
        this.exclude = new HashSet<>(Arrays.asList(exclude));
    }

    public Result find() {
        File root = new File(this.rootPath);
        Result result = new Result();
        find(root, result);
        return result;
    }

    private void find(File file, Result result) {
        String filename = file.getName();
        if (exclude.contains(filename)) return;
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children == null) return;
            for (File child : children) {
                find(child, result);
            }
        } else if ("text/plain".equals(probeFileContentType(file.getAbsolutePath()))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                final int[] lineNum = {0};
                reader.lines()
                        .map(s -> {
                            lineNum[0]++;
                            return s.trim();
                        })
                        .filter(line -> {
                            if (filter != null) return filter.test(line);
                            // 空白或注释行
                            return !line.isEmpty() &&
                                    !line.startsWith("//") &&
                                    !line.startsWith("/*") &&
                                    !line.startsWith("*") &&
                                    !line.startsWith("<!--");
                        })
                        .forEach(line -> {
                            if (line.contains(findString)) {
                                result.addResult(file.getAbsolutePath(), lineNum[0], line);
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String probeFileContentType(String file) {
        Path p = Paths.get(file);
        try {
            return Files.probeContentType(p);
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
        return "unknown";
    }

    public class Result {
        public List<ResultItem> results = new ArrayList<>();

        public void addResult(String file, int line, String lineCode) {
            results.add(new ResultItem(file, line, lineCode));
        }

        public void print() {
            StringBuilder sb = new StringBuilder();
            sb.append("Found ").append(results.size()).append(" result for string `")
                    .append(findString).append('`')
                    .append(" in path [").append(rootPath).append("]\n");
            for (ResultItem result : results) {
                sb.append(result.file).append(':').append(result.line)
                        .append(", code=\"").append(result.lineCode).append('\"').append('\n');
            }
            System.out.println(sb);
        }

        public class ResultItem {
            String file;
            int line;
            String lineCode;

            public ResultItem(String file, int line, String lineCode) {
                this.file = file;
                this.line = line;
                this.lineCode = lineCode;
            }

            @Override
            public String toString() {
                return "Result{" +
                        "file='" + file + '\'' +
                        ", line=" + line +
                        '}';
            }
        }

    }
}
