package top.littlefogcat.demolist.tools.contentfinder;

public class ContentFinderSample {
    static String thisFile = "C:\\Repositories";

    public static void main(String[] args) {
        ContentFinder finder = new ContentFinder(".getInstance()", thisFile, "build", ".idea", "generated", "venv", "venv0");
        ContentFinder.Result result = finder.find();
        result.print();
    }
}
