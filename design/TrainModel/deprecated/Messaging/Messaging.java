public abstract class Messaging {
	
	Messaging leftNeighbor;
	Messaging rightNeighbor;
	
	void setNeighbors(Messaging m) {
		return;
	}
	void setNeighbors(Messaging m, Messaging m2) {
		return;
	}
	abstract Message createMessage(MessageType t);
	void passMessage(Message m) {
		return;
	}
	abstract void sendMessage(Message m);
	abstract void handleMessage(Message m);
	
}	