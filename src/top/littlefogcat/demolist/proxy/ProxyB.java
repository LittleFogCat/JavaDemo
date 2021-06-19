package top.littlefogcat.demolist.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyB implements InvocationHandler {
    private final Server target;

    ProxyB(Server target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("访问了代理服务器！");
        return method.invoke(target, args);
    }
}
