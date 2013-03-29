package me.rishabh.vspoc.subscriber.data;

import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;
import me.rishabh.vspoc.utilities.SimpleLogger;

public class AverageDistanceData extends AbstractTrackerData {

    private static final SimpleLogger LOG = SimpleLogger.getLogger(AverageDistanceData.class);

    // The previous reading
    private static Reading previousDataNorth;
    private static Reading previousDataSouth;

    // [2] - for direction
    // [5] - days
    // Each item of the below array is representation of 60 minutes interval.
    // Stores count of vehicles
    private static long[][][] count60 = new long[2][5][24];

    // Stores the sum of distance between vehicles
    private static long[][][] zDistance60 = new long[2][5][24];

    // Stores the average of distance between vehicles
    private static long[][][] avgDistance60 = new long[2][5][24];

    @Override
    public void recordData(Reading data) {
        String methodName = "recordData()";
        LOG.debug(methodName, "Entering...");

        Day day = data.getDayOfTravel();
        Direction direction = data.getDirectionOfTravel();
        long time = data.getTimeOfFrontAxleOnMarkerA();
        int index = (int) (time / 60000 / 60); // 60 minutes

        count60[direction.ordinal()][day.ordinal()][index]++;

        long distance = 0;
        if (data.getDirectionOfTravel().equals(Direction.NORTH_BOUND)) {
            distance = getDistanceBetweenVehiclesInMeters(previousDataNorth, data);
        } else {
            distance = getDistanceBetweenVehiclesInMeters(previousDataSouth, data);
        }

        LOG.debug(methodName, "Distance between vehicles: " + distance);

        zDistance60[direction.ordinal()][day.ordinal()][index] += distance;

        avgDistance60[direction.ordinal()][day.ordinal()][index] = zDistance60[direction.ordinal()][day.ordinal()][index]
                / count60[direction.ordinal()][day.ordinal()][index];

        LOG.debug(methodName, "Count : " + count60[direction.ordinal()][day.ordinal()][index]);
        LOG.debug(methodName, "Total distance : " + zDistance60[direction.ordinal()][day.ordinal()][index]);
        LOG.debug(methodName, "Avg distance : " + avgDistance60[direction.ordinal()][day.ordinal()][index]);

        // make the current reading, the previous.
        if (data.getDirectionOfTravel().equals(Direction.NORTH_BOUND)) {
            previousDataNorth = data;
        } else {
            previousDataSouth = data;
        }
    }

    private long getDistanceBetweenVehiclesInMeters(Reading previousData, Reading data) {
        double distance = 0;
        if (previousData != null) {
            if (previousData.getDayOfTravel().equals(data.getDayOfTravel())) {

                double speedOfSecondVehicle = getSpeedOfVehicleInMetersSecond(data);
                long timeFrontAxleSecondCar = data.getTimeOfFrontAxleOnMarkerA();
                long timeRearAxleFirstCar = previousData.getTimeOfRearAxleOnMarkerA();
                double timeInMilliseconds = timeFrontAxleSecondCar - timeRearAxleFirstCar;
                double timeInSeconds = timeInMilliseconds * 0.001;
                distance = speedOfSecondVehicle * timeInSeconds;
            }
        }
        return Math.round(distance);
    }

    private double getSpeedOfVehicleInMetersSecond(Reading data) {
        double timeInMilliseconds = data.getTimeOfRearAxleOnMarkerA() - data.getTimeOfFrontAxleOnMarkerA();
        double timeInSeconds = timeInMilliseconds * 0.001;
        double distInMetersInOneSecond = DISTANCE_BW_AXLE / timeInSeconds;
        return distInMetersInOneSecond;
    }

    public long getAverageDistance(Direction direction, Day day, int hour) {
        return avgDistance60[direction.ordinal()][day.ordinal()][hour - 1];
    }

    // --- METHODS ADDED TO AID UNIT TESTING --- //

    /** For unit testing */
    static long[][][] getCount60() {
        return count60;
    }

    /** For unit testing */
    static long[][][] getzDistance60() {
        return zDistance60;
    }

    /** For unit testing */
    static long[][][] getAvgDistance60() {
        return avgDistance60;
    }

    /** For unit testing */
    static Reading getPreviousDataNorth() {
        return previousDataNorth;
    }

    /** For unit testing */
    static void setPreviousDataNorth(Reading previousDataNorth) {
        AverageDistanceData.previousDataNorth = previousDataNorth;
    }

    /** For unit testing */
    static Reading getPreviousDataSouth() {
        return previousDataSouth;
    }

    /** For unit testing */
    static void setPreviousDataSouth(Reading previousDataSouth) {
        AverageDistanceData.previousDataSouth = previousDataSouth;
    }

    /** For unit testing */
    static void setCount60(long[][][] count60) {
        AverageDistanceData.count60 = count60;
    }

    /** For unit testing */
    static void setzDistance60(long[][][] zDistance60) {
        AverageDistanceData.zDistance60 = zDistance60;
    }

    /** For unit testing */
    static void setAvgDistance60(long[][][] avgDistance60) {
        AverageDistanceData.avgDistance60 = avgDistance60;
    }

}
