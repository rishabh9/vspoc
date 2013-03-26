package me.rishabh.vspoc.model;

public enum Day {
	ONE, TWO, THREE, FOUR, FIVE;
	
	public Day next()  
    {  
        Day days[] = Day.values();  
        int ordinal = this.ordinal();  
        ordinal = ++ordinal % days.length;  
        return days[ordinal];  
    }
}
