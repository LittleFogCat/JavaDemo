package top.littlefogcat.demolist.dynamic;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collections;

/**
 * 动态编译代码
 */
public class DynamicCodeCompiler {
    private String code;
    private String className;
    private String folderPath;

    /**
     * @param code       需要加载的代码，字符串形式
     * @param className  加载的类名
     * @param folderPath 加载的类保存的文件夹
     */
    public DynamicCodeCompiler(String code, String className, String folderPath) {
        this.code = code;
        this.className = className;
        this.folderPath = folderPath;
    }

    private String compile() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StringWriter writer = new StringWriter();
        JavaFileObject fileObject = new DynamicLoadFileObject();
        compiler.getTask(
                writer,
                null,
                null,
                Arrays.asList("-d", folderPath),
                null,
                Collections.singletonList(fileObject)
        ).call();
        return writer.toString();
    }

    private Class<?> loadClass() throws MalformedURLException, ClassNotFoundException {
        URL classURL = new URL("file:" + folderPath);
        ClassLoader classLoader = new URLClassLoader(new URL[]{classURL});
        return classLoader.loadClass(className);
    }

    public Class<?> compileAndLoadClass(boolean printOutput) throws MalformedURLException, ClassNotFoundException {
        String output = compile();
        if (printOutput) System.out.println("DynamicCodeCompiler/compile output: " + output);
        return loadClass();
    }

    public Class<?> compileAndLoadClass() throws MalformedURLException, ClassNotFoundException {
        return compileAndLoadClass(false);
    }

    private class DynamicLoadFileObject extends SimpleJavaFileObject {

        private DynamicLoadFileObject() {
            super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension),
                    Kind.SOURCE);
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }
}
