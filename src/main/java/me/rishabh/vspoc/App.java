package me.rishabh.vspoc;

import java.io.File;
import java.io.IOException;

import me.rishabh.vspoc.exceptions.UnSupportedInputDataPatternException;
import me.rishabh.vspoc.publisher.ReadingsPublisher;
import me.rishabh.vspoc.reader.DataReader;
import me.rishabh.vspoc.reader.FileDataReader;
import me.rishabh.vspoc.subscribers.SpeedDistributionTracker;
import me.rishabh.vspoc.subscribers.VehicleCountTracker;

public class App {

    public static void main(String args[]) {
        ReadingsPublisher publisher = new ReadingsPublisher();

        VehicleCountTracker vct = new VehicleCountTracker();
        publisher.addObserver(vct);

        SpeedDistributionTracker sdt = new SpeedDistributionTracker();
        publisher.addObserver(sdt);

        try {
            DataReader s = new FileDataReader(publisher, new File(args[0]));
            s.readAndPush();
        } catch (UnSupportedInputDataPatternException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        vct.display();
        sdt.display();
    }
}
