package top.littlefogcat.demolist.tools.stock;

import java.util.Random;

public class Stock {
    private static final Random RANDOM = new Random();
    private int day = 0;
    private double price = 1;
    private double stocks = 0;

    //    public void next() {
//        int ni = RANDOM.nextInt(100);
//        double rate;
//        if (ni < 50) {
//            rate = round((1 - RANDOM.nextDouble()) * 10);
//        } else {
//            rate = -round((1 - RANDOM.nextDouble()) * 9.3666); // 9.3766
//        }
//        price = price * (1 + rate / 100);
//        stocks += 1 / price;
//        day++;
//    }

    public void next() {
        int ni = RANDOM.nextInt(100);
        double rate;
        if (ni < 50) {
            rate = round((1 - RANDOM.nextDouble()) * 10);
        } else {
            rate = -round((1 - RANDOM.nextDouble()) * 9.3666); // 9.3766
        }
        price = price * (1 + rate / 100);
        stocks += 1 / price;
        day++;
    }

    @Override
    public String toString() {
        return "day " + day + ", price = " + price + ", stocks = " + stocks;
    }

    public static void main(String[] args) {
        Stock stock = new Stock();
        for (int i = 0; i < 1000000; i++) {
            stock.next();
        }
        System.out.println(stock);
    }

    private static void test() {
        int down = 0;
        int up = 0;
        for (int i = 0; i < 100000; i++) {
            Stock stock = new Stock();
            for (int j = 0; j < 1000; j++) {
                stock.next();
            }
            if (stock.price <= 1) {
                down++;
            } else up++;
        }
        System.out.println("up: " + up + ", down" + down + "");
    }

    public static double round(double d) {
        return Math.round(d * 100) / 100d;
    }
}
