package top.littlefogcat.demolist.game.dkp;

import top.littlefogcat.demolist.utils.FileUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WixiaBangpaiDKP {
    public static final String FILE = "C:\\WeGameApps\\天涯明月刀\\DKPData\\BangPai_DKPModifyRecord.txt";
    public static final String OUT_FILE = OUT_FILE();

    private static String OUT_FILE() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String formatted = df.format(new Date());
        return "C:\\WeGameApps\\天涯明月刀\\DKPData\\DKP" + formatted + ".txt";
    }

    public void createWeeklyDKP() {
        List<String> lines = FileUtil.readTextFileByLine(FILE);
        List<MemberRecord> memberRecords = new ArrayList<>();
        MemberRecord member = null;
        for (String line : lines) {
            if (line.length() == 0) {
                memberRecords.add(member);
            } else if (line.charAt(0) != '2') {
                member = new MemberRecord(line);
            } else {
                checkContent(member, line);
            }
        }

        for (MemberRecord record : memberRecords) {
            System.out.println(record);
        }

        FileUtil.writeLinesToFile(OUT_FILE, memberRecords);
    }

    private void checkContent(MemberRecord member, String line) {
        int pos;
        if (line.contains("醉侠")) {
            member.zuixia++;
        } else if ((pos = line.indexOf("聚饮")) >= 0) {
            int left = line.indexOf('（', pos);
            int right = line.indexOf('次', left);
            String times = line.substring(left + 1, right);
            member.drink += Integer.parseInt(times);
        } else if ((pos = line.indexOf("委任")) >= 0) {
            int left = line.indexOf('（', pos);
            int right = line.indexOf('次', left);
            String times = line.substring(left + 1, right);
            member.weiren += Integer.parseInt(times);
        } else if ((pos = line.indexOf("玉石")) >= 0) {
            int left = line.indexOf('（', pos);
            int right = line.indexOf('个', left);
            String times = line.substring(left + 1, right);
            member.up += Integer.parseInt(times);
        } else if ((pos = line.indexOf("资金")) >= 0) {
            int left = line.indexOf('（', pos);
            int right = line.indexOf('金', left);
            String times = line.substring(left + 1, right);
            member.up += Integer.parseInt(times);
        }
    }

    public static void main(String[] args) {
        WixiaBangpaiDKP dkp = new WixiaBangpaiDKP();
        dkp.createWeeklyDKP();
    }
}
