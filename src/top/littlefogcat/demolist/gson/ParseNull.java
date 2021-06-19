package top.littlefogcat.demolist.gson;

import com.google.gson.Gson;

import java.util.List;

public class ParseNull {
    public static class Student {
        public String name;
        public int age;

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
//        Gson gson = new Gson();
//        Student student = gson.fromJson("{}", Student.class);
//        System.out.println(student);


        Gson gson = new Gson();
        Object student = gson.fromJson("{}", Object.class);
        System.out.println(student.getClass());
    }
}
