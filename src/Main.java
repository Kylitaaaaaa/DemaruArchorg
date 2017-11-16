import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.print("First word: ");
		String first = sc.nextLine();
		System.out.print("Second word: ");
		String second = sc.nextLine();
		
		Damerau d2 = new Damerau(first, second);
		d2.OASDamerau();
		
		Damerau d1 = new Damerau(first, second);
		d1.tryThread();
		
		
		
//		d.OASDamerau();
//		d.DHDamerau();
	}

}
