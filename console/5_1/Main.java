import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;


class Main{
	
   public static void main(String[] args) {
	   System.out.print("Tiedostossa oleva teksti: ");
		try(BufferedReader br = new BufferedReader(new FileReader("testfile.txt"))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			System.out.println(everything);
		}catch(Exception e){}
		
	    Scanner scanner = new Scanner(System.in);
		System.out.println("Please write more content to file: ");
		String input = scanner.nextLine();
		
		try (FileWriter out = new FileWriter("testfile.txt", true)) {
		out.write("\n"+input);
		}catch(Exception e){}
   }
   
}