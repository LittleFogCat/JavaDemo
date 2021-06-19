package top.littlefogcat.demolist.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamRead {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("src/top/littlefogcat/demolist/io/Sample");
        int ch;
        while ((ch = is.read()) != -1) {
            System.out.print((char) ch);
        }
    }
}
