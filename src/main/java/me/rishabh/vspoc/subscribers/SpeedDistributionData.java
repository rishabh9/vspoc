package me.rishabh.vspoc.subscribers;

import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;
import me.rishabh.vspoc.utilities.SimpleLogger;

public class SpeedDistributionData {

    private static final SimpleLogger LOG = SimpleLogger.getLogger(SpeedDistributionData.class);

    private static final double DISTANCE_BW_AXLE = 2.5; // in meters

    // [2] - for direction
    // [5] - days
    // Each item of the below array is representation of 60 minutes interval.
    // Stores count of vehicles
    private static long[][][] count60 = new long[2][5][24];

    // Stores the average speed of vehicles
    private static double[][][] avgSpeed60 = new double[2][5][24];

    // stores the sum of all vehicle speeds for the given interval.
    // Required to calculate the average speed
    private static double[][][] zSpeed60 = new double[2][5][24];

    public void recordData(Reading data) {
        Day day = data.getDayOfTravel();
        long time = data.getTimeOfFrontAxleOnMarkerA();
        Direction direction = data.getDirectionOfTravel();
        double speed = getSpeedInKmph(data);
        int index = (int) (time / 60000 / 60); // 60 minutes

        LOG.debug("recordData()", "Speed : " + speed);

        count60[direction.ordinal()][day.ordinal()][index]++;
        zSpeed60[direction.ordinal()][day.ordinal()][index] += speed;
        avgSpeed60[direction.ordinal()][day.ordinal()][index] = zSpeed60[direction.ordinal()][day.ordinal()][index]
                / count60[direction.ordinal()][day.ordinal()][index];

        LOG.debug("recordData()", "Count : " + count60[direction.ordinal()][day.ordinal()][index]);
        LOG.debug("recordData()", "Total speed : " + zSpeed60[direction.ordinal()][day.ordinal()][index]);
        LOG.debug("recordData()", "Avg Speed : " + avgSpeed60[direction.ordinal()][day.ordinal()][index]);
    }

    /**
     * Get the average speed of vehicles
     * 
     * @param direction
     *            North-bound or South-bound
     * @param day
     *            The day of travel
     * @param hour
     *            The hour of travel
     * @return
     */
    public long getAverageSpeed(Direction direction, Day day, int hour) {
        double speed = avgSpeed60[direction.ordinal()][day.ordinal()][hour - 1];
        return Math.round(speed);
    }

    /**
     * 
     * @param data
     *            The Reading
     * @return Speed of vehicle in Kmph
     */
    public double getSpeedInKmph(Reading data) {
        double timeInMilliseconds = data.getTimeOfRearAxleOnMarkerA() - data.getTimeOfFrontAxleOnMarkerA();
        double timeInSeconds = timeInMilliseconds * 0.001;
        double distInMetersInOneSecond = DISTANCE_BW_AXLE / timeInSeconds;
        double distInMetersInOneHour = distInMetersInOneSecond * 3600;
        double distInKmInOneHour = distInMetersInOneHour / 1000;
        return distInKmInOneHour;
    }

}
