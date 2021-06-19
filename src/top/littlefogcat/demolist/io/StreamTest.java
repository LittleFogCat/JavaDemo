package top.littlefogcat.demolist.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class StreamTest {
    public static void main(String[] args) throws IOException {
        InputStream is= new ByteArrayInputStream("Hello你好".getBytes(StandardCharsets.UTF_8));
        byte[] bytes = new byte[16];
        is.read(bytes);
        System.out.println(new String(bytes));

        new InputStream(){

            @Override
            public int read() throws IOException {
                return 0;
            }
        };
    }
}
