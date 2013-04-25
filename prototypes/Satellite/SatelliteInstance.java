package TLTTC;

import java.util.*;

public class SatelliteInstance
{
	private int satelliteID; 
	private TrainModel train;

	private final int halfLen = 16.6; //in m
	
	public SatelliteInstance(TrainModel train) 
	{
		this.train = train;
		satelliteID = train.trainID;
	}

	public double[] calcGPS()
	{
		//empty!!!
	}	


}
