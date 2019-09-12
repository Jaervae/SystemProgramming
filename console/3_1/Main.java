import java.util.Scanner;


class Main implements ThreadClass.ThreadClassReporterInterFace{
	
	private String http;
	
    public static void main(String[] args) {
		Main main = new Main();
		main.StartThread();
    }
	
	public void StartThread(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Type the URL you wanna search -- Example: 'http://oamk.fi/~vetapani/'");
		http = scanner.nextLine();
		
		ThreadClass threadClass = new ThreadClass(this);
		threadClass.start();
		
	}
	
	
	//Interface methods
	public void interupted(){
		System.out.println("Thread was interupted");
	}
	
	public String getHTTP(){
		return http;
	}
	
	public void printHTTP(String mHttp){
		System.out.println(mHttp);
	}
	
}