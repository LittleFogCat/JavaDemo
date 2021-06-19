package top.littlefogcat.demolist.annotation;

public class AnnotationTest {
    @Autowired("animal_panda")
    public Animal animal;

    AnnotationTest() throws Exception {
        Injector.inject(this);
    }

    public void say() {
        animal.say();
    }

    public static void main(String[] args) throws Exception {
        Injector.init(AnnotationTest.class.getClassLoader(), "top.littlefogcat.demolist.annotation");
        AnnotationTest test = new AnnotationTest();
        test.say();
    }
}
