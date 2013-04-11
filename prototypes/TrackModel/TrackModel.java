package TLTTC;

import java.util.HashMap;


public class TrackModel extends Worker
{
    private HashMap<int, Block> blocks;
    private HashMap<int, Node> nodes;

    public TrackModel()
    {
    
    }
    
    public void initTrack()
    {
    
    
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