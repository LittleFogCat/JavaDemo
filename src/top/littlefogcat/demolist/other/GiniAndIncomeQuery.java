package top.littlefogcat.demolist.other;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import top.littlefogcat.demolist.utils.HttpUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GiniAndIncomeQuery {

    private static class GiniAndIncomeBean {
        String year;
        String country;
        Double income;
        Double gini;
    }

    private static final String GINI_URL = "http://api.worldbank.org/v2/country/all/indicator/SI.POV.GINI?format=json";
    private static final String INCOME_URL = "http://api.worldbank.org/v2/country/all/indicator/NY.ADJ.NNTY.PC.KD?format=json";
    private final Gson gson = new Gson();
    private ExecutorService threadPool = Executors.newCachedThreadPool();

    public void queryDataAndSave(String year, String localDir) throws Exception {
        List<GiniAndIncomeBean> dataList = query(year);
        saveToLocal(dataList, localDir);
    }

    public List<GiniAndIncomeBean> query(String year) throws Exception {
        List<GiniAndIncomeBean> dataList = new ArrayList<>();
        List<Future<?>> tasks = new ArrayList<>();
        Map<String, WorldBankData> incomeMap = queryData(INCOME_URL + "&date=" + year, tasks);
        Map<String, WorldBankData> giniMap = queryData(GINI_URL + "&date=" + year, tasks);
        for (Future<?> task : tasks) task.get(); // 等待所有网络任务执行完毕
        // parse
        for (String countryName : giniMap.keySet()) {
            if (incomeMap.containsKey(countryName)) {
                WorldBankData incomeData = incomeMap.get(countryName);
                WorldBankData giniData = giniMap.get(countryName);
                GiniAndIncomeBean data = new GiniAndIncomeBean();
                data.year = year;
                data.country = countryName;
                data.income = (Double) incomeData.value;
                data.gini = (Double) giniData.value;
                dataList.add(data);
            }
        }
        return dataList;
    }

    public void saveToLocal(List<GiniAndIncomeBean> dataList, String localPath) {
        String filename = localPath + "\\" + System.currentTimeMillis() + ".csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            File f = new File(filename);
            if (!f.exists() && !f.createNewFile()) return;
            for (GiniAndIncomeBean data : dataList) {
                if (data.country.contains(",")) { // 替换逗号
                    data.country = data.country.replace(",", ".");
                }
                data.income = (double) Math.round(data.income);
                String line = "";
                line += data.country + ",";
                line += data.income + ",";
                line += data.gini;
                writer.write(line);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, WorldBankData> queryData(String url, List<Future<?>> tasks) {
        Map<String, WorldBankData> dataMap = new HashMap<>();
        int pages = getPages(url);
        for (int page = 1; page <= pages; page++) {
            int finalPage = page;
            Runnable task = () -> {
                String pageUrl = url + "&page=" + finalPage;
                String json = HttpUtil.get(pageUrl);
                JsonArray arr = JsonParser.parseString(json).getAsJsonArray();
                WorldBankData[] data = gson.fromJson(arr.get(1), WorldBankData[].class);
                for (WorldBankData d : data) {
                    if (d.value != null) {
                        dataMap.put(d.country.value, d);
                    }
                }
                System.out.println("done page " + finalPage);
            };
            Future<?> f = threadPool.submit(task);
            tasks.add(f);
        }
        return dataMap;
    }

    private int getPages(String url) {
        String json = HttpUtil.get(url);
        JsonArray arr = JsonParser.parseString(json).getAsJsonArray();
        return arr.get(0).getAsJsonObject().get("pages").getAsInt();
    }
}
