/*
 *
 */
 
package TLTTC;
import java.util.*;
 
 public class TrainList
 {
    
    HashMap<Integer, TrainViewModel> _trainList;
    
    CTCController _controller;
    
    TrainList(CTCController controller)
    {
        _controller = controller;
        _trainList = new HashMap<Integer, Object>();
    }
    
    public boolean contains ( Integer tID )
    {
        return _trainList.getKeys().contains(tID);
    }
    
    public void addTrain ( Integer tID, String line )
    {
        if (!contains(tID))
        {
            TrainViewModel train = new TrainViewModel( tID, line );
            _trainList.put(tID, train);
        }
    }
    
    public void removeTrain ( Integer tID )
    {
        if (contains(tID))
        {
            _trainList.remove(tID);
        }
    }   
    
    public void setActive ( Integer tID )
    {
        if (contains(tID))
        {
            _trainList.get(tID).setActive();
        }
    }
    
    public TrainViewModel getTrain ( Integer tID )
    {
        if (contains(tID))
        {
            return _trainList.get(tID);
        }
        else
        {
            return null; // in case we later want to return something else
        }
    }
    
    public HashTable<Integer, Integer> nextBlocksForTrains ()
    {
        HashTable<Integer, Integer> blocks = new HashTable<Integer, Integer>();
        Enumeration trains = _trainList.elements();
        
        for (TrainViewModel train : trains)
        {
            blocks.put(train.getNextBlock(), train.getTrainID());
        }
        
        return blocks;
    }
 }