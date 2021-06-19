package top.littlefogcat.demolist.proxy;

public class UserA {

    public void visitGoogle() {
        // I want to visit Google, but GFW stopped me!
        // I can't visit Google directly, so I need a proxy!
        ProxyA proxy = new ProxyA(new Google());
        proxy.visit();
    }

    public void visitTwitter() {
        ProxyA proxy = new ProxyA(new Twitter());
        proxy.visit();
    }

    public static void main(String[] args) {
        UserA user = new UserA();
        user.visitGoogle();
        user.visitTwitter();
    }
}
