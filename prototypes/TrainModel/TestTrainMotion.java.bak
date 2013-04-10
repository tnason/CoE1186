public class TestTrainMotion {

	public static void main(String[] args) {
		TrainMotion test = new TrainMotion();	
		

		test.setGrade(1);
		for(int j = 0; j < 13; j++) {
			test.setPower(27+(10000*j));
			for(int i = 0; i < 2500; i++) {
				if(i % 250 == 0 || (j<2 && i < 20)) {
					test.printState();
				}
				test.motionStep();
			}	
		}

		for(int i = 0; i < 50000; i++) {
				if(i % 2500 == 0) {
					test.printState();
				}
				test.motionStep();
			}
		/*
		test.setPower(0);
		test.setEmergencyBrake(true);
		
		for(int i = 0; i < 10000; i++) {
			if(i % 1000 == 0) {
				test.printState();
			}
			test.motionStep();
		}	
		*/
	}
	
}