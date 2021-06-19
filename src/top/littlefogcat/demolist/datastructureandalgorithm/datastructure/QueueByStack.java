package top.littlefogcat.demolist.datastructureandalgorithm.datastructure;

import java.util.Stack;

/**
 * 栈实现队列
 */
public class QueueByStack {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (stack1.empty()) return -1;
        while (stack1.size() > 1) stack2.push(stack1.pop());
        int poll = stack1.pop();
        while (stack2.size() > 0) stack1.push(stack2.pop());
        return poll;
    }

    @Override
    public String toString() {
        return stack1.toString() + ", " + stack2.toString();
    }
}

class Test {
    public static void main(String[] args) {
        QueueByStack queue = new QueueByStack();
        queue.deleteHead();
        System.out.println(queue);
        queue.appendTail(5);
        System.out.println(queue);
        queue.appendTail(2);
        System.out.println(queue);
        queue.deleteHead();
        System.out.println(queue);
        queue.deleteHead();
        System.out.println(queue);
        queue.deleteHead();
    }
}
