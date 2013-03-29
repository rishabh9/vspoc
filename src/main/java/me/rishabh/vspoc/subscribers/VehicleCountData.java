package me.rishabh.vspoc.subscribers;

import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;
import me.rishabh.vspoc.utilities.SimpleLogger;

public class VehicleCountData {

    private static final SimpleLogger LOG = SimpleLogger.getLogger(VehicleCountData.class);

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

    /**
     * 
     * @param data
     * @param interval
     */
    public void recordData(Reading data) {
        recordData(data, 5);
        recordData(data, 15);
        recordData(data, 20);
        recordData(data, 30);
        recordData(data, 60);
    }

    private void recordData(Reading data, int interval) {
        Day day = data.getDayOfTravel();
        long time = data.getTimeOfFrontAxleOnMarkerA();
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

    /**
     * Count of vehicles traveling in the specified direction for the given day.
     * 
     * @param direction
     *            North-bound or south-bound
     * @param day
     *            Day of travel
     * @return Count of vehicles
     */
    public long getTotalCountForDirectionDay(Direction direction, Day day) {
        long count = 0l;
        for (int i = 0; i < 24; i++) {
            count += count60[direction.ordinal()][day.ordinal()][i];
        }
        return count;
    }

    /**
     * Count of vehicles traveling in the specified direction, in morning.
     * 
     * @param direction
     *            North-bound or south-bound
     * @return Count of vehicles
     */
    public long getTotalCountForMornings(Direction direction, Day day) {
        long count = 0l;
        for (int i = 0; i < 12; i++) {
            count += count60[direction.ordinal()][day.ordinal()][i];
        }
        return count;
    }

    /**
     * Count of vehicles traveling in the specified direction, in evening.
     * 
     * @param direction
     *            North-bound or south-bound
     * @return Count of vehicles
     */
    public long getTotalCountForEvenings(Direction direction, Day day) {
        long count = 0l;
        for (int i = 12; i < 24; i++) {
            count += count60[direction.ordinal()][day.ordinal()][i];
        }
        return count;
    }

    /**
     * The hour of the day when the traffic/volume is maximum.
     * 
     * @param direction
     *            North-bound or south-bound
     * @param day
     *            Day of travel
     * @return The hour
     */
    public long getPeakVolumeHour(Direction direction, Day day) {
        int hour = 0;
        long max = 0l;
        long temp = 0;
        for (int i = 0; i < 24; i++) {
            temp = count60[direction.ordinal()][day.ordinal()][i];
            if (temp >= max) {
                max = temp;
                hour = i;
            }
        }
        return ++hour;
    }
}