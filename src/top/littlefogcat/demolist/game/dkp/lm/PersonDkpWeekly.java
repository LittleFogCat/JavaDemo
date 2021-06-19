package top.littlefogcat.demolist.game.dkp.lm;

public class PersonDkpWeekly {
    public PersonDkpWeekly(String name, String party, int remain, int gain) {
        this.name = name;
        this.party = party;
        this.remain = remain;
        this.gain = gain;
    }

    String name;
    String party;
    int remain;
    int gain;

    @Override
    public String toString() {
        return name + "（" + party + "）：" + gain;
    }

    public String toStringDetail() {
        return toString() + " / 争锋：" + zhengFeng() +
                "，掠夺：" + lveDuo() +
                "，联赛：" + lianSai();
    }

    public int lveDuo() {
        return gain % 10;
    }

    public int lianSai() {
        return gain / 100;
    }

    public int zhengFeng() {
        return (gain - lveDuo() - lianSai() * 100) / 10;
    }

}
