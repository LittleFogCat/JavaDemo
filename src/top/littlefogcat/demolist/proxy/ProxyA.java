package top.littlefogcat.demolist.proxy;

public class ProxyA implements Server {
    private Server target;

    ProxyA(Server target) {
        this.target = target;
    }

    @Override
    public void visit() {
        System.out.println("访问了代理服务器");
        target.visit();
    }
}
