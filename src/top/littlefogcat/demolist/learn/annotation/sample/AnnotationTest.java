package top.littlefogcat.demolist.learn.annotation.sample;

import top.littlefogcat.demolist.learn.annotation.annotations.Autowired;
import top.littlefogcat.demolist.learn.annotation.annotations.Component;
import top.littlefogcat.demolist.learn.annotation.ObjectsContainer;

@Component
public class AnnotationTest {
    @Autowired(Constants.BEAN_PANDA)
    public Animal panda;
    @Autowired(Constants.BEAN_DUCK)
    public Animal duck;

    public void say() {
        panda.say();
        duck.say();
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        ObjectsContainer container = ObjectsContainer.get(Constants.PACKAGE_NAME);
        AnnotationTest test = container.getComponent(AnnotationTest.class);
        test.say();
    }
}
