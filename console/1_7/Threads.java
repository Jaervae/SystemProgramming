import java.util.Scanner;


class Threads{

    public static void main(String[] args) {
        Threads thread = new Threads();
		thread.startEngine();
    }
	
	public void startEngine(){
		
		boolean spamming = false;
		ThreadClass threadClass = new ThreadClass();
		try {
			while (true) {
				
				Scanner scanner = new Scanner(System.in);

				System.out.println("Herra, Anna komentosi : ");

				String input = scanner.nextLine();  // Lue komento
				
				if (input.equals("START")){
					
					System.out.println("Looppaus aloitettu");
	
					threadClass  = new ThreadClass();
				
					threadClass.start();
																	
					threadClass.startRunning();
					
				}
				
				if (input.equals("QUIT")){
					System.exit(0);
				}
				
				
				if (input.equals("STOP")){
					
					System.out.println("Looppaus lopetettu");
				
					threadClass.stopRunning();
					threadClass.join();
					
				}
			  
		  }
		} catch(Exception e) {
		  e.printStackTrace();
		}
	
	}
	
}