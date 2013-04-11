package TLTTC;

import java.util.*;


public class TrackModel extends Worker implements constData
{
    private HashMap<Integer, Block> blocks;
    private HashMap<Integer, Node> nodes;

    public TrackModel()
    {
    
    }
    
    public void initTrack()
    {
    
    
    }

    public void run(){}
    

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