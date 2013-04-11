package TLTTC;

import java.util.*;
import java.io.*;
import java.util.concurrent.*;


public class TrackModel extends Worker implements Runnable, constData
{
	

    private HashMap<Integer, Block> blocks = new HashMap<Integer, Block>();
    private HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

    public  HashMap<Integer, Block> getBlocks(){
    	return blocks;
    }
    
    
    private LinkedBlockingQueue<Message> msgs;
    private Module name = Module.trackModel;
    
    public TrackModel () {
        msgs = new LinkedBlockingQueue<Message>();
    }

    public void run() {
        while (true) 
        {
            if(msgs.peek() != null)
            {
                Message m = msgs.poll();
    
                if(name == m.getDest())
                {
                    System.out.println("RECEIVED MESSAGE ~ (source : " + m.getSource() + "), (dest : " + m.getDest() + ")\n");
  
                	//_---------------my handlers in here
                
                
                }
                else
                {

                    if(m.getType() == msg.CTC_TnMd_Request_Train_Creation)
                    {
                        m.addData("yard",blocks.get(1)); // FIX THIS!
                    }

                    System.out.println("PASSING MSG ~ (source : " + m.getSource() + "), (step : " + name + "), (dest : "+m.getDest()+")");
                    m.updateSender(name);
                    Environment.passMessage(m);
                }
            }
        }
    }
  
    public void initTrack()
    {
        /*try
        {
            Scanner s = new Scanner(new File("layout_new.txt"));

            while(s.hasNextLine())
            {
                String line = s.nextLine();

                if(line.startsWith("#"))
                    continue;
                if(line.equals("-1"))
                    break;



            }
        } 
        catch (Exception e)
        {
                //System.out.println(e);
        }*/
            Node node1 = new YardNode(0,0,0);
            Node node2 = new ConnectorNode(200,0,0);
            Node node3 = new ConnectorNode(400,0,1);
            Node node4 = new ConnectorNode(600,0,2);
            Node node5 = new ConnectorNode(800,0,2);
            Node node6 = new ConnectorNode(1000,0,1);
            Node node7 = new ConnectorNode(1200,0,0);
            Node node8 = new YardNode(1400,0,0);            
            
            LinearBlock block1 = new LinearBlock(node1, node2);
            LinearBlock block2 = new LinearBlock(node2, node3);
            LinearBlock block3 = new LinearBlock(node3, node4);
            LinearBlock block4 = new LinearBlock(node4, node5);
            LinearBlock block5 = new LinearBlock(node5, node6);
            LinearBlock block6 = new LinearBlock(node6, node7);
            LinearBlock block7 = new LinearBlock(node7, node8);
            
            blocks.put(1, block1);
            blocks.put(2, block2);
            blocks.put(3, block3);
            blocks.put(4, block4);  
            blocks.put(5, block5);
            blocks.put(6, block6);            
            blocks.put(7, block7);
                
            //assign to the static so anyone can get at this  
    }

    
    public void setMsg(Message m) 
    {
        msgs.add(m);
    }

    public void send(Message m)
    {
        System.out.println("SENDING MSG ~ (start : "+m.getSource() + "), (dest : "+m.getDest()+"), (type : " + m.getType()+ ")");
        m.updateSender(name);
        Environment.passMessage(m);
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