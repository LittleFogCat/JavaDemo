//package top.littlefogcat.demolist.other;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import top.littlefogcat.demolist.utils.HttpUtil;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.*;
//import java.util.concurrent.*;
//
//public class Gini {
//    private String url = "http://api.worldbank.org/v2/country/all/indicator/SI.POV.GINI?format=json";
//    private Gson gson = new Gson();
//    private int pages;
//    private ExecutorService threads = Executors.newCachedThreadPool();
//
//    public Gini() {
//    }
//
//    public void beforeParse() {
//        String json = HttpUtil.get(url);
//        System.out.println(json);
//        JsonArray obj = JsonParser.parseString(json).getAsJsonArray();
//        JsonObject general = obj.get(0).getAsJsonObject();
//        pages = general.get("pages").getAsInt();
//    }
//
//    public void parse() throws ExecutionException, InterruptedException {
//        beforeParse();
//        ThreadPoolExecutor threads = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//        List<WorldBankData> giniList = new ArrayList<>();
//        List<WorldBankData> incomeList = new ArrayList<>();
//        List<Future<?>> futures = new ArrayList<>();
//        List<Integer> errorList = new ArrayList<>();
//        for (int i = 1; i <= pages; i++) {
//            final int page = i;
//            Future<?> f = threads.submit(() -> getGini(page, 3, giniList, errorList));
//            futures.add(f);
//        }
//        for (int page = 1; page <= 6; page++) {
//            int finalPage = page;
//            Future<?> f = threads.submit(() -> getIncome(finalPage, 3, incomeList, errorList));
//            futures.add(f);
//        }
//        for (Future<?> future : futures) future.get(); // 等待任务执行结束
//        giniList.sort((o1, o2) -> o1.countryiso3code.equals(o2.countryiso3code) ?
//                o1.date.compareTo(o2.date) :
//                o1.country.value.compareTo(o2.country.value));
//        System.out.println("data list: " + giniList);
//        System.out.println("error list: " + errorList);
////        output(dataList);
//        output1(giniList);
//    }
//
//    private void getGini(int page, int retryCount, List<WorldBankData> dataList, List<Integer> errorList) {
//        String url = this.url + "&page=" + page;
//        try {
//            String json = HttpUtil.get(url);
//            JsonArray jArr = JsonParser.parseString(json).getAsJsonArray();
//            WorldBankData[] data = gson.fromJson(jArr.get(1), WorldBankData[].class);
//            for (WorldBankData datum : data) {
//                if (datum.value != null) dataList.add(datum);
//            }
////            System.out.println("complete " + page);
//        } catch (Exception e) {
//            if (retryCount <= 0) {
//                System.err.println("retry failed: page " + page);
//                errorList.add(page);
//                return;
//            }
//            System.out.println("retry page=" + page + ", retryCount=" + retryCount);
//            getGini(page, retryCount - 1, dataList, errorList);
//        }
//    }
//
//    private void getIncome(int page, int retryCount, List<WorldBankData> incomeList, List<Integer> errorList) {
//        String url = "http://api.worldbank.org/v2/country/all/indicator/NY.ADJ.NNTY.PC.KD?format=json&date=2020&page=" + page;
//        try {
//            String json = HttpUtil.get(url);
//            JsonArray jArr = JsonParser.parseString(json).getAsJsonArray();
//            WorldBankData[] data = gson.fromJson(jArr.get(1), WorldBankData[].class);
//            for (WorldBankData datum : data) {
//                if (datum.value != null) incomeList.add(datum);
//            }
////            System.out.println("complete " + page);
//        } catch (Exception e) {
//            if (retryCount <= 0) {
//                System.err.println("retry failed: page " + page);
//                errorList.add(page);
//                return;
//            }
//            System.out.println("retry page=" + page + ", retryCount=" + retryCount);
//            getGini(page, retryCount - 1, incomeList, errorList);
//        }
//    }
//
//    private void output(List<WorldBankData> dataList) {
//        String fileName = "C:\\Users\\littlefogcat\\Desktop\\gini.csv";
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
//            File file = new File(fileName);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            for (WorldBankData data : dataList) {
//                String line = "";
//                if (data.country.value.contains(",")) {
//                    data.country.value = data.country.value.replace(",", ".");
//                }
//                line += data.country.value + ",";
//                line += data.date + ",";
//                line += data.value;
//                bw.write(line);
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private void output1(List<WorldBankData> dataList) {
//        String fileName = "C:\\Users\\littlefogcat\\Desktop\\gini1.csv";
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
//            File file = new File(fileName);
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            Map<Integer, WorldBankData> CNData = new HashMap<>();
//            Map<Integer, WorldBankData> USData = new HashMap<>();
//            Map<Integer, WorldBankData> FRData = new HashMap<>();
//            Map<Integer, WorldBankData> SAData = new HashMap<>();
//            for (WorldBankData d : dataList) {
//                switch (d.country.value) {
//                    case "China" -> CNData.put(Integer.valueOf(d.date), d);
//                    case "United States" -> USData.put(Integer.valueOf(d.date), d);
//                    case "France" -> FRData.put(Integer.valueOf(d.date), d);
//                    case "South Africa" -> SAData.put(Integer.valueOf(d.date), d);
//                }
//            }
//            String firstLine = ",China,United States,France,South Africa";
//            bw.write(firstLine);
//            bw.newLine();
//            for (int year = 1990; year <= 2020; year++) {
//                String line = "" + year + ",";
//                if (CNData.containsKey(year)) line += CNData.get(year).value;
//                line += ",";
//                if (USData.containsKey(year)) line += USData.get(year).value;
//                line += ",";
//                if (FRData.containsKey(year)) line += FRData.get(year).value;
//                line += ",";
//                if (SAData.containsKey(year)) line += SAData.get(year).value;
//                bw.write(line);
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Gini gini = new Gini();
//        gini.parse();
//    }
//}
