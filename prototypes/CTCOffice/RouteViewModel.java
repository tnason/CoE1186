/*
 *
 */

// Imports and Packages
package TLTTC;
import java.util.*;

public class RouteViewModel
{
    ArrayList<Integer> _route;
    TrainViewModel _train;
    RouteViewModel (TrainViewModel train)
    {
        _train = train;
        _route = new ArrayList<Integer>();
    }
    
    public Integer getNextBlock( Integer currentBlock )
    {
        int index = _route.indexOf(currentBlock);
        index++;
        if ( index < _route.size() )
        {
            return _route.get(index);
        }
        else
        {
            return null;
        }
    }
    
    public void removeBlockFromRoute( Integer bID )
    {
        if (_route.contains(bID))
        {
            _route.remove(_route.indexOf(bID));
        }
    }
    
    public void addBlockToRouteAtIndex( Integer bID, int index)
    {
        _route.add(index, bID); // this is probably dangerous
    }
    public void addBlockToRoute( Integer bID )
    {
        _route.add(bID);
    }
    public void changeBlockOfRouteAtIndex( Integer bID, int index)
    {
        _route.set(index, bID); // This is probably dangerous
    }
}