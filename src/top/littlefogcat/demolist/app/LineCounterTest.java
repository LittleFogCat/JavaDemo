package top.littlefogcat.demolist.app;

import top.littlefogcat.demolist.utils.LineCounter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 统计一个文件夹里面一共有多少行代码
 */
@SuppressWarnings({"ConstantConditions", "SameParameterValue"})
public class LineCounterTest {
    public static final String PATH1 = "C:\\Repositories";
    public static final String PATH2 = "C:\\Users\\littlefogcat\\IdeaProjects";
    public static final String PATH3 = "C:\\Repositories\\ClickerX";
    public static final Set<String> defaultSuffixes = new HashSet<>(Arrays.asList(
            ".java", ".kt", ".xml"
    ));
    public static final Set<String> defaultExclude = new HashSet<>(Arrays.asList(
            ".idea",
            "build",
            "compose-sample",
            "Cranesample",
            "FileExplorer",
            "FileExplorer1",
            "FileExplorerY",
            "flutter_app",
            "generated",
            "ic_launcher_background.xml",
            "ic_launcher_foreground.xml",
            "ic_launcher_round.xml",
            "Jetpack-WanAndroid",
            "luaj",
            "SmallGames - 副本",
            "talkback",
            "VirtualLocation",
            "venv",
            "venv0",
            "wanandroid_jetpack_demo"
    ));

    public static void main(String[] args) throws IOException {
        countDemoList();
        countTotal();
        countTotalStrict();
        countTotalSample();
    }

    static void countDemoList() {
        new LineCounter.Builder("C:\\Repositories\\DemoList")
                .suffix(defaultSuffixes)
                .exclude(defaultExclude)
                .strict(true)
                .count();
    }

    static void countTotal() {
        new LineCounter.Builder(PATH1, PATH2)
                .suffix(defaultSuffixes)
                .exclude(defaultExclude)
                .name("常规统计")
                .strict(true)
                .countUseTime(true)
                .count();
    }

    static void countTotalStrict() {
        new LineCounter.Builder()
                .suffix(defaultSuffixes)
                .exclude(defaultExclude)
                .strict(true)
                .count();
    }

    static void countTotalSample() {
        LineCounter.CountResult result = new LineCounter.Builder("C:\\Repositories") // 可以有多个
                .suffix(".java", ".kt", ".xml") // 要统计的后缀，留空则统计所有
                .exclude(".idea", "build", "generated") // 忽略的文件/文件夹
                .granularity(LineCounter.Granularity.TYPE) // 统计的粒度，统计结果以文件/类型/总计为单位，默认TYPE
                .strict(true) // 是否过滤无效行，默认否
                .filter(null) // 过滤无效行规则，留空则使用默认规则
                .name("total sample") // 当前统计器名称，在打印时使用
                .printDetail(false) // 打印时是否打印具体统计结果HashMap，默认否
                .printAfterCount(true) // 是否在统计之后打印，默认是
                .countUseTime(true) // 是否打印统计用时，默认否
                .count(); // 进行统计
    }
}
