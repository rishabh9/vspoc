package me.rishabh.vspoc.subscriber.data;

import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;
import me.rishabh.vspoc.utilities.SimpleLogger;

public class SpeedDistributionData extends AbstractTrackerData {

    private static final SimpleLogger LOG = SimpleLogger.getLogger(SpeedDistributionData.class);

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

    @Override
    public void recordData(Reading data) {
        String methodName = "recordData()";
        LOG.debug(methodName, "Entering...");

        Day day = data.getDayOfTravel();
        long time = data.getTimeOfFrontAxleOnMarkerA();
        Direction direction = data.getDirectionOfTravel();
        double speed = getSpeedInKmph(data);
        int index = (int) (time / 60000 / 60); // 60 minutes

        LOG.debug(methodName, "Speed : " + speed);

        count60[direction.ordinal()][day.ordinal()][index]++;
        zSpeed60[direction.ordinal()][day.ordinal()][index] += speed;
        avgSpeed60[direction.ordinal()][day.ordinal()][index] = zSpeed60[direction.ordinal()][day.ordinal()][index]
                / count60[direction.ordinal()][day.ordinal()][index];

        LOG.debug(methodName, "Count : " + count60[direction.ordinal()][day.ordinal()][index]);
        LOG.debug(methodName, "Total speed : " + zSpeed60[direction.ordinal()][day.ordinal()][index]);
        LOG.debug(methodName, "Avg Speed : " + avgSpeed60[direction.ordinal()][day.ordinal()][index]);
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
    private double getSpeedInKmph(Reading data) {
        // Time difference between the front and rear axle on marker A
        double timeInMilliseconds = data.getTimeOfRearAxleOnMarkerA() - data.getTimeOfFrontAxleOnMarkerA();
        // Convert that time into seconds
        double timeInSeconds = timeInMilliseconds * 0.001;
        // Calculate how many meters the vehicle has traveled in one second.
        double distInMetersInOneSecond = DISTANCE_BW_AXLE / timeInSeconds;
        // Calculate how many meters the vehicle would have traveled in one hour
        double distInMetersInOneHour = distInMetersInOneSecond * 3600;
        // Convert meters per hour to kilometers per hour
        double distInKmInOneHour = distInMetersInOneHour / 1000;
        return distInKmInOneHour;
    }

}
