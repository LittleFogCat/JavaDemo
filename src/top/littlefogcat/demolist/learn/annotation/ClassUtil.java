package top.littlefogcat.demolist.learn.annotation;

import java.io.File;
import java.net.URL;
import java.util.*;

@SuppressWarnings("ALL")
public class ClassUtil {
    public static List<String> scanClasses(ClassLoader classLoader, String packageName) throws Exception {
        String packageNameForUrl = packageName.replace(".", "/");
        URL url = classLoader.getResource(packageNameForUrl);
        File root = new File(url.toURI());
        List<String> classList = new ArrayList<>();
        scanClasses(root, packageName, classList);
        return classList;
    }

    private static void scanClasses(File root, String packageName, List<String> result) {
        for (File child : root.listFiles()) {
            String name = child.getName();
            if (child.isDirectory()) {
                scanClasses(child, packageName + "." + name, result);
            } else if (name.endsWith(".class")) {
                String className = packageName + "." + name.replace(".class", "");
                result.add(className);
            }
        }
    }
}
