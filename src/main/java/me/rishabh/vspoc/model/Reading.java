package me.rishabh.vspoc.model;


public class Reading {

	// Time in milliseconds when...
	private long frontAxleOnA;
	private long rearAxleOnA;
	private long frontAxleOnB;
	private long rearAxleOnB;

	// The direction of the vehicle
	private Direction direction;

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

	public void setTimeOfFrontAxleOnMarkerA(long milliseconds) {
		this.frontAxleOnA = milliseconds;
	}

	public void setTimeOfFrontAxleOnMarkerB(long milliseconds) {
		this.frontAxleOnB = milliseconds;
	}

	public void setTimeOfRearAxleOnMarkerA(long milliseconds) {
		this.rearAxleOnA = milliseconds;
	}

	public void setTimeOfRearAxleOnMarkerB(long milliseconds) {
		this.rearAxleOnB = milliseconds;
	}

	public boolean isValid() {
		if (frontAxleOnA == 0 || rearAxleOnA == 0) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [frontAxleOnA=" + frontAxleOnA + ", rearAxleOnA="
				+ rearAxleOnA + ", frontAxleOnB=" + frontAxleOnB
				+ ", rearAxleOnB=" + rearAxleOnB + ", direction=" + direction
				+ ", day=" + day + "]";
	}

}
