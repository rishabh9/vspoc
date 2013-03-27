package me.rishabh.vspoc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(-19800000L);
        System.out.println(c.getTimeInMillis());
        System.out.println(c.getTime().toString());
        App app = new App();
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(
                    "/media/personal/Int/code/vspoc/src/main/resources/inputdata.txt"));
            writer = new BufferedWriter(new FileWriter("/media/personal/Int/code/vspoc/src/main/resources/output.txt"));
            String data = reader.readLine();
            while (data != null) {
                String ms = app.extractMilliSeconds(data);
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(Long.parseLong(ms));
                if (86400000L <= Long.parseLong(ms)) {
                    System.out.println(cal.getTime().toString());
                }
                writer.write(cal.getTime().toString() + "\n");
                // System.out.println(ms);
                data = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
                if (null != writer) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private String extractMilliSeconds(String data) {
        return data.substring(1);
    }
}
