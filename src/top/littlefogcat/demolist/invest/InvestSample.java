package top.littlefogcat.demolist.invest;

public class InvestSample {
    private Value income;
    private double rate;
    private Action actionByYear;

    public InvestSample(Value income, double rate, Action actionByYear) {
        this.income = income;
        this.rate = rate;
        this.actionByYear = actionByYear;
    }

    public double after(int year) {
        int value = 0;
        for (int i = 0; i < year; i++) {
            value *= (1 + rate);
            value += income.getValue(i);
            actionByYear.action(i, value);
        }
        return value;
    }
}

class Main {
    public static void main(String[] args) {
        InvestSample sample = new InvestSample(
                year -> {
                    if (year <= 20) return 50 - 0.3 * (year - 10) * (year - 10);
                    else return 20;
                },
                0.08,
                (year, value) -> {
                    System.out.println("第" + year + "年：" + format((Integer) value));
                    return null;
                });
        sample.after(50);
    }

    static String format(int value) {
        if (value < 10000) return value + "万";
        else return value / 10000 + "亿" + (value - value / 10000) + "万";
    }
}
