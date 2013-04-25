/*
 * Author: Sean Moore
 * Last Updated: 4/21/13
 * Purpose: List of all trains currently in service
 */
 
package TLTTC;
import java.util.*;
 
 public class TrainList
 {
    
    Hashtable<Integer, TrainViewModel> _trainList;
    
    CTCController _controller;
    
    TrainList(CTCController controller)
    {
        _controller = controller;
        _trainList = new Hashtable<Integer, TrainViewModel>();
    }
    
    public boolean contains ( Integer tID )
    {
        return _trainList.containsKey(tID);
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
    
    public Hashtable<Integer, Integer> nextBlocksForTrains ()
    {
        Hashtable<Integer, Integer> blocks = new Hashtable<Integer, Integer>();
       
        ArrayList<TrainViewModel> trains = new ArrayList<TrainViewModel>(_trainList.values());
        
        for (TrainViewModel train : trains)
        {
            blocks.put(train.getNextBlock(), train.getTrainID());
        }
        
        return blocks;
    }
    
    public Integer trainCount ()
    {
        return _trainList.size();
    }
    
    public ArrayList<TrainViewModel> getTrains ()
    {
        ArrayList<TrainViewModel> trains = new ArrayList<TrainViewModel>(_trainList.values());
        return trains;
    }
 }