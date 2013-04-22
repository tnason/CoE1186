/*
 *
 */

// Imports and Packages
package TLTTC;
import java.util.*;

public class BlockViewModel
{
    private Integer _blockID;
    private Integer _occupantID = -1;
    private boolean _occupied = false;
    
    BlockViewModel ( Integer bID )
    {
        _blockID = bID;
    }
    
    public void setCurrentTrain ( Integer tID )
    {
        if (!_occupied)
        {
            _occupantID = tID;
            _occupied = true;
        }
    }
    
    public void setVacant ()
    {
        _occupied = false;
        _occupantID = -1;
    }
    
    public boolean isOccupied ()
    {
        return _occupied;
    }
    
    public Integer getBlockID ()
    {
        return _blockID;
    }
}