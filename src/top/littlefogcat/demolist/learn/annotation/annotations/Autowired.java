package top.littlefogcat.demolist.learn.annotation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记@Autowired的字段可以实现自动注入。
 * 需要在组件中使用。
 *
 * @see Component
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
    String value();
}
