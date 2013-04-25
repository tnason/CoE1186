package TLTTC;

import java.util.*;
import java.io.*;
import java.util.concurrent.*;


public class TrackModel extends Worker implements Runnable, constData
{
	public TrackModel()
	{
		blocks = new HashMap<Integer, Block>();
		nodes = new HashMap<Integer, Node>();
                msgs = new LinkedBlockingQueue<Message>();
	}

        private HashMap<Integer, Block> blocks;
        private HashMap<Integer, Node> nodes;

        public HashMap<Integer, Block> getBlocks(){
    	    return blocks;
        }
    
        public String toString(){
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
    
    public void setBlockMaintenanceByID(int blockID, boolean state){
    	blocks.get(new Integer(blockID)).setMaintenance(state);
    }
    
    public void setBlockOccupationByID(int blockID, boolean state){
    	blocks.get(new Integer(blockID)).setOccupation(state);    	
    }
    
    
///*    
    private LinkedBlockingQueue<Message> msgs;
    private Module name = Module.trackModel;

    public void run() {
        while (true) 
        {
            if(msgs.peek() != null)
            {
                Message m = msgs.poll();
    
                if(name == m.getDest())
                {
                    //System.out.println("TrackModel RECEIVED MESSAGE ~ (source : " + m.getSource() + "), (dest : " + m.getDest() + ")\n");
                    //System.out.println("Unhandled...");	
                	//_---------------my handlers in here

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
                        m.addData("yardBlock",blocks.get(1)); // FIX THIS!
                        m.addData("yardNode", nodes.get(1));
                    }

                    //System.out.println("PASSING MSG ~ (source : " + m.getSource() + "), (step : " + name + "), (dest : "+m.getDest()+")");
                    m.updateSender(name);
                    Environment.passMessage(m);
                }
            }
        }
    }
//*/  
    public void init()
    {
	
	TrackModelUI userInterface = new TrackModelUI();

        try
        {

	    //to force it to use the old prototype layout throw an error here

	
	    File f = new File("layout.txt");
            Scanner s = new Scanner(f);
            int i = 0;

	    System.out.println(null + "foo");

            while(s.hasNextLine())
            {
                String line = s.nextLine();
		System.out.println("\nparsing text \"" + line +"\"");
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
		    System.out.println("new yard parsed " + nodes.get(id).toString());                
		}
                else if (nodeAttr[0].equals("connection"))
                {
		    //read <x> <y> <z>
                    nodes.put(id, new ConnectorNode(Double.parseDouble(nodeAttr[2]),
                                                    Double.parseDouble(nodeAttr[3]),
                                                    Double.parseDouble(nodeAttr[4])));
		    System.out.println("new connection parsed " + nodes.get(id).toString());
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

	    userInterface.refresh();
        	
     
 	    System.out.println("\n\n foo" + blocks);           
	          
        } 
        catch (Exception e)
        {

	    

            System.out.println(e.getMessage());
            /*    
	    Node node1 = new YardNode(0,0,0);
            Node node2 = new ConnectorNode(200,0,0);
            Node node3 = new ConnectorNode(400,0,1);
            Node node4 = new ConnectorNode(600,0,2);
            Node node5 = new ConnectorNode(800,0,2);
            Node node6 = new SwitchNode(1000,0,1);
            Node node7 = new ConnectorNode(1200,0,0);
            Node node8 = new YardNode(1400,0,0);            
            
            nodes.put(1, node1);
            nodes.put(2, node2);            
            nodes.put(3, node3);
            nodes.put(4, node4);
            nodes.put(5, node5);
            nodes.put(6, node6);
            nodes.put(7, node7);
            nodes.put(8, node8);            
            
            
            LinearBlock block1 = new LinearBlock(node1, node2, 1 , 0 );
            LinearBlock block2 = new LinearBlock(node2, node3, 2 , 0);
            LinearBlock block3 = new LinearBlock(node3, node4, 3 , 0 );
            LinearBlock block4 = new LinearBlock(node4, node5, 4 , 0 );
            LinearBlock block5 = new LinearBlock(node5, node6, 5 , 1 );
            LinearBlock block6 = new LinearBlock(node6, node7, 6 , 1 );
            LinearBlock block7 = new LinearBlock(node7, node8, 7 , 1 );

            node1.setOutput(block1);

            node2.setInput(block1);
            node2.setOutput(block2);

            node3.setInput(block2);
            node3.setOutput(block3);

            node4.setInput(block3);
            node4.setOutput(block4);

            node5.setInput(block4);
            node5.setOutput(block5);

            node6.setInput(block5);
            node6.setOutput(block6);

            node7.setInput(block6);
            node7.setOutput(block7);

            node8.setInput(block7);



            block4.addController(1);
            
            blocks.put(1, block1);
            blocks.put(2, block2);
            blocks.put(3, block3);
            blocks.put(4, block4);  
            blocks.put(5, block5);
            blocks.put(6, block6);            
            blocks.put(7, block7);
	    */
        }
    	
    
    }

///*  
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
//*/
}
