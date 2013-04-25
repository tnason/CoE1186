package TLTTC;

import java.util.*;
import java.io.*;
import java.util.concurrent.*;


public class TrackModel extends Worker implements Runnable, constData
{
    private LinkedBlockingQueue<Message> msgs;
    private Module name = Module.trackModel;
    TrackModelUI userInterface;
    private HashMap<Integer, Block> blocks;
    private HashMap<Integer, Node> nodes;
    private int yn = -1;


	public TrackModel()
	{
	    blocks = new HashMap<Integer, Block>();
	    nodes = new HashMap<Integer, Node>();
        msgs = new LinkedBlockingQueue<Message>();
	}

    public HashMap<Integer, Block> getBlocks()
    {
    	return blocks;
    }
    
    public String toString()
	{
        StringBuilder sb = new StringBuilder();  
        sb.append("Listing Nodes\n");
        for(Integer iObj : nodes.keySet())
        {
            sb.append("\t");
        	sb.append(iObj);
        	sb.append(": ");
        	sb.append(nodes.get(iObj).toString());
        	sb.append("\n");	
        }
    	sb.append("Listing Blocks\n");
    	for(Integer iObj : blocks.keySet())
        {
    		sb.append("\t");
    		sb.append(iObj);
    		sb.append(": ");
    		sb.append(blocks.get(iObj).toString());
    		sb.append("\n");    		
    		
    	}
    	
    	return sb.toString();
    }
    
    public void setBlockMaintenanceByID(int blockID, boolean state)
    {
    	blocks.get(new Integer(blockID)).setMaintenance(state);
    }
    
    public void setBlockOccupationByID(int blockID, boolean state)
    {
    	blocks.get(new Integer(blockID)).setOccupation(state);    	
    }
    
    public void run() {

        long refreshRender = System.currentTimeMillis()+1000;
        while (true) 
        {
            if(System.currentTimeMillis() > refreshRender)
            {
                userInterface.refresh();
                refreshRender = System.currentTimeMillis() + 1000;
            }


            if(msgs.peek() != null)
            {
                Message m = msgs.poll();
    
                if(name == m.getDest())
                {
                    if(m.getType() == constData.msg.TnMd_TcMd_Request_Track_Speed_Limit){

                    	Hashtable<String, Object> mData = m.getData();
                    	

                    	Message response = new Message(Module.trackModel, Module.trackModel, Module.trainController, msg.TcMd_TnCt_Send_Track_Speed_Limit);
                    	response.addData("speedLimit", new Double(15.0));		//1 meter per second always
                    	response.addData("trainID", mData.get("trainID"));
                    	Environment.passMessage(response);
                    	
                    }                    
                }
                else
                {
                    if(m.getType() == msg.CTC_TnMd_Request_Train_Creation)
                    {
                        m.addData("yardBlock",blocks.get(63)); // FIX THIS!
                        m.addData("yardNode", nodes.get(yn));
                    }

                    m.updateSender(name);
                    Environment.passMessage(m);
                }
            }
        }
    }

    public void init()
    {
	    TrackModelForm form = new TrackModelForm();
    	form.setVisible(true);
	    userInterface = new TrackModelUI();

        try
        {
	        File f = new File("layout.txt");
            Scanner s = new Scanner(f);
            int i = 0;

            while(s.hasNextLine())
            {
                String line = s.nextLine();
		        //System.out.println("\nparsing text \"" + line +"\"");
                if(line.startsWith("#"))
                    continue;
		
		        //assume the line structure starts with <string type> <int id> <...>
                String [] nodeAttr = line.split(" ");
                int id = Integer.parseInt(nodeAttr[1]);


                if(nodeAttr[0].equals("yard"))
                {
		            //read <x> <y> <z>
                    nodes.put(id, new YardNode(Double.parseDouble(nodeAttr[2]),
                                               Double.parseDouble(nodeAttr[3]),
                                               Double.parseDouble(nodeAttr[4])));
		            //System.out.println("new yard parsed " + nodes.get(id).toString());  

                    yn = id;            
		        }
                else if (nodeAttr[0].equals("connection"))
                {
		            //read <x> <y> <z>
                    nodes.put(id, new ConnectorNode(Double.parseDouble(nodeAttr[2]),
                                                    Double.parseDouble(nodeAttr[3]),
                                                    Double.parseDouble(nodeAttr[4])));
		            //System.out.println("new connection parsed " + nodes.get(id).toString());
                }
		        else if (nodeAttr[0].equals("switch"))
		        {
		        //read <x> <y> <z>
		        nodes.put(new Integer(id), new SwitchNode(Double.parseDouble(nodeAttr[2]),
                                                 Double.parseDouble(nodeAttr[3]),
                                                 Double.parseDouble(nodeAttr[4])));
		        }
		        else if (nodeAttr[0].equals("linearblock"))
		        {
		        //read <startID> <stopID> <controllerID comma seperated list>
		            Node start = nodes.get(Integer.parseInt(nodeAttr[2]));
		            Node stop  = nodes.get(Integer.parseInt(nodeAttr[3]));
		      
		        String[] controllerIDStrings = nodeAttr[4].split(",");
		        int[] controllerIDNumbers = new int[controllerIDStrings.length];

		        for(int idx=0;idx<controllerIDStrings.length;idx++)
		        {
			        controllerIDNumbers[idx] = Integer.parseInt(controllerIDStrings[idx]);
		        }

		        LinearBlock newBlock = new LinearBlock(start, stop, id, controllerIDNumbers[0]);
		        for(int idx=1; idx < controllerIDNumbers.length; idx++)
			        newBlock.addController(controllerIDNumbers[idx]);

		        start.setOutput(newBlock);
		        stop.setInput(newBlock);

		        blocks.put(new Integer(id), (Block)newBlock);
		        System.out.println("new linearblock parsed " + blocks.get(id));
		        
		        userInterface.addBlockToRender(newBlock);
		    }
		    else if (nodeAttr[0].equals("arcblock"))
		    {   
		        //this does not actually render curves.
		        //for now it just draws lines between endpoints.		

		        //read <startID> <stopID> <controllerID comma seperated list>
		        Node start = nodes.get(Integer.parseInt(nodeAttr[2]));
		        Node stop  = nodes.get(Integer.parseInt(nodeAttr[3]));
		          
		        String[] controllerIDStrings = nodeAttr[4].split(",");
		        int[] controllerIDNumbers = new int[controllerIDStrings.length];

		        for(int idx=0;idx<controllerIDStrings.length;idx++)
		        {
			        controllerIDNumbers[idx] = Integer.parseInt(controllerIDStrings[idx]);
		        }

		        LinearBlock newBlock = new LinearBlock(start, stop, id, controllerIDNumbers[0]);
		        for(int idx=1; idx < controllerIDNumbers.length; idx++)
			        newBlock.addController(controllerIDNumbers[idx]);

		        start.setOutput(newBlock);
		        stop.setInput(newBlock);

		        blocks.put(new Integer(id), (Block)newBlock);
		        System.out.println("new linearblock parsed " + blocks.get(id));
		    
		        userInterface.addBlockToRender(newBlock);

		        }
		        else
		        {
		            //this should never happen!!!!!!!
		            System.out.println("cant parse that line? " + line);
		        }

                i++;
            }

	/*

			Block b1 = blocks.get(1);
			Block b12 = blocks.get(12);
			Block b13 = blocks.get(13);

			System.out.println("\n\n\n block1="+b1+"\n\nblock12="+b12+"\n\nblock13="+b13+"\n\n\n");

		        //hardcoded switches, replace if time permits
			SwitchNode switch1 = new SwitchNode(1300, 100, 0);
			switch1.setDivergingOutput(b1);
			switch1.setOutput(b12);
			switch1.setInput(b13);

			nodes.put(137, switch1);
			b1.setStartNode(switch1);
			b12.setStopNode(switch1);
			b13.setStartNode(switch1);
	*/		
	
			userInterface.refresh();
        	
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void setMsg(Message m) 
    {
        msgs.add(m);
    }

    public void send(Message m)
    {
        //System.out.println("SENDING MSG ~ (start : "+m.getSource() + "), (dest : "+m.getDest()+"), (type : " + m.getType()+ ")");
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
