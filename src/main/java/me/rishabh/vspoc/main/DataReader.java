package me.rishabh.vspoc.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.rishabh.vspoc.model.Day;
import me.rishabh.vspoc.model.Direction;
import me.rishabh.vspoc.model.Reading;

public class DataReader {

	private List<Reading> reading = new ArrayList<Reading>();
	private String filepath = "/media/personal/Int/code/vspoc/src/main/resources/inputdata.txt";
	private String outpath = "/media/personal/Int/code/vspoc/src/main/resources/output.txt";

	public void processData() throws Exception {

		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(filepath));
			writer = new BufferedWriter(new FileWriter(outpath));

			long oldTime = 86400000l;

			String line = reader.readLine();
			long newTime = timeInMilliseconds(line);

			Day dayOfTravel = Day.FIVE;

			while (line != null) {
				Reading car = new Reading();
				if (oldTime > newTime) {
					dayOfTravel = dayOfTravel.next();
				}
				car.setDayOfTravel(dayOfTravel);

				if (startsWithA(line)) {
					car.setTimeOfFrontAxleOnMarkerA(timeInMilliseconds(line));
					line = reader.readLine();
				} else {
					// TODO Remove this check
					throw new Exception("CANNOT COME HERE");
				}
				if (startsWithA(line)) {
					car.setTimeOfRearAxleOnMarkerA(timeInMilliseconds(line));
					car.setDirection(Direction.NORTH_BOUND);
					reading.add(car);
					line = reader.readLine();
					// TODO Remove this check
					if (!car.isValid()) {
						throw new Exception("CAR DATA IS INVALID");
					}

					oldTime = newTime;
					newTime = timeInMilliseconds(line);
					// writer.write(car.toString() + "\n");
					continue;
				} else {
					car.setTimeOfFrontAxleOnMarkerB(timeInMilliseconds(line));
					line = reader.readLine();

					if (startsWithA(line)) {
						car.setTimeOfRearAxleOnMarkerA(timeInMilliseconds(line));
						line = reader.readLine();
					} else {
						// TODO Remove this check
						throw new Exception("CANNOT COME HERE");
					}

					if (startsWithB(line)) {
						car.setTimeOfRearAxleOnMarkerB(timeInMilliseconds(line));
						car.setDirection(Direction.SOUTH_BOUND);
						reading.add(car);
						line = reader.readLine();
						// TODO Remove this check
						if (!car.isValid()) {
							throw new Exception("CAR DATA IS INVALID");
						}
					}
				}
				oldTime = newTime;
				newTime = timeInMilliseconds(line);
				// writer.write(car.toString() + "\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			reader.close();
			writer.close();
		}
		// System.out.println(vehicles.size());
	}

	private long timeInMilliseconds(String line) {
		long returnValue = 0;
		try {
			returnValue = Long.parseLong(line.substring(1));
		} catch (Exception e) {
		}
		return returnValue;
	}

	private boolean startsWithA(String line) {
		return line.startsWith("A");
	}

	private boolean startsWithB(String line) {
		return line.startsWith("B");
	}

	public static void main(String args[]) {
		DataReader s = new DataReader();
		try {
			s.processData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}