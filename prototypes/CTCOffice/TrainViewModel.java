// TODO: Implement Route, Schedule, nextBlock

package TLTTC;
import java.util.*;

public class TrainViewModel
{
    // ivars
    private Integer _trainID;
    private Integer _fixedBlockAuthority = (Integer) 1;
    private Double _movingBlockAuthority = (Double) 0.0;
    private Integer _currentBlock;
    private Double _speed = (Double) 0.0;
    private String _line;
    private Integer _nextBlock;
    private boolean _active = false;
    private RouteViewModel _route;
    
    TrainViewModel ( Integer tID, String line )
    {
        _trainID = tID;
        _line = line;
        _route = new RouteViewModel(this);
        
        if (line == "green")
        {
            _currentBlock = 0;
        }
        else
        {
            _currentBlock = 0; // CHANGE TO CORRECT BLOCK
        }
    }
    
    public Integer getCurrentBlock () 
    {
        return _currentBlock;
    }
    
    public void setCurrentBlock ( Integer bID )
    {
        _currentBlock = bID;
    }
    
    public Integer getNextBlock ()
    {
        return _nextBlock;
    }
    
    public Double getSpeed ()
    {
        return _speed;
    }
    
    public void setSpeed ( Double s )
    {
        if (s > 0)
        {
            _speed = s;
        }
    }
    
    public Integer getFixedBlockAuthority ()
    {
        return _fixedBlockAuthority;
    }
    
    public void setFixedBlockAuthority ( Integer authority )
    {
        if (authority > 0)
        {
            _fixedBlockAuthority = authority;
        }
    }
    
    public Double getMovingBlockAuthority ()
    {
        return _movingBlockAuthority;
    }
    
    public void setMovingBlockAuthority ( Double authority )
    {
        if (authority > 0)
        {
            _movingBlockAuthority = authority;
        }
    }
    
    public String getLine ()
    {
        return _line;
    }
    
    public Integer getTrainID ()
    {
        return _trainID;
    }
    
    public void setActive ()
    {
        _active = true;
    }
    
    public boolean isActive ()
    {
        return _active;
    }
    
    public String getNextStation ()
    {
        return "Station 01"; //TODO - Implement this, yo!
    }
    
    public String getScheduleStatus()
    {
        return "On Time"; // TODO - implement this, yo!
    }
    public ArrayList<Integer> getRouteListing ()
    {
        ArrayList<Integer> routeList = new ArrayList<Integer>();
        routeList.add(0);
        routeList.add(1);
        routeList.add(2);
        return routeList;
        // TODO write this for realz
    }
}