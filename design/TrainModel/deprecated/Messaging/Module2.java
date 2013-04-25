public class Module2 extends Messaging {
	

	public void setNeighbors(Messaging m, Messaging m2) {
		leftNeighbor = m;
		rightNeighbor = m2;
	}
	
	public Message createMessage(MessageType t) {
		return new Message(t);
	}
	
	public void passMessage(Message m) {
		if(m.type == MessageType.MESSAGE_2) {
			rightNeighbor.handleMessage(m);
		} else if(m.type == MessageType.MESSAGE_5) {
			leftNeighbor.handleMessage(m);
		}
	}
	
	public void sendMessage(Message m) {
		if(m.type == MessageType.MESSAGE_3) {
			leftNeighbor.handleMessage(m);
		} else if (m.type == MessageType.MESSAGE_4) { 
			rightNeighbor.handleMessage(m);
		}
	}
	
	public void handleMessage(Message m) {
		if(m.type == MessageType.MESSAGE_1) {
			//receive message from Module1
			System.out.println("Mod2 received message from Mod1");
		} else if (m.type == MessageType.MESSAGE_6) {
			//receive message from Module3
			System.out.println("Mod2 received message from Mod3");
		} else if (m.type == MessageType.MESSAGE_2 || m.type == MessageType.MESSAGE_5) {
			System.out.println("Mod2 passed message");
			passMessage(m);
		} else {
			//error!
		}
	}
	
}