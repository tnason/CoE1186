public class Module3 extends Messaging {
		
	public void setNeighbors(Messaging m) {
		leftNeighbor = m;
	}
	
	public Message createMessage(MessageType t) {
		return new Message(t);
	}
	
	public void passMessage() {
		return;
	}
	
	public void sendMessage(Message m) {
		leftNeighbor.handleMessage(m);
	}
	
	public void handleMessage(Message m) {
		if(m.type == MessageType.MESSAGE_2) {
			//receive message from Module1
			System.out.println("Mod3 received message from Mod1");
		} else if (m.type == MessageType.MESSAGE_4) {
			//receive message from Module2
			System.out.println("Mod3 received message from Mod2");
		} else {
			//error!
		}
	}
	
}