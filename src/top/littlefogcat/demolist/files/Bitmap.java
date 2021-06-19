package top.littlefogcat.demolist.files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Bitmap {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\littlefogcat\\Pictures\\回收站图像.bmp";

        FileReader r = new FileReader(path);
        ArrayList<Integer> bitmap = new ArrayList<>();
        int read;
        while ((read = r.read()) != -1) {
            bitmap.add(read);
        }

        r.close();

        System.out.println(bitmap);
    }
}
