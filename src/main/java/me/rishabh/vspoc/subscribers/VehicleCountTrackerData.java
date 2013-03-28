package me.rishabh.vspoc.subscribers;

import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;
import me.rishabh.vspoc.utilities.SimpleLogger;

public class VehicleCountTrackerData {

    private static final SimpleLogger LOG = SimpleLogger.getLogger(VehicleCountTrackerData.class);

    // [2] - for direction
    // [5] - days
    // Each item of the below array is representation of 5 minutes interval.
    private static long[][][] count5 = new long[2][5][288];

    // Each item of the below array is representation of 15 minutes interval.
    private static long[][][] count15 = new long[2][5][96];

    // Each item of the below array is representation of 20 minutes interval.
    private static long[][][] count20 = new long[2][5][72];

    // Each item of the below array is representation of 30 minutes interval.
    private static long[][][] count30 = new long[2][5][48];

    // Each item of the below array is representation of 60 minutes interval.
    private static long[][][] count60 = new long[2][5][24];

    public void recordData(Reading data, int interval) {
        Day day = data.getDayOfTravel();
        long time = data.getMarkedTime();
        Direction direction = data.getDirectionOfTravel();
        int index = (int) (time / 60000 / interval);

        if (LOG.isDebugEnabled()) {
            LOG.debug("recordData()", "time = " + time + " .. index = " + index);
        }

        switch (interval) {

            case 60:
                count60[direction.ordinal()][day.ordinal()][index]++;
                break;
            case 30:
                count30[direction.ordinal()][day.ordinal()][index]++;
                break;
            case 20:
                count20[direction.ordinal()][day.ordinal()][index]++;
                break;
            case 15:
                count15[direction.ordinal()][day.ordinal()][index]++;
                break;
            default:
                // case 5
                count5[direction.ordinal()][day.ordinal()][index]++;
        }
    }

}