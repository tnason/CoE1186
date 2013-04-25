package TLTTC;

import java.util.*;

public class SatelliteContainer 
{
	private Hashtable<Integer, SatelliteInstance> satellites;

	public SatelliteContainer()
	{
		satellites = new Hashtable<Integer, SatelliteInstance> ();
	}

	public SatelliteInstance addSatellite(TrainModel train)
	{
		SatelliteInstance newSat = new SatelliteInstance(train);
		satellites.put(train.getID(), newSat);

		return newSat;
	}

}
