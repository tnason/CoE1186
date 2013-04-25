package TLTTC;

import java.util.*;

public class UnitTests {
	private static TrackModel model;
	
	/*
	public static void main(String[] args){
		model = new TrackModel();
		model.initTrack();
		

		demoUseCase();
		
		beginCLI();
		
	}
	*/
	
	
	public static void demoUseCase(){
		//demo setting block 2 under maintenance
		System.out.println("\n\nDemonstrating put block 2 in maitenance mode\n\n");
		System.out.println(model.toString());
    	model.setBlockMaintenanceByID(2, true);
		System.out.println(model.toString());		
		System.out.println("\n\nDemonstrating put block 2 in operational mode\n\n");    	
		model.setBlockMaintenanceByID(2, false);		
		System.out.println(model.toString());

	}
	
	
	public static void beginCLI(){
		//simple command line interface
		System.out.println("Starting Track Model Command Line Interface (q to exit)");
		Scanner scanner = new Scanner(System.in);
		while(true){
			String input = scanner.nextLine();
			if(input.equals("q")){
				System.out.println("Leaving Track Model Command Line Interface");
				break;
			}
			
			Scanner tokenizer = new Scanner(input);
			
			String command = tokenizer.next();
			
			if(command.equals("show"))
			{
				System.out.println(model.toString());
			}
			else if(command.equals("setm"))
			{
				int blockID = tokenizer.nextInt();
				boolean state = tokenizer.nextBoolean();
				model.setBlockMaintenanceByID(blockID, state);			
			}
			
			
			
			
		}
		
		
	}
	
	
}
