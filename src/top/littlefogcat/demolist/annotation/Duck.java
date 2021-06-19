package top.littlefogcat.demolist.annotation;

@InjectName("animal_duck")
public class Duck implements Animal {
    @Override
    public void say() {
        System.out.println("I am a duck");
    }
}
