package me.rishabh.vspoc.subscriber.data;

import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VehicleCountDataTest {

    private VehicleCountData vdt;

    @Before
    public void setUp() throws Exception {
        vdt = new VehicleCountData();

        Reading data = new Reading();
        data.setDayOfTravel(Day.ONE);
        data.setDirection(Direction.NORTH_BOUND);
        data.setTimeOfFrontAxleOnMarkerA(98186);
        data.setTimeOfFrontAxleOnMarkerB(0);
        data.setTimeOfRearAxleOnMarkerA(98333);
        data.setTimeOfRearAxleOnMarkerB(0);

        vdt.recordData(data);

        data = new Reading();
        data.setDayOfTravel(Day.ONE);
        data.setDirection(Direction.NORTH_BOUND);
        data.setTimeOfFrontAxleOnMarkerA(499718);
        data.setTimeOfFrontAxleOnMarkerB(0);
        data.setTimeOfRearAxleOnMarkerA(499886);
        data.setTimeOfRearAxleOnMarkerB(0);

        vdt.recordData(data);

        data = new Reading();
        data.setDayOfTravel(Day.ONE);
        data.setDirection(Direction.SOUTH_BOUND);
        data.setTimeOfFrontAxleOnMarkerA(638379);
        data.setTimeOfFrontAxleOnMarkerB(638382);
        data.setTimeOfRearAxleOnMarkerA(638520);
        data.setTimeOfRearAxleOnMarkerB(638523);

        vdt.recordData(data);

        data = new Reading();
        data.setDayOfTravel(Day.TWO);
        data.setDirection(Direction.SOUTH_BOUND);
        data.setTimeOfFrontAxleOnMarkerA(66447225);
        data.setTimeOfFrontAxleOnMarkerB(66447228);
        data.setTimeOfRearAxleOnMarkerA(66447347);
        data.setTimeOfRearAxleOnMarkerB(66447349);

        vdt.recordData(data);

    }

    @After
    public void tearDown() throws Exception {
        VehicleCountData.setCount60(new long[2][5][24]);
    }

    @Test
    public void testRecordData() {
        long count[][][] = VehicleCountData.getCount60();
        Assert.assertEquals(2, count[0][0][0]);
        Assert.assertEquals(1, count[1][0][0]);
        Assert.assertEquals(1, count[1][1][18]);
    }

    @Test
    public void testGetTotalCountForDirectionDay() {
        long count = vdt.getTotalCountForDirectionDay(Direction.NORTH_BOUND, Day.ONE);
        Assert.assertEquals(2, count);
        count = vdt.getTotalCountForDirectionDay(Direction.SOUTH_BOUND, Day.ONE);
        Assert.assertEquals(1, count);
        count = vdt.getTotalCountForDirectionDay(Direction.SOUTH_BOUND, Day.TWO);
        Assert.assertEquals(1, count);
    }

    @Test
    public void testGetTotalCountForMornings() {
        long count = vdt.getTotalCountForMornings(Direction.NORTH_BOUND, Day.ONE);
        Assert.assertEquals(2, count);
        count = vdt.getTotalCountForMornings(Direction.SOUTH_BOUND, Day.ONE);
        Assert.assertEquals(1, count);
        count = vdt.getTotalCountForMornings(Direction.SOUTH_BOUND, Day.TWO);
        Assert.assertEquals(0, count);
    }

    @Test
    public void testGetTotalCountForEvenings() {
        long count = vdt.getTotalCountForEvenings(Direction.NORTH_BOUND, Day.ONE);
        Assert.assertEquals(0, count);
        count = vdt.getTotalCountForEvenings(Direction.SOUTH_BOUND, Day.ONE);
        Assert.assertEquals(0, count);
        count = vdt.getTotalCountForEvenings(Direction.SOUTH_BOUND, Day.TWO);
        Assert.assertEquals(1, count);
    }

    @Test
    public void testGetPeakVolumeHour() {
        Assert.assertEquals(1, vdt.getPeakVolumeHour(Direction.NORTH_BOUND, Day.ONE));
        Assert.assertEquals(1, vdt.getPeakVolumeHour(Direction.SOUTH_BOUND, Day.ONE));
        Assert.assertEquals(19, vdt.getPeakVolumeHour(Direction.SOUTH_BOUND, Day.TWO));
    }

}
