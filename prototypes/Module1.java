public class Module1 extends Messaging {
	
	Message temp;
	
	public void setNeighbors(Messaging m) {
		rightNeighbor = m;
	}
	
	public Message createMessage(MessageType t) {
		return new Message(t);
	}
	
	public void passMessage() {
		return;
	}
	
	public void sendMessage(Message m) {
		rightNeighbor.handleMessage(m);
	}
	
	public void handleMessage(Message m) {
		if(m.type == MessageType.MESSAGE_3) {
			//receive message from Module2
			System.out.println("Mod1 received message from Mod2");
		} else if (m.type == MessageType.MESSAGE_5) {
			//receive message from Module3
			System.out.println("Mod1 received message from Mod3");
		} else {
			//error!
		}
	}
	
}