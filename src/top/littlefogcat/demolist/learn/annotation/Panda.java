package top.littlefogcat.demolist.learn.annotation;

@InjectName("animal_panda")
public class Panda implements Animal {
    @Override
    public void say() {
        System.out.println("I am a panda");
    }
}
