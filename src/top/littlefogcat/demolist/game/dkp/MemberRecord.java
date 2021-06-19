package top.littlefogcat.demolist.game.dkp;

public class MemberRecord {
    String name;
    int weiren;
    int drink;
    int up;
    int zuixia;

    MemberRecord(String name) {
        this.name = name;
    }

    int dkp() {
        return (int) (weiren * 1.5 + drink * 1.1 + up / 10d + zuixia * 50);
    }

    int oldDkp() {
        return (int) (weiren / 10d + drink + up / 10d + zuixia);
    }

    @Override
    public String toString() {
        MemberRecord record = this;
        return record.name + "（DKP：" + dkp() + "）\n" +
                "委任：" + record.weiren + "，" +
                "喝酒：" + record.drink + "，" +
                "醉侠：" + record.zuixia + "，" +
                "上缴资金和玉石：" + record.up + "\n";
    }
}
