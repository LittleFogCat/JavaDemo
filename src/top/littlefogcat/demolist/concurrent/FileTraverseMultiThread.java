package top.littlefogcat.demolist.concurrent;

import top.littlefogcat.demolist.utils.Timer;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 多线程遍历文件
 */
public class FileTraverseMultiThread {
    private static final String ROOT = "c:/repositories";

    public static void main(String[] args) {
        Timer.printExecTime("single-thread", () -> {
            int count = traverseFileSystemSingleThread();
            System.out.println("count: "+count);
        });
    }

    public static int traverseFileSystemSingleThread() {
        Queue<File> fileQueue = new LinkedList<>();
        File root = new File(ROOT);
        fileQueue.offer(root);
        int count = 0;
        while (fileQueue.size() > 0) {
            File f = fileQueue.poll();
            count++;
            File[] children = f.listFiles();
            if (children != null) {
                for (File child : children) {
                    fileQueue.offer(child);
                }
            }
        }
        return count;
    }

    public static int traverseFileSystemMultiThread() {
        Queue<File> fileQueue = new LinkedList<>();
        File root = new File(ROOT);
        fileQueue.offer(root);
        int count = 0;
        while (fileQueue.size() > 0) {
            File f = fileQueue.poll();
            count++;
            File[] children = f.listFiles();
            if (children != null) {
                for (File child : children) {
                    fileQueue.offer(child);
                }
            }
        }
        return count;
    }
}
