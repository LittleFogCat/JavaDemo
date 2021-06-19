package top.littlefogcat.demolist.stock;

public class DintTou {
    public static double[] points = {3901, 3640, 3789, 3485, 3172, 4862, 2157, 2605, 2633, 2996, 2755, 2798, 3600, 3953, 1364, 855};

    public static void main(String[] args) {
        double stock = 0;
        for (double point : points) {
            stock += 10000 / point;
        }
        System.out.println("股：" + stock);
        double totalInput = 10000 * points.length;
        double finalMoney = stock * 5930;
        double yearIncomeRate = Math.pow(finalMoney / totalInput, 1d / points.length);
        System.out.println("总投入：" + totalInput);
        System.out.println("最终资产：" + finalMoney);
        System.out.println("年化收益率：" + yearIncomeRate);
    }
}
