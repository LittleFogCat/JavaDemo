自动注入注解示例

通过`@Autowired`注解进行注入。

用法示例：

```java
// 组件类，通过@Component注解标记
@Component
class MyComponent {
    // 通过@Autowired注解注入字段
    @Autowired("my_name")
    MyBean bean;
}

// 注入类，通过@Bean注解标记
@Bean("my_name")
class MyBean {
    public void say() {
        System.out.println("I am MyBean.");
    }
}

class Main {
    public static void main(String[] args) {
        // 通过包名初始化容器，并执行注入
        ObjectsContainer container = ObjectsContainer.get("top.littlefogcat.demo");
        // 根据Class获取组件对象
        container.getComponent(MyComponent.class);
        // 获取注入的bean
        MyBean bean = container.bean;
        bean.say();
    }
}
```

输出：
> I am MyBean.

详见代码。