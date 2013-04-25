/*
 *
 */

// Imports and Packages
package TLTTC;
import java.util.*;


public class CTCController implements constData
{
    
    private CTCMessageServer _msgServer;
    private TrainList _trainList;
    private BlockList _blockList;
    private ControllerList _controllerList;
    private ScheduleViewModel _schedule;
    private int _trainCount = 0; //this needs to be an int
    private CTCUI _CTCInterface;
    private double kMilesToMetersConversion = .44704;
    
    CTCController ( CTCMessageServer msgServer ) 
    {
        _msgServer = msgServer;
        _trainList = new TrainList( this );
        _blockList = new BlockList( this );
        _controllerList = new ControllerList ( this );
        _schedule = new ScheduleViewModel( this );
        _CTCInterface = new CTCUI( this );
        _CTCInterface.setVisible(true);
    }
    
    // Inbound handlers
    public void updateSchedules ( Timetable t ) 
    {
        
    }
    
    public void addTrain ( int tID )
    {
        if (_trainList.contains(tID))
        {
            _trainList.setActive(tID);
        }
        else
        {
            _trainList.addTrain(tID, _CTCInterface.getCurrentLine());
            _trainList.setActive(tID);
        }
        _CTCInterface.setDataModelForTable(_trainList.getTrain(tID));
    }
    
    public void removeTrain ( int tID )
    {
        if (_trainList.contains(tID))
        {
            _trainList.removeTrain(tID);
        }
    }
    
    public void updateOccupancy ( int bID )
    {   
        // determine which train this "block" belongs to
        int tID = _trainList.nextBlocksForTrains().get(bID);
        updateOccupancy(bID, tID);    
    }
    
    public void updateOccupancy( int bID, int tID )
    {
        int vacantBlockID = _trainList.getTrain(tID).getCurrentBlock();
        _blockList.getBlock(bID).setVacant();
        _trainList.getTrain(tID).setCurrentBlock(bID);
        _blockList.getBlock(bID).setCurrentTrain(tID);
        _CTCInterface.setDataModelForTable(_trainList.getTrain(tID));
    }
    
    // outbound handlers
    public void sendManualSpeed ( Double speed, int tID )
    {
        Hashtable<String, Object> data = new Hashtable<String, Object>();
        data.put("velocity", speed);
        data.put("trainID", tID);
        Module destination = Module.trainController;
        msg type = msg.CTC_TnCt_Send_Manual_Speed;
        _msgServer.composeMessage(destination, type, data);
    }
    
    public void sendManualMovingBlockAuthority ( Double authority, int tID )
    {
        Hashtable<String, Object> data = new Hashtable<String, Object>();
        data.put("authority", authority);
        data.put("trainID", tID);
        Module destination = Module.trainController;
        msg type = msg.CTC_TnCt_Send_Manual_MovingBlock;
        _msgServer.composeMessage(destination, type, data);
    }
    
    public void sendManualFixedBlockAuthority ( Integer authority, int tID )
    {
        Hashtable<String, Object> data = new Hashtable<String, Object>();
        data.put("authority", authority);
        data.put("trainID", tID);
        Module destination = Module.trainController;
        msg type = msg.CTC_TnCt_Send_Manual_FixedBlock;
        _msgServer.composeMessage(destination, type, data);
    }
    
    public void dispatchTrain ( String line )
    {
        Hashtable<String, Object> data = new Hashtable<String, Object>();
        data.put("trainID", _trainCount);
        data.put("line", line);
        Module destination = Module.trainModel;
        msg type = msg.CTC_TnMd_Request_Train_Creation;
        _msgServer.composeMessage(destination, type, data);
		//should wait for confirmation
        _trainList.addTrain(_trainCount, line);
        _CTCInterface.setDataModelForTable(_trainList.getTrain(_trainCount));
        _trainCount++;
    }
    
    public void generateSchedule ( int tID ) {
        Hashtable<String, Object> data = new Hashtable<String, Object>();
        // determine line for train
        String line = _trainList.getTrain(tID).getLine();
        data.put("trainID", tID);
        data.put("line", line);
        Module destination = Module.scheduler;
        msg type = msg.CTC_Sch_Generate_Schedule;
        _msgServer.composeMessage(destination, type, data);
        
    }
    
    public void closeTrackSections ( ArrayList<Integer> bIDs )
    {
        Hashtable<String, Object> data = new Hashtable<String, Object>();
        data.put("blockIDs", bIDs);
        Module destination = Module.trackModel;
        msg type = msg.CTC_TcMd_Send_Track_Closing;
        _msgServer.composeMessage(destination, type, data);   
    }
    
    public void openTrackSections ( ArrayList<Integer> bIDs )
    {
        Hashtable<String, Object> data = new Hashtable<String, Object>();
        data.put("blockIDs", bIDs);
        Module destination = Module.trackModel;
        msg type = msg.CTC_TcMd_Send_Track_Opening;
        _msgServer.composeMessage(destination, type, data);
    }
    
    public ArrayList<TrainViewModel> getTrainList ()
    {
        if (_trainList.trainCount() > 0)
        {
            return _trainList.getTrains(); // returns array list of train view models
        }
        else
        {
            return null; // be sure to check for this when called
        }
    }
    
    public void setSpeedForTrain ( int tID, Double speed )
    {
        // GUI calls this to set the speed of a train, so that controller can handle all the necessary interactions that must occur. Namely, a message must be sent and a model must be updated.
        
        _trainList.getTrain(tID).setSpeed(speed);
        _CTCInterface.setDataModelForTable(_trainList.getTrain(tID));
        sendManualSpeed(speed * kMilesToMetersConversion, tID);
    }
    
    public void setAuthorityForTrain ( int tID, Double authority)
    {
        _trainList.getTrain(tID).setMovingBlockAuthority(authority);
        _CTCInterface.setDataModelForTable(_trainList.getTrain(tID));
        sendManualMovingBlockAuthority(authority, tID);
    }
    
    public void setAuthorityForTrain( int tID, Integer authority)
    {
        _trainList.getTrain(tID).setFixedBlockAuthority(authority);
        _CTCInterface.setDataModelForTable(_trainList.getTrain(tID));
        sendManualFixedBlockAuthority(authority, tID);
    }
    
    public ArrayList<Integer> getRouteListingForTrain( int tID )
    {
        if (_trainList.contains(tID))
        {
            return _trainList.getTrain(tID).getRouteListing();
        }
        else return null;
    }
    
    public void reachedNextStation (int tID)
    {
        _trainList.getTrain(tID).nextStationReached();
        _CTCInterface.setDataModelForTable(_trainList.getTrain(tID));
    }
}
