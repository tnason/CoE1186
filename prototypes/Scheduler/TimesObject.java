package TLTTC;

import java.sql.*;

public class TimesObject
{
	public final String stationName;
	public final int trainNumber;
	public final long time;

	public TrainStatus status;

	public TimesObject(String stationName, int trainNumber, long time, TrainStatus status)
	{
		this.stationName = stationName;
		this.trainNumber = trainNumber;
		this.time = time;
		this.status = status;
	}

	public String toString()
	{
		return new String("Station Name: " + stationName +
					"\nTrain Number: " + trainNumber +
					"\nArrivalTime: " + new Time(time).toString() +
					"\nTrain Status: " + status);
	}
}