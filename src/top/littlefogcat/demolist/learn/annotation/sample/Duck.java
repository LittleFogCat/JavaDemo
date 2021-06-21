package top.littlefogcat.demolist.learn.annotation.sample;

import top.littlefogcat.demolist.learn.annotation.annotations.Bean;

@Bean(name = Constants.BEAN_DUCK)
public class Duck implements Animal {
    @Override
    public void say() {
        System.out.println("I am duck@" + hashCode());
    }
}
