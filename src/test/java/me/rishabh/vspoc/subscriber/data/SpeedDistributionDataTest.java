package me.rishabh.vspoc.subscriber.data;

import static org.junit.Assert.fail;
import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpeedDistributionDataTest {

    SpeedDistributionData sdd;

    @Before
    public void setUp() throws Exception {
        sdd = new SpeedDistributionData();

        Reading data = new Reading();
        data.setDayOfTravel(Day.ONE);
        data.setDirection(Direction.NORTH_BOUND);
        data.setTimeOfFrontAxleOnMarkerA(98186);
        data.setTimeOfFrontAxleOnMarkerB(0);
        data.setTimeOfRearAxleOnMarkerA(98333);
        data.setTimeOfRearAxleOnMarkerB(0);

        sdd.recordData(data);

        data = new Reading();
        data.setDayOfTravel(Day.ONE);
        data.setDirection(Direction.NORTH_BOUND);
        data.setTimeOfFrontAxleOnMarkerA(499718);
        data.setTimeOfFrontAxleOnMarkerB(0);
        data.setTimeOfRearAxleOnMarkerA(499886);
        data.setTimeOfRearAxleOnMarkerB(0);

        sdd.recordData(data);

        data = new Reading();
        data.setDayOfTravel(Day.ONE);
        data.setDirection(Direction.SOUTH_BOUND);
        data.setTimeOfFrontAxleOnMarkerA(638379);
        data.setTimeOfFrontAxleOnMarkerB(638382);
        data.setTimeOfRearAxleOnMarkerA(638520);
        data.setTimeOfRearAxleOnMarkerB(638523);

        sdd.recordData(data);
    }

    @After
    public void tearDown() throws Exception {
        // Reset Static variables.
        SpeedDistributionData.setAvgSpeed60(new double[2][5][24]);
        SpeedDistributionData.setCount60(new long[2][5][24]);
        SpeedDistributionData.setzSpeed60(new double[2][5][24]);
        sdd = null;
    }

    @Test
    public void testRecordData() {
        long a[][][] = SpeedDistributionData.getCount60();

        Assert.assertEquals(2, a[0][0][0]);
        Assert.assertEquals(1, a[1][0][0]);

        double b[][][] = SpeedDistributionData.getzSpeed60();

        Assert.assertEquals(114.795918367, b[0][0][0], 0.001);
        Assert.assertEquals(63.8297872340, b[1][0][0], 0.001);

        double c[][][] = SpeedDistributionData.getAvgSpeed60();

        Assert.assertEquals(57.397959183, c[0][0][0], 0.001);
        Assert.assertEquals(63.8297872340, c[1][0][0], 0.001);

    }

    @Test
    public void testGetAverageSpeed() {
        Assert.assertEquals(57, sdd.getAverageSpeed(Direction.NORTH_BOUND, Day.ONE, 1));
        Assert.assertEquals(64, sdd.getAverageSpeed(Direction.SOUTH_BOUND, Day.ONE, 1));
    }

}
