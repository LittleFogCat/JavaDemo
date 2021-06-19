package top.littlefogcat.demolist.game.dkp.lm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static top.littlefogcat.demolist.utils.FileUtil.*;

public class LianmengDKPWeekly {
    public static final String PATH = "C:\\WeGameApps\\天涯明月刀\\DKPData\\LianMeng_DKP.txt";
    public static final String OUT_PATH = "C:\\WeGameApps\\天涯明月刀\\DKPData\\records\\";

    public static void main(String[] args) {
        List<PersonDkpWeekly> dkpDataList = readData(); // 读取原始数据
        dkpDataList.sort(Comparator.comparing(o -> o.party)); // 按照party排序
        output(dkpDataList); // 输出到txt文件
    }

    private static List<PersonDkpWeekly> readData() {
        List<PersonDkpWeekly> dkpDataList = new ArrayList<>();
        List<String> lines = readTextFileByLine(PATH);
        if (!"PVP".equals(lines.get(1))) {
            throw new RuntimeException("Error - Not PVP Data!");
        }
        for (int i = 3; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] split = line.split("\\s+\\t+");
            String name = split[0];
            String party = split[2];
            String remain = split[3];
            String gain = split[4];
            PersonDkpWeekly dkpData = new PersonDkpWeekly(name, party, Integer.parseInt(remain), Integer.parseInt(gain));
            dkpDataList.add(dkpData);
        }
        return dkpDataList;
    }

    private static void output(List<PersonDkpWeekly> personDkpList) {
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        String filename = OUT_PATH + "DKP-" + dateStr + ".txt";
        checkAndCreateFile(filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // 统计个人数据
            writer.write("DKP统计-个人\n日期：" + dateStr + "\n");
            for (PersonDkpWeekly person : personDkpList) {
                writer.write(person.toStringDetail());
                writer.newLine();
            }
            writer.newLine();
            // 统计party数据
            List<PartyWeekly> partyWeeklyList = PartyWeekly.generate(personDkpList);
            writer.write("总计-争锋\n");
            for (PartyWeekly party : partyWeeklyList) {
                writer.write(party.partyName + "：" + party.partyZF + "    ");
            }
            writer.newLine();
            writer.write("总计-掠夺\n");
            for (PartyWeekly party : partyWeeklyList) {
                writer.write(party.partyName + "：" + party.partyLD + "    ");
            }
            writer.newLine();
            writer.write("总计-联赛\n");
            for (PartyWeekly party : partyWeeklyList) {
                writer.write(party.partyName + "：" + party.partyLS + "    ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
