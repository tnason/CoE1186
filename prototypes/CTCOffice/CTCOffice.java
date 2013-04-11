package TLTTC;
import java.util.*;
import java.util.concurrent.*;


public class CTCOffice extends Worker implements Runnable, constData {
    
    
    // ivars! Yay!
    private static String _sender = "CTC";
    private java.util.concurrent.LinkedBlockingQueue<Message> msgs;
    private Module name = Module.CTC; //CTCOffice?
    private HashMap<Integer, TrainViewModel> _trainList = new HashMap<Integer, TrainViewModel>();
    private HashMap<Integer, BlockViewModel> _blockList = new HashMap<Integer, BlockViewModel>();
    // 
    private int _trainCount = 0;
    
    CTCOffice () {
        // Do any set-up needed, launch the GUI, and get it on!
        msgs = new java.util.concurrent.LinkedBlockingQueue<Message>();
        // generate a virtual model of the track, boi!
        
        // 
    }
    
    public void run () {
        Integer tID, bID;
        while(true) {
            if (msgs.peek() != null)
                {
                
                Message m = msgs.poll();
                if (name == m.getDest())
                { // hey, this was sent to me; let's do something
                    System.out.println("RECEIVED MESSAGE ~ (source : " + m.getSource() + "), (dest : " + m.getDest() + ")");
                    switch (m.getType()) {
		                case TnMd_CTC_Confirm_Train_Creation: // hey a train really did get made!
			                // unpack the data from the message
			                tID = (Integer) m.getData().get("trainID");
			                addTrainToTrainList(tID);
                        break; // end Confirm train creation case
		                case TnMd_CTC_Request_Train_Destruction: // Aw snap! Train's going away, better let everyone know
			                tID = (Integer) m.getData().get("trainID");
			                removeTrainFromTrainList(tID);
                        break; // end train destruction case
		                case TnMd_CTC_Send_Block_Occupied: // Train has definitely moved to a new block; let e'rybody know
		                    bID = (Integer) m.getData().get("blockID");
		                break; // end block occupied case
		                case Sch_CTC_Send_Schedule:
		                    // probably do something here
		                break;
		                default:// Well shit, 
		                    
		                System.out.println("Unknown message Type!");
		                
		                break;
                    }
                }
                else { // it ain't ours
                    System.out.println("PASSING MSG ~ (source : " + m.getSource() + "), (step : " + name + "), (dest : "+m.getDest()+")");
                    m.updateSender(name);
                    Environment.passMessage(m);
                }
            }
        }
    }
    
    public void setMsg(Message m) {
        msgs.add(m);
    }
    
    public void send(Message m)
    {
        System.out.println("SENDING MSG ~ (start : "+m.getSource() + "), (dest : "+m.getDest()+"), (type : " + m.getType()+ ")");
        Environment.passMessage(m);
    }
    
     public void dispatchTrain () { // probably other vars needed
    
        // Compose a message to train. Holla at your boy!
        Message m = new Message(Module.CTC, Module.CTC, Module.trainModel, msg.CTC_TnMd_Request_Train_Creation, new String[] {"trainID"}, new Object[] {_trainCount});
        send(m);
        
        _trainCount++;
    }
    
    public void addTrainToTrainList(Integer tID) {
        TrainViewModel train = new TrainViewModel(tID);
        _trainList.put(tID, train);
    }
    
    public void removeTrainFromTrainList(Integer tID) {
        if (_trainList.containsKey(tID)) {
            _trainList.remove(tID);
        }
    }
    
    public void updateBlockOccupancy( Integer bID ) {
        if (_trainList.size() == 1) {
            // lazy, I know, but why do more work!
            TrainViewModel train = _trainList.get(1);
            train.setCurrentBlockID(bID);
            _trainList.put(train.getTrainID(), train);
        }
        else if (_trainList.size() == 0) {
            // sumthin' weird's happenin' over yar...
        }
        else {
            // a little more complex
            Collection<TrainViewModel> trains = _trainList.values();
            for (TrainViewModel train : trains) {
                int cBID = train.getCurrentBlockID();
                if (cBID + 1 == bID || cBID - 1 == bID) {
                    // THIS IS NOT GOING TO WORK WITH SWITCHES, HAVE TO USE A GENERATED ROUTE TO DETERMINE TRAIN THAT MOVED
                    train.setCurrentBlockID(bID);
                    _trainList.put(train.getTrainID(), train);
                    break;
                }
            }
        }
    }
    
    public void generateSchedule() { // vars?
        
    }
    
    public void setFixedAuthority (int tID, int authority) { // yeah, need other stuff too!
        
        if (authority >= 0) { // Technically, there shouldn't be any checks on authority, but come on
            // set in the model
            TrainViewModel train = _trainList.get(tID);
            train.setFixedBlockAuthority(authority);
            _trainList.put(tID, train);            
            // create a message to send to the train controller
            Message m = new Message(Module.CTC, Module.CTC, Module.trainController, msg.CTC_TnCt_Send_Manual_FixedBlock, new String[] {"trainID", "authority"}, new Object[] {tID, authority});
        }
        else {
            System.out.println("Respect my authoritah! And make that number positive!");
        }
    }
    
    public void setMovingAuthority (int tID, double authority) {
        if (authority >= 0) { // Technically, there shouldn't be any checks on authority, but come on
            // set in the model
            TrainViewModel train = _trainList.get(tID);
            train.setMovingBlockAuthority(authority);
            _trainList.put(tID, train);            
            // create a message to send to the train controller
            Message m = new Message(Module.CTC, Module.CTC, Module.trainController, msg.CTC_TnCt_Send_Manual_MovingBlock, new String[] {"trainID", "authority"}, new Object[] {tID, authority});
        }
        else {
            System.out.println("Respect my (moving) authoritah! And make that number positive!");
        }
    }
    
    public void setSpeed(int tID, double speed) { // and other shit, too
    
        if (speed >= 0) {
            TrainViewModel train = _trainList.get(tID);
            train.setSpeed(speed);
            _trainList.put(tID, train);            
            // create a message to send to the train controller
            
            // convert speed to m/s from mph
            speed = speed * (.44704);
            
            Message m = new Message(Module.CTC, Module.CTC, Module.trainController, msg.CTC_TnCt_Send_Manual_Speed, new String[] {"trainID", "velocity"}, new Object[] {tID, speed});
        }
        else {
            System.out.println("Naw man, that speed don't make no sense!");
        } 
    }
}



class TrainViewModel
{
    // ivars and such
    private final int _trainID;
    private int _fixedBlockAuthority;
    private double _movingBlockAuthority;
    private double _speed;
    private int _currentBlockID;
    private int _currentControllerID;
    
    // methods. Get/set some!
    public int getTrainID () {
        return _trainID;
    }
    
    public int getFixedBlockAuthority() {
        return _fixedBlockAuthority;   
    }
    
    public void setFixedBlockAuthority(int authority) {
        if (authority >= 0) {
            _fixedBlockAuthority = authority;
        }
    }
    
    public double getMovingBlockAuthority() {
        return _movingBlockAuthority;
    }
    
    public void setMovingBlockAuthority(double authority) {
        if (authority >= 0) {
            _movingBlockAuthority = authority;
        }
    }
    
    public double getSpeed() {
        return _speed;
    }
    
    public void setSpeed(double speed) {
        if (speed >= 0) {
            _speed = speed;
        }
    }
    
    public int getCurrentBlockID() {
        return _currentBlockID;
    }
    
    public void setCurrentBlockID(int id) {
        _currentBlockID = id;
    }
    
    public int getCurrentControllerID() {
        return _currentControllerID;
    }
    
    public int setCurrentControllerID(int id) {
        return _currentControllerID;
    }
    
    // some REAL methods:
    TrainViewModel(int tID)
    {
        _trainID = tID;
        _currentBlockID = 0;
        _currentControllerID = 0;
        _fixedBlockAuthority = 0;
        _movingBlockAuthority = 0;
        _speed = 0;
    }
    
    TrainViewModel(int tID, int bID, int cID)
    {
    // Convenience initializer to set custom block/controller ids.
        _trainID = tID;
        _currentBlockID = bID;
        _currentControllerID = cID;
        _fixedBlockAuthority = 0;
        _movingBlockAuthority = 0;
        _speed = 0;
    }
    TrainViewModel( int tID, int bID, int cID, int fBA, int mBA, int s)
    {
    // Convenience to set all ivars at init.
        _trainID = tID;
        _currentBlockID = bID;
        _currentControllerID = cID;
        _fixedBlockAuthority = fBA;
        _movingBlockAuthority = mBA;
        _speed = s;
    }
}

class BlockViewModel
{
    private final int _blockID;
    private int _currentOccupantID;
    private final int _controllerID;
    
    // get/set methods
    public int getBlockID() {
        return _blockID;
    }
    
     public int getControllerID() {
        return _controllerID;
    }
    
    public int getCurrentOccupantID() {
        return _currentOccupantID;
    }
    
    public void setCurrentOccupantID (int id) {
        if (id >= -1) {
            _currentOccupantID = id;
        }
    }
    
    // Constructors
    public BlockViewModel (int bID, int cID) {
        _blockID = bID;
        _controllerID = cID;
        _currentOccupantID = -1;
    }
    
    // Other Methods
    
    public boolean isOccupied() {
        if (_currentOccupantID >= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

/*
class ControllerViewModel
{
    // ivars and all that
    private final int _controllerID;
    private final ArrayList<Integer> _containedBlockIDs;
    private ArrayList<Integer> _containedTrainIDs;
    
    // get/set methods
    public int getControllerID() {
        return _controllerID;
    }
    
    public ArrayList<Integer> getContainedBlockIDs() {
        return _containedBlockIDs;
    }
    
    public ArrayList<Integer> getContainedTrainIDs() {
        return _containedTrainIDs;
    }
    
    public void addContainedTrainID (int id) {
        if (id >= 0) {
            _containedTrainIDs.add(id);
        }
    }
    
    public int removeContainedTrainID (int id) {
        int tID = -1;
        if (_containedTrainIDs.contains(id)) {
            int tID = _containedTrainIDs.remove(id);
        }
        
        return tID;
    }
    
   
}*/
