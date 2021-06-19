package top.littlefogcat.demolist.proxy;

public class Twitter implements Server {
    @Override
    public void visit() {
        System.out.println("访问了Twitter！");
    }
}
