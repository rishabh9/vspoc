package me.rishabh.vspoc.model;

/**
 * This class holds the reading every time a car passes over the markers.
 * 
 * @author rishabh
 * 
 */
public class Reading {

	// Time in milliseconds when...
	private long frontAxleOnA;
	private long rearAxleOnA;
	private long frontAxleOnB;
	private long rearAxleOnB;

	// The direction of the vehicle
	private Direction direction;

	// Holds the day when the car passed over the markers.
	private Day day;

	/**
	 * Set the direction in which the vehicle is traveling.
	 * 
	 * @param direction
	 *            NORTH_BOUND or SOUTH_BOUND
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Set the day of travel of the vehicle.
	 * 
	 * @param day
	 *            ONE, TWO, THREE, FOUR or FIVE
	 */
	public void setDayOfTravel(Day day) {
		this.day = day;
	}

	/**
	 * Set the time when the front axle goes over the marker A
	 * 
	 * @param milliseconds
	 *            Time in milliseconds from midnight
	 */
	public void setTimeOfFrontAxleOnMarkerA(long milliseconds) {
		this.frontAxleOnA = milliseconds;
	}

	/**
	 * Set the time when the front axle goes over the marker B
	 * 
	 * @param milliseconds
	 *            Time in milliseconds from midnight
	 */
	public void setTimeOfFrontAxleOnMarkerB(long milliseconds) {
		this.frontAxleOnB = milliseconds;
	}

	/**
	 * Set the time when the rear axle goes over the marker A
	 * 
	 * @param milliseconds
	 *            Time in milliseconds from midnight
	 */
	public void setTimeOfRearAxleOnMarkerA(long milliseconds) {
		this.rearAxleOnA = milliseconds;
	}

	/**
	 * Set the time when the rear axle goes over the marker B
	 * 
	 * @param milliseconds
	 *            Time in milliseconds from midnight
	 */
	public void setTimeOfRearAxleOnMarkerB(long milliseconds) {
		this.rearAxleOnB = milliseconds;
	}

	/**
	 * Helper method to validate if reading is a valid. TODO - REMOVE THIS
	 * METHOD WHEN CODE COMPLETE
	 * 
	 * @return True if reading is valid
	 */
	public boolean isValid() {
		if (frontAxleOnA == 0 || rearAxleOnA == 0) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Reading [frontAxleOnA=" + frontAxleOnA + ", rearAxleOnA="
				+ rearAxleOnA + ", frontAxleOnB=" + frontAxleOnB
				+ ", rearAxleOnB=" + rearAxleOnB + ", direction=" + direction
				+ ", day=" + day + "]";
	}

}
