/*
 *
 */
 
package TLTTC;
import java.util.*;

public class TrainScheduleViewModel 
{   
    private TrainViewModel _train;
    private ArrayList<String> _trainSchedule;
    
    TrainScheduleViewModel ( TrainViewModel train )
    {
        _train = train;
        _trainSchedule = new ArrayList<String>();
        String[] stations = {
            "Glenbury",
            "Dormont",
            "Mt Lebanon",
            "Poplar",
            "Castle Shannon",
            "Mt Lebanon",
            "Overbrook",
            "Inglewood",
            "Central",
            "Whited",
            "Station",
            "Edgebrook",
            "Pioneer",
            "Station",
            "Whited",
            "South Bank",
            "Central",
            "Inglewood"
        };
        for (int i = 0; i < stations.length; i++)
        {
            _trainSchedule.add(stations[i]);
        }
    }
    
    public String nextStation ()
    {
        if (_trainSchedule.size() > 0)
        {
            return _trainSchedule.get(0);
        }
        else
        {
            return null;
        }
    }
    
    public void stationReached ()
    {
        _trainSchedule.remove(0);
    }
}