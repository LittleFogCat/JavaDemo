package top.littlefogcat.demolist.other;

public class WorldBankData {

    /**
     * indicator : {"id":"SI.POV.GINI","value":"Gini index (World Bank estimate)"}
     * country : {"id":"1A","value":"Arab World"}
     * countryiso3code : ARB
     * date : 2020
     * value : null
     * unit :
     * obs_status :
     * decimal : 1
     */

    public Indicator indicator; // 数据类型
    public Country country; // 国家
    public String countryiso3code; // 国家代码
    public String date; // 日期
    public Object value; // 值
    public String unit; //
    public String obs_status;
    public int decimal;

    public static class Indicator {
        /**
         * id : SI.POV.GINI
         * value : Gini index (World Bank estimate)
         */

        public String id;
        public String value;
    }

    public static class Country {
        /**
         * id : 1A
         * value : Arab World
         */

        public String id;
        public String value;
    }

    @Override
    public String toString() {
        return "{" + country.value + "-" + date + "-" + value + '}';
    }
}
