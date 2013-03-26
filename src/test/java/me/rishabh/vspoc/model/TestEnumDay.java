package me.rishabh.vspoc.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestEnumDay {

	@Test
	public void testNext() {
		Day day = Day.ONE;
		Day day2 = Day.TWO;
		day = day.next();
		assertTrue(day == day2);
	}

	@Test
	public void testNextTwice() {
		Day day = Day.ONE;
		Day day2 = Day.THREE;
		day = day.next();
		day = day.next();
		assertTrue(day == day2);
	}

	@Test
	public void testNextWrapAround() {
		Day day = Day.FIVE;
		Day day2 = Day.ONE;
		day = day.next();
		assertTrue(day == day2);
	}

	@Test
	public void testNextNegative() {
		Day day = Day.FIVE;
		Day day2 = Day.THREE;
		day = day.next();
		assertTrue(day != day2);
	}
}
