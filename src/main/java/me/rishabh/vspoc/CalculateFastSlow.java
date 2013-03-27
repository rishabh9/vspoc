package me.rishabh.vspoc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CalculateFastSlow {

    /**
     * @param args
     */
    public static void main(String[] args) {
        CalculateFastSlow cfs = new CalculateFastSlow();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(
                    "/media/personal/Int/code/vspoc/src/main/resources/inputdata.txt"));
            String data = reader.readLine();
            while (data != null) {
                if (cfs.isA(data)) {
                    data = reader.readLine();
                    if (cfs.isA(data)) {
                        data = reader.readLine();
                        if (cfs.isB(data)) {
                            System.out.println("Encountered AAB");
                        }
                    }
                }
                data = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isB(String data) {
        return data.startsWith("B");
    }

    private boolean isA(String data) {
        return data.startsWith("A");
    }
}
