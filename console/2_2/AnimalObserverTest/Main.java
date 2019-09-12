import java.util.Scanner;

public class Main {
	
	private final int tamagotchiAmmount = 5;
	private final int healthAtBegin = 10;
	
    public static void main (String[] args) {

	
		Scanner scanner = new Scanner(System.in);
		System.out.println("Type 'start' to begin");
		String input = scanner.nextLine();
		
		if(input.equals("start")){
			
			
			cls();
			System.out.println("Peli Aloitettu");
			
			Main main = new Main();
			main.GameStarts();
			
		}
		
    }
	
		public void GameStarts(){
		
			// Create the zoo to store animals
			Zoo zoo = new Zoo();
			// Register a listener to be notified when an animal is added
			zoo.registerAnimalAddedListener(new PrintNameAnimalAddedListener());
			zoo.registerAnimalAddedListener(new CountingAnimalAddedListener());
		
			for ( int i = 0; i < tamagotchiAmmount ; i++)
			{
				
				zoo.addAnimal(new Animal("Mooo",i,healthAtBegin));
	
			}
	}
	
		public static void cls(){
		try{	
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}catch(Exception E)
			{
				System.out.println(E);
			}
	}
}