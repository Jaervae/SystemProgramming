import java.util.List;
import java.util.ArrayList;

public class Zoo extends Thread{
	
	private volatile boolean flag = true;
	private int id;
	
	public void stopRunning(){
        flag = false;
    }
	public void startRunning(){
        flag = true;
    }
	
	public Zoo (int param){
		
		id = param;
		
	}
	
	public Zoo (){
	
	}
	
    private List<Animal> animals = new ArrayList<>();
    private List<AnimalAddedListener> listeners = new ArrayList<>();
    public void addAnimal (Animal animal) {
        // Add the animal to the list of animals
        this.animals.add(animal);
        // Notify the list of registered listeners
        this.notifyAnimalAddedListeners(animal);
		
		Zoo zoo = new Zoo(animal.getNumber());
		zoo.start();
		
    }
    public void registerAnimalAddedListener (AnimalAddedListener listener) {
        // Add the listener to the list of registered listeners
        this.listeners.add(listener);
    }
    public void unregisterAnimalAddedListener (AnimalAddedListener listener) {
        // Remove the listener from the list of the registered listeners
        this.listeners.remove(listener);
    }
    protected void notifyAnimalAddedListeners (Animal animal) {
        // Notify each of the listeners in the list of registered listeners
        this.listeners.forEach(listener -> listener.onAnimalAdded(animal));
    }
	
	public void animalGotHungrier(int id){
		
		int hungryState = animals.get(id).getHungryState();
		hungryState =- 1;
		
		animals.get(id).setHungryState(hungryState);
		
		System.out.println("hungryState: " + hungryState + " id: " + id );
	}
	
	public void run(){
	
		try {
			while (flag) {
				Thread.sleep(2000);
				//System.out.println("Threadissa ollaan: " + id);
				Zoo zoo = new Zoo();
				zoo.animalGotHungrier(id);
			
		  }
		} catch(Exception e) {
		  e.printStackTrace();
		}
		
	}
	
}
