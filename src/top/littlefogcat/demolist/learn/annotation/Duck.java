package top.littlefogcat.demolist.learn.annotation;

@InjectName("animal_duck")
public class Duck implements Animal {
    @Override
    public void say() {
        System.out.println("I am a duck");
    }
}
