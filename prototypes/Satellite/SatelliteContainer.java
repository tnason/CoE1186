package TLTTC;

import java.util.*;

public class SatelliteContainer 
{
	private Hashtable<Integer, SatelliteInstance> satellites;

	public SatelliteContainer()
	{
		satellites = new Hashtable<Integer, SatelliteInstance> ();
	}

	public Satellite addSatellite(TrainModel train)
	{
		Satellite newSat = new Satellite(train);
		satellites.put(train.trainID, newSat);
	}

}
