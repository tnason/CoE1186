public class TestModuleMessaging {
	
	public static void main(String[] args) {
		Module1 one = new Module1();
		Module2 two = new Module2();
		Module3 three = new Module3();	

		one.setNeighbors(two);
		two.setNeighbors(one, three);
		three.setNeighbors(two);
		
		one.sendMessage(one.createMessage(MessageType.MESSAGE_2));
		three.sendMessage(three.createMessage(MessageType.MESSAGE_5));
		three.sendMessage(three.createMessage(MessageType.MESSAGE_6));
		two.sendMessage(two.createMessage(MessageType.MESSAGE_3));
		two.sendMessage(two.createMessage(MessageType.MESSAGE_4));
	}

}