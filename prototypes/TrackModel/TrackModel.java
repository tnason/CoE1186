package TLTTC;

import java.util.HashMap;


public class TrackModel extends Worker
{
    private HashMap<Integer, Block> blocks;
    private HashMap<Integer, Node> nodes;
    private java.util.concurrent.LinkedBlockingQueue<Message> msgs;
    
    public TrackModel (Module name) {
        msgs = new java.util.concurrent.LinkedBlockingQueue<Message>();
    }

    public void run() {
        while (true) {
        
        }
    }
    
    public TrackModel()
    {
    
    }
    
    public void initTrack()
    {
    
    
    }
    
    public void setMsg(Message m) 
    {
        msgs.add(m);
    }
    

    //handlers-----------------------------------

    //TnCt_TcMd_Request_Track_Speed_Limit
    public void getBlockSpeedLimit(int blockID)
    {
      //gets a block id 
    }


    //pass throughs------------------------------

    //CTC_TnMd_Request_Train_Creation
    public void relayTrainCreationMsg()
    {
    
    }

}