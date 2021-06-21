package top.littlefogcat.demolist.learn.annotation.annotations;

import java.lang.annotation.*;

/**
 * 标记一个类为一个组件，组件中的字段可以通过@Autowired注解实现自动注入。
 *
 * @see Autowired
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
}
