package top.littlefogcat.demolist.proxy;

public class Google implements Server {

    @Override
    public void visit() {
        System.out.println("访问了Google!");
    }
}
