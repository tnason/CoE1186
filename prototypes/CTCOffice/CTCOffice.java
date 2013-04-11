package TLTTC;
import java.util.*;
import java.util.concurrent.*;


public class CTCOffice extends Worker implements Runnable, constData {
    
    
    // ivars! Yay!
    private static String _sender = "CTC";
    private java.util.concurrent.LinkedBlockingQueue<Message> msgs;
    private Module name = Module.CTC; //CTCOffice?
    // private ArrayList<BlockViewModel> _blockList;
    // private ArrayList<ControllerViewModel> _controllerList;
    private ArrayList<TrainViewModel> _trainList;
    // 
    
    CTCOffice (Module name) {
        // Do any set-up needed, launch the GUI, and get it on!
        msgs = new java.util.concurrent.LinkedBlockingQueue<Message>();
        // generate a virtual model of the track, boi!
        
        // 
    }
    
    public void run () {
        while(true) {
            if (msgs.peek() != null) {
                
                Message m = msgs.poll();
                if (name == m.getDest()) { // hey, this was sent to me; let's do something
                    switch (m.getType()) {
                        case constData.TnMd_CTC_Confirm_Train_Creation: // hey a train really did get made!
                           // unpack the data from the message
                           m.getData().get("trainID");
                        break; // end Confirm train creation case
                        case TnMd_CTC_Request_Train_Destruction: // Aw snap! Train's going away, better let everyone know
                            m.getData().get("trainID");
                        break; // end train destruction case
		                case TnMd_CTC_Send_Block_Occupied: // Train has definitely moved to a new block; let e'rybody know
		                
		                break; // end block occupied case
		                /*
		                 * Other cases will be implemented here in the future.
		                 */
		                default: // Well shit, somebody fucked up this one.
		                    
		                System.out.println("Unknown message Type!");
                    }
                }
                else { // it ain't ours, get that shit outta here!
                    System.out.println("PASSING MSG: step->"+name + " source->"+m.getSource()+ " dest->"+m.getDest());
          m.updateSender(name);
          Environment.passMessage(m);
                }
            }
        }
    }
    
    public void setMsg(Message m) {
        msgs.add(m);
    }
    
    public void send(Message m) {
        
    }
}
/*
class CTCController
{
    
    public void dispatchTrain (String line) { // probably other vars needed
    
    // Compose a message to train. Holla at your boy!
    }
    
    public void generateSchedule() { // vars?
        
    }
    
    public void setAuthority (int authority) { // yeah, need other stuff too!
        
        if (authority >= 0) { // Technically, there shouldn't be any checks on authority, but come on
            // create a message to send to the train controller
            System.out.println("Good authority, let's send it!");
        }
        else {
            System.out.println("Respect my authoritah! And make that number positive!");
        }
    }
    
    public void setSpeed(double speed) { // and other shit, too
    
    if (speed >= 0) {
        System.out.println("Good speed, let's send it!");
    }
    else {
        System.out.println("Naw man, that speed don't make no sense!");
    }
        
    }
    
    private void updateBlockForTrain(int blockID, int trainID) {
    
    }
    
    private void updateControllerForTrain(int controllerID, int trainID) {
    
    }
}
*/
class TrainViewModel
{
    // ivars and such
    
    // methods. Get/set some!
}
/*
class blockViewModel
{
    
}

class controllerViewModel
{

}
*/