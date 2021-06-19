//package top.littlefogcat.demolist.knowledgesystem.jvm.classloader;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class MyClassLoader extends ClassLoader {
//    private String dirPath;
//
////    @Override
//    public String getName() {
//        return "MyClassLoader";
//    }
//
//    public MyClassLoader(String dirPath) {
//        if (!dirPath.endsWith("/") && !dirPath.endsWith("\\")) {
//            dirPath += "/";
//        }
//        this.dirPath = dirPath;
//    }
//
//    @Override
//    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        String filePath = dirPath + name.replace('.', '/') + ".class";
//        byte[] b;
//        Path path;
//        try {
//            path = Paths.get(new URI(filePath));
//            b = Files.readAllBytes(path);
//            return defineClass(name, b, 0, b.length);
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
//
//class Main {
//    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
////        loadOutsideClass();
////        loadInProjectClass();
////        Main.class.getClassLoader().loadClass("file:/C:/Users/littlefogcat/AndroidStudioProjects/architecture-sample/todoapp/app/build/intermediates/classes/mock/debug/com/example/android/architecture/blueprints/todoapp/tasks/TasksFilterType.class");
//        String classPath = "C:\\Users\\littlefogcat\\IdeaProjects\\DemoList\\out\\production\\DemoList";
//        String className = "top.littlefogcat.demolist.Global";
//        MyClassLoader mcl = new MyClassLoader(classPath);
//        Class<?> loadedClass = mcl.loadClass(className);
//        System.out.println(loadedClass.getName());
//        System.out.println(loadedClass.getClassLoader().getName());
//    }
//
//    @SuppressWarnings({"rawtypes"})
//    private static void loadOutsideClass() throws ClassNotFoundException {
//        MyClassLoader classLoader = new MyClassLoader("file:/C:/Users/littlefogcat/AndroidStudioProjects/architecture-sample/todoapp/app/build/intermediates/classes/mock/debug");
//        String className = "com.example.android.architecture.blueprints.todoapp.tasks.TasksFilterType";
//        Class cls = classLoader.loadClass(className);
//        System.out.println(cls.getClassLoader().getName());
//        System.out.println("myclassloader's parent is " + classLoader.getParent());
//        System.out.println("myclassloader's parent's parent is " + classLoader.getParent().getParent());
//        System.out.println("myclassloader's parent's parent's parent is " + classLoader.getParent().getParent().getParent());
//
//        System.out.println("String's classloader is " + String.class.getClassLoader());
//        System.out.println(MyClassLoader.class.getClassLoader());
//        System.out.println(MyClassLoader.class.getClassLoader().getClass().getClassLoader());
//    }
//
//    @SuppressWarnings({"rawtypes"})
//    private static void loadInProjectClass() throws ClassNotFoundException {
//        MyClassLoader classLoader = new MyClassLoader("C:\\Users\\littlefogcat\\IdeaProjects\\DemoList\\out\\production\\DemoList");
//        String className = "top.littlefogcat.demolist.Global";
//        Class cls = classLoader.loadClass(className);
//        System.out.println(cls.getClassLoader().getName());
//    }
//}