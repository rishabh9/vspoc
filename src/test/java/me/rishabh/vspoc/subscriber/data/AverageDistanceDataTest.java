package me.rishabh.vspoc.subscriber.data;

import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AverageDistanceDataTest {

    private AverageDistanceData adt;

    @Before
    public void setUp() throws Exception {
        adt = new AverageDistanceData();

        Reading data = new Reading();
        data.setDayOfTravel(Day.ONE);
        data.setDirection(Direction.NORTH_BOUND);
        data.setTimeOfFrontAxleOnMarkerA(98186);
        data.setTimeOfFrontAxleOnMarkerB(0);
        data.setTimeOfRearAxleOnMarkerA(98333);
        data.setTimeOfRearAxleOnMarkerB(0);

        adt.recordData(data);

        data = new Reading();
        data.setDayOfTravel(Day.ONE);
        data.setDirection(Direction.NORTH_BOUND);
        data.setTimeOfFrontAxleOnMarkerA(499718);
        data.setTimeOfFrontAxleOnMarkerB(0);
        data.setTimeOfRearAxleOnMarkerA(499886);
        data.setTimeOfRearAxleOnMarkerB(0);

        adt.recordData(data);

        data = new Reading();
        data.setDayOfTravel(Day.ONE);
        data.setDirection(Direction.SOUTH_BOUND);
        data.setTimeOfFrontAxleOnMarkerA(638379);
        data.setTimeOfFrontAxleOnMarkerB(638382);
        data.setTimeOfRearAxleOnMarkerA(638520);
        data.setTimeOfRearAxleOnMarkerB(638523);

        adt.recordData(data);
    }

    @After
    public void tearDown() throws Exception {
        // Reset Static variables.
        AverageDistanceData.setAvgDistance60(new long[2][5][24]);
        AverageDistanceData.setCount60(new long[2][5][24]);
        AverageDistanceData.setzDistance60(new long[2][5][24]);
        AverageDistanceData.setPreviousDataNorth(null);
        AverageDistanceData.setPreviousDataSouth(null);
    }

    @Test
    public void testRecordData() {

        long a[][][] = AverageDistanceData.getCount60();

        Assert.assertEquals(2, a[0][0][0]);
        Assert.assertEquals(1, a[1][0][0]);
    }

    @Test
    public void testGetAverageDistance() {

        long dist = adt.getAverageDistance(Direction.NORTH_BOUND, Day.ONE, 1);
        Assert.assertEquals(2986, dist);

        dist = adt.getAverageDistance(Direction.SOUTH_BOUND, Day.ONE, 1);
        Assert.assertEquals(0, dist);
    }

}
