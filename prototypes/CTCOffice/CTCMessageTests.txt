package TLTTC;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CTCMessageTests {
    
    public Message msg;
    public CTCMessageServer msgServer = new msgServer();
    
    @Before
    public void setup () {
        // do any set up (i.e. create a message to assess the message server ability
        msg = new Message(); // TODO: Implement options passing
    }
    
    @Test
    /**
     * Testing to assess whether the message server correctly responds to a message in which an unknown message type is sent
     *
     */
    public void unknownMessageTypeResponse () {
        // set the msg type to an alternative, unknown value.
        msg.setMessageType("Some_Unknown_Message_Type");
        // execute msgServer with given Message
        msgServer.parseMessage(msg); // TODO - Assert this equal to something
    }
    /**
     * Testing to assess whether the message server correctly passes along a message in which it is not the final destination
     */
    public void notFinalMessageDestination () {
        // set the message destination to non-CTC module
        msg.setDestination(Module.trackController);
        // execute msgServer with given Message
        
    }
    

}