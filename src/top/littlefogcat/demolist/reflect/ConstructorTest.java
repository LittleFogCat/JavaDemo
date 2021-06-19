package top.littlefogcat.demolist.reflect;

import java.io.*;

public class ConstructorTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        final String path = "C:\\Users\\littlefogcat\\IdeaProjects\\DemoList\\src\\top\\littlefogcat\\demolist\\reflect\\serial.txt";
        A a = new A("bob");
        System.out.println(a);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeObject(a);
        oos.close();

        File file = new File(path);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        A read = (A) ois.readObject();
        System.out.println(read);
    }
}

class A implements Serializable {
    String name;
    B b = new B(this);

    A(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", b=" + b +
                '}';
    }
}

class B implements Serializable {
    A a;

    public B(A a) {
        this.a = a;
    }

}