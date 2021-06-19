package top.littlefogcat.demolist.game.dkp.lm;

import java.util.*;

public class PartyWeekly {
    public String partyName;
    public int partyZF;
    public int partyLD;
    public int partyLS;

    public static List<PartyWeekly> generate(List<PersonDkpWeekly> personList) {
        Map<String, Integer> partySpecMap = new HashMap<>();
        for (PersonDkpWeekly person : personList) {
            int spec = Spec.makeSpec(person.zhengFeng(), person.lveDuo(), person.lianSai());
            partySpecMap.put(person.party, partySpecMap.getOrDefault(person.party, 0) + spec);
        }
        List<PartyWeekly> partyWeeklyList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : partySpecMap.entrySet()) {
            String name = entry.getKey();
            Spec spec = new Spec(entry.getValue());
            System.out.println("name: " + name + ", spec: " + Integer.toHexString(spec.val));
            System.out.println("name: " + name + ", ld: " + spec.ld + ", ls: " + spec.ls);
            PartyWeekly pw = new PartyWeekly();
            pw.partyName = name;
            pw.partyZF = spec.zf;
            pw.partyLD = spec.ld;
            pw.partyLS = spec.ls;
            partyWeeklyList.add(pw);
        }
        return partyWeeklyList;
    }
}
