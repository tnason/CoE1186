// TODO: Implement Route, Schedule, nextBlock

package TLTTC;
import java.util.*;

public class TrainViewModel
{
    // ivars
    private Integer _trainID;
    private Integer _fixedBlockAuthority = 1;
    private Double _movingBlockAuthority = 0;
    private Integer _currentBlock;
    private Double _speed = 0;
    private String _line;
    private Integer _nextBlock;
    
    TrainViewModel ( Integer tID, String line )
    {
        _trainID = tID;
        _line = line;
        
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
        return nextBlock;
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
    
    public Integer getLine ()
    {
        return _line;
    }
    
    public Integer getTrainID ()
    {
        return _trainID;
    }
}