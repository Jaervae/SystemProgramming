import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;


class TamagotchiFarm implements ThreadClass.TamagotchiThreadReporterInterface{
	
	private final int tamagotchiAmmount = 5;
	private final int healthAtBegin = 10;
	ArrayList<ThreadClass> list = new ArrayList<ThreadClass>();
	private int id;
	private int cntr = 0;
	private boolean gameOn = false;
	Boolean[] booleanArray = new Boolean[tamagotchiAmmount];


	
    public static void main(String[] args) {
		

        Scanner scanner = new Scanner(System.in);
		System.out.println("Type 'start' to begin");
		String input = scanner.nextLine();
		
		if(input.equals("start")){
			cleanScreen();
			System.out.println("Peli Aloitettu");
			TamagotchiFarm tamagotchiFarm = new TamagotchiFarm();
			tamagotchiFarm.GameStarts();
			
		}
    }
	
	public void GameStarts(){
		id = 0;
		Arrays.fill(booleanArray, Boolean.FALSE);
		for ( int i = 0; i < tamagotchiAmmount ; i++)
		{
			createTamagotchi(i);
			ThreadClass object = new ThreadClass(this); 
			object.start();

		}
		gameOn = true;
		NotifyMainThread();
		
		while(gameOn){
			
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			try {
				int whatToFeed = Integer.parseInt(input);	
				if(whatToFeed <= tamagotchiAmmount){
					booleanArray[whatToFeed] = true;}
			} catch(NumberFormatException e) {
				  System.out.println("Thoose wasnt valid numbers :(");
			}
		}
	}
	
	public void createTamagotchi(int i){
		list.add(new ThreadClass(this));
		list.get(i).setNumber(i);
		list.get(i).setHungryState(10);
		list.get(i).setStatus("Alive and happy");
		try {
			Thread.sleep(100);
		} catch(Exception e) {
			  e.printStackTrace();
		}
		
	}
	
	public void interupted(){
		System.out.println("thread interrupted");

	}
	
	public int getOwnTamagotchi(){
		
		int temp = this.id;
		id++;
		return temp;
	}
	
	public void dataChanged(int id,int hungry){
		list.get(id).setHungryState(hungry);
		list.get(id).setStatus(CheckIfAlive(id));
		cleanScreen();
		NotifyMainThread();
	}
	
	public boolean checkIfNeedToEat(int id){
		if(booleanArray[id]){
			booleanArray[id] = false;
			return true;
		}	
		return false;
	}
	
	public void NotifyMainThread(){
		Thread t = new Thread(new Runnable() {
			public void run() {
				boolean allDied = true;
				for ( int i = 0; i < tamagotchiAmmount ; i++)
				{
					if(list.get(i).getHungryState() >= 0 || list.get(i).getHungryState() > 20){
						allDied = false;
						break;
					}			
				}
	
				cleanScreen();
				if(allDied)
					GameOver();
				
				for ( int i = 0; i < tamagotchiAmmount ; i++)
				{
					System.out.println("Tamagotchi " + i + " " + list.get(i).getStatus() + " Hungry status: " + list.get(i).getHungryState());
				}
			}
		});
		t.start();
	}
	
	public void GameOver(){
		gameOn = false;
		System.out.println("");
		System.out.println("");
		System.out.println("\t GAME OVER");
		System.out.println("");
		System.out.println("");
		System.exit(0); 
	}
	
	
	public int GetHungryStatus(int id){
		return list.get(id).getHungryState();
		
	}
	
	public String CheckIfAlive (int id){
		
		if (GetHungryStatus(id) < 0 ){
			
			return " was starved to dead :(";
			
		}
		
		if (GetHungryStatus(id) > 20 ){
			
			return " was overfed and now is dead.";
			
		}
		
		return " is happy and alive!";
		
	}
	
	
	public static void cleanScreen(){
		try{	
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}catch(Exception E)
			{
				System.out.println(E);
			}
	}
}