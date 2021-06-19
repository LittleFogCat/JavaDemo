package top.littlefogcat.demolist.proxy;

import java.lang.reflect.Proxy;

public class UserB {

    public void visitGoogle() {
        ProxyB proxy = new ProxyB(new Google());
        // 动态代理
        Server dynamic = (Server) Proxy.newProxyInstance(Google.class.getClassLoader(),
                Google.class.getInterfaces(), proxy);
        dynamic.visit();
    }

    public void visitTwitter() {
        ProxyB proxy = new ProxyB(new Twitter());
        Server dynamicProxy = (Server) Proxy.newProxyInstance(Twitter.class.getClassLoader(),
                Twitter.class.getInterfaces(), proxy);
        dynamicProxy.visit();
    }

    public static void main(String[] args) {
        UserB user = new UserB();
        user.visitGoogle();
        user.visitTwitter();
    }
}
