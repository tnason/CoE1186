package TLTTC;

import java.util.*;

public class SatelliteInstance
{
	private int satelliteID; 
	private TrainModel train;

	
	public SatelliteInstance(TrainModel train) 
	{
		this.train = train;
		satelliteID = train.getID();
	}

	public double calcGPS()
	{
		//empty!!!
		return 0;
	}	


}
