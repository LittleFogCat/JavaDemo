package top.littlefogcat.demolist.learn.reflect.pojo;

public class Person {
    protected String name = "default name";

    @Override
    public String toString() {
        return "Person(name='" + name + "')";
    }

    public static void printPerson(Person person) {
        System.out.println(person == null ? "No person" : "Person:" + person);
    }
}
