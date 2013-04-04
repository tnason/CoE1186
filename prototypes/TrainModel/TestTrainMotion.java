public class TestTrainMotion {

	public static void main(String[] args) {
		TrainMotion test = new TrainMotion();	
		

		test.setPower(120000);
		for(int i = 0; i < 1000000; i++) {
			if(i % 1000 == 0 && i < 200000) {
				test.printState();
			}
			test.motionStep();
		}	
		
		test.setPower(0);
		test.setEmergencyBrake(true);
		
		for(int i = 0; i < 10000; i++) {
			if(i % 1000 == 0) {
				test.printState();
			}
			test.motionStep();
		}	
	}
	
}