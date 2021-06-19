package top.littlefogcat.demolist.io;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class StringInputStreamTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "ajiobjaio腹肌哦" +
                "区间内太哦，解耦PKOP复刻f䂲";
        ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
        int ch;
        while ((ch = is.read()) != -1) {
            System.out.print((char) ch);
        }
    }
}
